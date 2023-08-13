
package com.zyd.blog.business.vo;

import com.zyd.blog.framework.object.BaseConditionVO;
import com.zyd.blog.business.entity.BizPhotoAlbumBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 15:44
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizPhotoAlbumConditionVO extends BaseConditionVO {

    Long albumId;
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

}

