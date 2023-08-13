
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

    @RequiresPermissions("bizPhotoAlbums")
    @PostMapping("/list")
    public PageResult list(BizPhotoAlbumConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<BizPhotoAlbumBo> pageInfo = bizPhotoAlbumService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @PostMapping("/listAll")
    public ResponseVO listAlbum() {
        return ResultUtil.success(null, bizPhotoAlbumService.listAll());
    }

    @RequiresPermissions("bizPhotoAlbum:add")
    @PostMapping(value = "/add")
    public ResponseVO add(BizPhotoAlbumBo bizPhotoAlbum) {
        bizPhotoAlbumService.insert(bizPhotoAlbum);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"bizPhotoAlbum:batchDelete", "bizPhotoAlbum:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            bizPhotoAlbumService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 条记录");
    }

    @RequiresPermissions("bizPhotoAlbum:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.bizPhotoAlbumService.getByPrimaryKey(id));
    }

    @RequiresPermissions("bizPhotoAlbum:edit")
    @PostMapping("/edit")
    public ResponseVO edit(BizPhotoAlbumBo bizPhotoAlbum) {
        try {
            bizPhotoAlbumService.updateSelective(bizPhotoAlbum);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("数据修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
