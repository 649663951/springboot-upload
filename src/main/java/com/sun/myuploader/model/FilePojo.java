package com.sun.myuploader.model;

import java.util.Date;

public class FilePojo {
    
    private Integer id;

    private Integer patchIndex;

    private Integer parent;

    private String name;

    private String path;

    private String md5;

    private Long size;

    private Date createTime;

    public FilePojo() {

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatchIndex() {
		return patchIndex;
	}

	public void setPatchIndex(Integer patchIndex) {
		this.patchIndex = patchIndex;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public FilePojo(Integer patchIndex, Integer parent, String name, String path, String md5, Long size) {
        this.patchIndex = patchIndex;
        this.parent = parent;
        this.name = name;
        this.path = path;
        this.md5 = md5;
        this.size = size;
    }
}