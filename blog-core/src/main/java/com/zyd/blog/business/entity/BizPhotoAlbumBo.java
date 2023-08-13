
package com.zyd.blog.business.entity;

import com.zyd.blog.persistence.beans.BizPhotoAlbum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 16:04
 * @since 1.8
 */
public class BizPhotoAlbumBo {
	private static final long serialVersionUID = 1L;
	private BizPhotoAlbum bizPhotoAlbum;

	public BizPhotoAlbumBo () {
		this.bizPhotoAlbum = new BizPhotoAlbum();
	}

	public BizPhotoAlbumBo (BizPhotoAlbum bizPhotoAlbum) {
		this.bizPhotoAlbum = bizPhotoAlbum;
	}

	@JsonIgnore
	public BizPhotoAlbum getBizPhotoAlbum(){
		return this.bizPhotoAlbum;
	}

	public Long getId() {
		return this.bizPhotoAlbum.getId();
	}

	public void setId(Long id) {
		this.bizPhotoAlbum.setId(id);
	}

	public String getName() {
		return this.bizPhotoAlbum.getName();
	}

	public void setName(String name) {
		this.bizPhotoAlbum.setName(name);
	}

	public String getDetail() {
		return this.bizPhotoAlbum.getDetail();
	}

	public void setDetail(String detail) {
		this.bizPhotoAlbum.setDetail(detail);
	}

	public String getAuth() {
		return this.bizPhotoAlbum.getAuth();
	}

	public void setAuth(String auth) {
		this.bizPhotoAlbum.setAuth(auth);
	}

	public Date getCreateTime() {
		return this.bizPhotoAlbum.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		this.bizPhotoAlbum.setCreateTime(createTime);
	}

	public Date getUpdateTime() {
		return this.bizPhotoAlbum.getUpdateTime();
	}

	public void setUpdateTime(Date updateTime) {
		this.bizPhotoAlbum.setUpdateTime(updateTime);
	}

	public String getCover() {
		return this.bizPhotoAlbum.getCover();
	}

	public void setCover(String cover) {
		this.bizPhotoAlbum.setCover(cover);
	}

}

