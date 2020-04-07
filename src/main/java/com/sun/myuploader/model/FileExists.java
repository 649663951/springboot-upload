package com.sun.myuploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileExists {
    /**
               * 文件 id
     */
    private Integer id;

    /**
               * 文件状态
     *      -1: 不存在
     *       1: 已存在
     *       0: 部分存在
     */
    private Integer status;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Integer> getPatchIndex() {
		return patchIndex;
	}

	public void setPatchIndex(List<Integer> patchIndex) {
		this.patchIndex = patchIndex;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
                * 已上传的文件分片索引
     */
    private List<Integer> patchIndex;

    public Integer getId() {
        return id;
    }

    public static FileExists nonExistent() {
        return new FileExists(null, -1, null);
    }

    public static FileExists exists(Integer id) {
        return new FileExists(id, 1, null);
    }

    public static FileExists partExistent(Integer id, List<Integer> patchIndex) {
        return new FileExists(id, 0, patchIndex);
    }
    
    public FileExists(Integer id, Integer status, List<Integer> patchIndex) {
        this.id = id;
        this.status = status;
        this.patchIndex = patchIndex;
    }
}