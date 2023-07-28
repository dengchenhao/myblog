package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.service.BizFileService;
import com.zyd.blog.business.service.BizPhotoService;
import com.zyd.blog.business.vo.FileConditionVO;
import com.zyd.blog.business.vo.PhotoConditionVO;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2019/2/14 11:37
 * @since 1.0
 */
@RestController
@RequestMapping("/photo")
public class RestPhotoController {
    @Autowired
    private BizPhotoService photoService;

    @RequiresPermissions("photo")
    @PostMapping("/list")
    public PageInfo list(PhotoConditionVO vo) {
        vo.setPageSize(20);
        return photoService.findPageBreakByCondition(vo);
    }

    @RequiresPermissions("photo")
    @PostMapping(value = "/remove")
    @BussinessLog("删除文件，ids:{1}")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        photoService.remove(ids);

        return ResultUtil.success("成功删除 [" + ids.length + "] 张图片");
    }

    @RequiresPermissions("photo")
    @PostMapping(value = "/add")
    @BussinessLog("添加文件")
    public ResponseVO add(MultipartFile[] file) {
        if (null == file || file.length == 0) {
            return ResultUtil.error("请至少选择一张图片！");
        }
        int res = photoService.upload(file);
        return ResultUtil.success("成功上传" + res + "张图片");
    }
}
