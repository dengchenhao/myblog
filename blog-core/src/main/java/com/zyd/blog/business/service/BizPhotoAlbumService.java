
package com.zyd.blog.business.service;


import com.zyd.blog.framework.object.AbstractService;
import com.zyd.blog.business.entity.BizPhotoAlbumBo;
import com.zyd.blog.business.vo.BizPhotoAlbumConditionVO;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 15:44
 * @since 1.8
 */
public interface BizPhotoAlbumService extends AbstractService<BizPhotoAlbumBo, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<BizPhotoAlbumBo> findPageBreakByCondition(BizPhotoAlbumConditionVO vo);

    PageInfo<BizPhotoAlbumBo> findPageBreakByConditionAuth(BizPhotoAlbumConditionVO vo);
}
