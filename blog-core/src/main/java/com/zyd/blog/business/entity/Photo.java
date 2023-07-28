package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizFile;
import com.zyd.blog.persistence.beans.BizPhoto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Photo {
    private BizPhoto file;

    public Photo(BizPhoto file) {
        this.file = file;
    }

    public Photo() {
    }

    @JsonIgnore
    public BizPhoto getFile() {
        return file;
    }

    public Photo setFile(BizPhoto file) {
        this.file = file;
        return this;
    }

    public Long getId() {
        return this.file.getId();
    }

    public Photo setId(Long id) {
        this.file.setId(id);
        return this;
    }

    public Long getUserId() {
        return this.file.getUserId();
    }

    public Photo setUserId(Long userId) {
        this.file.setUserId(userId);
        return this;
    }

    public String getOriginalFileName() {
        return this.file.getOriginalFileName();
    }

    public Photo setOriginalFileName(String originalFileName) {
        this.file.setOriginalFileName(originalFileName);
        return this;
    }

    public String getFilePath() {
        return this.file.getFilePath();
    }

    public Photo setFilePath(String filePath) {
        this.file.setFilePath(filePath);
        return this;
    }

    public String getFullFilePath() {
        return this.file.getFullFilePath();
    }

    public Photo setFullFilePath(String fullFilePath) {
        this.file.setFullFilePath(fullFilePath);
        return this;
    }

    public String getFileHash() {
        return this.file.getFileHash();
    }

    public Photo setFileHash(String fileHash) {
        this.file.setFileHash(fileHash);
        return this;
    }

    public String getUploadType() {
        return this.file.getUploadType();
    }

    public Photo setUploadType(String uploadType) {
        this.file.setUploadType(uploadType);
        return this;
    }

    public Date getUploadStartTime() {
        return this.file.getUploadStartTime();
    }

    public Photo setUploadStartTime(Date uploadStartTime) {
        this.file.setUploadStartTime(uploadStartTime);
        return this;
    }

    public Date getUploadEndTime() {
        return this.file.getUploadEndTime();
    }

    public Photo setUploadEndTime(Date uploadEndTime) {
        this.file.setUploadEndTime(uploadEndTime);
        return this;
    }

    public Date getCreateTime() {
        return this.file.getCreateTime();
    }

    public Photo setCreateTime(Date createTime) {
        this.file.setCreateTime(createTime);
        return this;
    }

    public Date getUpdateTime() {
        return this.file.getUpdateTime();
    }

    public Photo setUpdateTime(Date updateTime) {
        this.file.setUpdateTime(updateTime);
        return this;
    }

    public Long getSize() {
        return this.file.getSize();
    }

    public Photo setSize(Long size) {
        this.file.setSize(size);
        return this;
    }

    public String getSuffix() {
        return this.file.getSuffix();
    }

    public Photo setSuffix(String suffix) {
        this.file.setSuffix(suffix);
        return this;
    }

    public Integer getWidth() {
        return this.file.getWidth();
    }

    public Photo setWidth(Integer width) {
        this.file.setWidth(width);
        return this;
    }

    public Integer getHeight() {
        return this.file.getHeight();
    }

    public Photo setHeight(Integer height) {
        this.file.setHeight(height);
        return this;
    }

    public String getStorageType() {
        return this.file.getStorageType();
    }

    public Photo setStorageType(String storageTypeEnum) {
        this.file.setStorageType(storageTypeEnum);
        return this;
    }

}