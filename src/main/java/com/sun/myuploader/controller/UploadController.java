package com.sun.myuploader.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sun.myuploader.constant.URLConstant;
import com.sun.myuploader.dao.FileMapper;
import com.sun.myuploader.model.FileExists;
import com.sun.myuploader.model.FilePojo;
import com.sun.myuploader.model.Result;
import com.sun.myuploader.utils.UploadUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UploadController {

	private Logger logger = LogManager.getLogger(UploadController.class.getName());	

	
    @Autowired
    private FileMapper fileMapper;

    @RequestMapping(value = URLConstant.NEWFILE , method = RequestMethod.POST)
    public FilePojo uploadFile(@RequestBody FilePojo file) {
    	logger.info("================新增文件记录:"+file.getName());
    	fileMapper.insertFile(file);
        return file;
    }

    /**
                * 文件是否存在
     *
     *  MD5
     * @param md5
     * @param size
     * @return
     */
    @RequestMapping(value = URLConstant.CHECKFILE , method = RequestMethod.GET)
    public FileExists fileExists(String md5, Long size) {
        FilePojo file = fileMapper.findByMd5(md5);
        if (file == null) {
        	logger.info("===================不存在："+md5);
            return FileExists.nonExistent();
        }
        if (file.getSize().equals(size)) {
        	logger.info("===================已存在："+md5);
            return FileExists.exists(file.getId());
        }
        // 部分存在
        return FileExists.partExistent(file.getId(), fileMapper.findByParent(file.getId()));
    }

    @RequestMapping(value = URLConstant.UPLOADFILE , method = RequestMethod.POST)
    public Result filePatchExists(String name, Integer index, Integer parent, String md5, Long size, MultipartFile patch) throws IOException {
        FilePojo file = fileMapper.findByParentAndMd5(parent, md5);
        if (file == null || !file.getSize().equals(size)) {
            Optional.ofNullable(file).ifPresent(e -> fileMapper.deleteById(e.getId()));
            fileMapper.insertFile(new FilePojo(index, parent, name, UploadUtil.saveFile(patch, size), md5, size));
            logger.info("==============新增分片："+index+"成功");
            return Result.OK();
        }
        return file.getSize().equals(size) ? Result.OK() : Result.FAIL();
    }

    @Transactional
    @RequestMapping(value = URLConstant.MERGEFILE , method = RequestMethod.POST)
    public Result filePatchMerge(Integer parent, Long size) throws IOException {
        FilePojo fileInfo = fileMapper.findById(parent);
        List<FilePojo> patchs = fileMapper.findByParentOrderByPatchIndexAsc(parent);
        Long total = patchs.stream().mapToLong(FilePojo::getSize).sum();
        if (fileInfo == null || CollectionUtils.isEmpty(patchs) || !total.equals(size)) {
            return Result.FAIL();
        }
        String fileType = UploadUtil.parseFileType(fileInfo.getName());
        String path = UploadUtil.mergeFile(fileType, patchs.stream().map(FilePojo::getPath).collect(Collectors.toList()));
        fileMapper.updateByIdSetPathAndSize(parent, path, total);
        fileMapper.deleteByParent(parent);
        logger.info("==============================删除parent："+parent);
        return Result.OK();
    }
}