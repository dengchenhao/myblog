
package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.vo.BizPhotoAlbumConditionVO;
import com.zyd.blog.persistence.beans.BizPhotoAlbum;
import com.zyd.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 15:44
 * @since 1.8
 */
@Repository
public interface BizPhotoAlbumMapper extends BaseMapper<BizPhotoAlbum>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizPhotoAlbum> findPageBreakByCondition(BizPhotoAlbumConditionVO vo);
}
