
package com.zyd.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.BizPhotoAlbumBo;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.BizPhotoAlbumService;
import com.zyd.blog.business.vo.BizPhotoAlbumConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 15:44
 * @since 1.8
 */
@RestController
@RequestMapping("/bizPhotoAlbum")
public class RestBizPhotoAlbumController {
    @Autowired
    private BizPhotoAlbumService bizPhotoAlbumService;

    @PostMapping("/list")
    public PageInfo list(BizPhotoAlbumConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<BizPhotoAlbumBo> pageInfo = bizPhotoAlbumService.findPageBreakByConditionAuth(vo);
        return pageInfo;
    }

    @PostMapping("/listAll")
    public ResponseVO listAlbum() {
        return ResultUtil.success(null, bizPhotoAlbumService.listAll());
    }

}
