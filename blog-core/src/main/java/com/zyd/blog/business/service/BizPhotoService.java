package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.File;
import com.zyd.blog.business.entity.Photo;
import com.zyd.blog.business.vo.FileConditionVO;
import com.zyd.blog.business.vo.PhotoConditionVO;
import com.zyd.blog.framework.object.AbstractService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
public interface BizPhotoService extends AbstractService<Photo, Long> {

    PageInfo<Photo> findPageBreakByCondition(PhotoConditionVO vo);

    Photo selectFileByPathAndUploadType(String filePath, String uploadType);

    void remove(Long[] ids);

    int upload(MultipartFile[] file);

    @Transactional(rollbackFor = Exception.class)
    int uploadPhoto(MultipartFile[] file);
}
