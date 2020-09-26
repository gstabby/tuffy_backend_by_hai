package com.markerhub.mapper;

import com.markerhub.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-08-24
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取所有的分类及对应文章数
     * @return
     */
    List<Category> getAll();


    /**
     * 不存在博客时删除
     * @param id
     * @return
     */
    int deleteNoBlog(@Param("id") Long id);
}
