package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.vo.FileConditionVO;
import com.zyd.blog.business.vo.PhotoConditionVO;
import com.zyd.blog.persistence.beans.BizFile;
import com.zyd.blog.persistence.beans.BizPhoto;
import com.zyd.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Repository
public interface BizPhotoMapper extends BaseMapper<BizPhoto> {

    List<BizPhoto> findPageBreakByCondition(PhotoConditionVO vo);
}
