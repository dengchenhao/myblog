
package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.BizPhotoAlbumBo;
import com.zyd.blog.business.service.BizPhotoAlbumService;
import com.zyd.blog.business.vo.BizPhotoAlbumConditionVO;
import com.zyd.blog.persistence.beans.BizPhotoAlbum;
import com.zyd.blog.persistence.mapper.BizPhotoAlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2023/07/29 16:04
 * @since 1.8
 */
@Service
public class BizPhotoAlbumServiceImpl implements BizPhotoAlbumService{

    @Autowired
    private BizPhotoAlbumMapper bizPhotoAlbumMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<BizPhotoAlbumBo> findPageBreakByCondition(BizPhotoAlbumConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizPhotoAlbum> list = bizPhotoAlbumMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<BizPhotoAlbumBo> boList = new ArrayList<>();
        for (BizPhotoAlbum bizPhotoAlbum : list){
            boList.add(new BizPhotoAlbumBo(bizPhotoAlbum));
        }
        PageInfo bean = new PageInfo<BizPhotoAlbum>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<BizPhotoAlbumBo> findPageBreakByConditionAuth(BizPhotoAlbumConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizPhotoAlbum> list = bizPhotoAlbumMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<BizPhotoAlbumBo> boList = new ArrayList<>();
        for (BizPhotoAlbum bizPhotoAlbum : list){
            if ( "1".equals(bizPhotoAlbum.getAuth())) {
                bizPhotoAlbum.setName(bizPhotoAlbum.getName()+"(私密，不可查看)");
                bizPhotoAlbum.setId((long) -1);
            }
            boList.add(new BizPhotoAlbumBo(bizPhotoAlbum));
        }
        PageInfo bean = new PageInfo<BizPhotoAlbum>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizPhotoAlbumBo insert(BizPhotoAlbumBo entity) {
        Assert.notNull(entity, "BizPhotoAlbum不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizPhotoAlbumMapper.insertSelective(entity.getBizPhotoAlbum());
        return entity;
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizPhotoAlbumMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(BizPhotoAlbumBo entity) {
        Assert.notNull(entity, "BizPhotoAlbum不可为空！");
        entity.setUpdateTime(new Date());
        return bizPhotoAlbumMapper.updateByPrimaryKeySelective(entity.getBizPhotoAlbum()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public BizPhotoAlbumBo getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizPhotoAlbum entity = bizPhotoAlbumMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new BizPhotoAlbumBo(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<BizPhotoAlbumBo> listAll() {
        List<BizPhotoAlbum> entityList = bizPhotoAlbumMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<BizPhotoAlbumBo> list = new ArrayList<>();
        for (BizPhotoAlbum entity : entityList) {
            list.add(new BizPhotoAlbumBo(entity));
        }
        return list;
    }
}
