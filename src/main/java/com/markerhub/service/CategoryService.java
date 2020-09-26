package com.markerhub.service;

import com.markerhub.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hai
 * @since 2020-08-24
 */
public interface CategoryService extends IService<Category> {
    /**
     * 获取所有分类及分类的文章数量
     * @return
     */
    List<Category> getAll();

    /**
     * 不存在博客时删除
     * @param id
     * @return
     */
    int deleteNoBlog(Long id);
}
