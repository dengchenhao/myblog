package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.File;
import com.zyd.blog.business.entity.Photo;
import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.business.service.BizFileService;
import com.zyd.blog.business.service.BizPhotoService;
import com.zyd.blog.business.vo.FileConditionVO;
import com.zyd.blog.business.vo.PhotoConditionVO;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.exception.GlobalFileException;
import com.zyd.blog.persistence.beans.BizFile;
import com.zyd.blog.persistence.beans.BizPhoto;
import com.zyd.blog.persistence.mapper.BizFileMapper;
import com.zyd.blog.persistence.mapper.BizPhotoMapper;
import com.zyd.blog.plugin.file.GlobalFileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Service
public class BizPhotoServiceImpl implements BizPhotoService {

    @Autowired
    private BizPhotoMapper photoMapper;

    @Override
    public PageInfo<Photo> findPageBreakByCondition(PhotoConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizPhoto> list = photoMapper.findPageBreakByCondition(vo);
        List<Photo> boList = getFiles(list);
        if (boList == null) return null;
        PageInfo bean = new PageInfo<BizPhoto>(list);
        bean.setList(boList);
        return bean;
    }

    private List<Photo> getFiles(List<BizPhoto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Photo> boList = new ArrayList<>();
        for (BizPhoto bizFile : list) {
            boList.add(new Photo(bizFile));
        }
        return boList;
    }

    @Override
    public Photo selectFileByPathAndUploadType(String filePath, String uploadType) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        BizPhoto file = new BizPhoto();
        file.setFilePath(filePath);
        if (StringUtils.isEmpty(uploadType)) {
            file.setUploadType(uploadType);
        }
        List<BizPhoto> fileList = photoMapper.select(file);
        return CollectionUtils.isEmpty(fileList) ? null : new Photo(fileList.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long[] ids) {
        for (Long id : ids) {
            Photo oldFile = this.getByPrimaryKey(id);
            this.removeByPrimaryKey(id);
            try {
                FileUploader uploader = new GlobalFileUploader();
                uploader.delete(oldFile.getFilePath(), oldFile.getUploadType());
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile[] file, Long albumId) {
        if (null == file || file.length == 0) {
            throw new GlobalFileException("请至少选择一张图片！");
        }
        for (MultipartFile multipartFile : file) {
            FileUploader uploader = new GlobalFileUploader();
            uploader.uploadPhoto(multipartFile, FileUploadType.PHOTO.getPath(), true, albumId);
        }
        return file.length;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int uploadPhoto(MultipartFile[] file) {
        if (null == file || file.length == 0) {
            throw new GlobalFileException("请至少选择一张图片！");
        }
        for (MultipartFile multipartFile : file) {
            FileUploader uploader = new GlobalFileUploader();
            uploader.upload(multipartFile, FileUploadType.PHOTO.getPath(), true);
        }
        return file.length;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Photo insert(Photo entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        photoMapper.insertSelective(entity.getFile());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        return photoMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Photo entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setUpdateTime(new Date());
        return photoMapper.updateByPrimaryKeySelective(entity.getFile()) > 0;
    }

    @Override
    public Photo getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        BizPhoto entity = photoMapper.selectByPrimaryKey(primaryKey);
        return new Photo(entity);
    }


    @Override
    public List<Photo> listAll() {
        List<BizPhoto> list = photoMapper.selectAll();

        return getFiles(list);
    }
}
