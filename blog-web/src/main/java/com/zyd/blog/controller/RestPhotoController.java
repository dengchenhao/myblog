package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.service.BizPhotoService;
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

    @PostMapping("/list")
    public PageInfo list(PhotoConditionVO vo) {
        vo.setPageSize(20);
        return photoService.findPageBreakByCondition(vo);
    }

}
