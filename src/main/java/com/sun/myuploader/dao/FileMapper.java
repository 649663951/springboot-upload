package com.sun.myuploader.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sun.myuploader.model.FilePojo;
import java.util.List;

/**
 * 
 * @author andy
 *
 */
@Mapper
public interface FileMapper {

    int insertFile(@Param("file") FilePojo file);


    int updateByIdSetPathAndSize(@Param("id") Integer id, @Param("path") String path, @Param("size") Long size);


    int deleteById(@Param("id") Integer id);


    int deleteByParent(@Param("parent") Integer parent);


    FilePojo findById(@Param("id") Integer id);


    List<Integer> findByParent(@Param("parent") Integer parent);


    FilePojo findByMd5(@Param("md5") String md5);


    FilePojo findByParentAndMd5(@Param("parent") Integer parent, @Param("md5") String md5);

    List<FilePojo> findByParentOrderByPatchIndexAsc(@Param("parent") Integer parent);
}