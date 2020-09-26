package com.markerhub.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.markerhub.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.entity.dto.BlogDto;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
public interface BlogService extends IService<Blog> {
    /**
     * 获取所有博客
     * @return
     */
   // List<Blog> getAll();
    IPage<Blog> getAllByPage(Long current);


    /**
     * 获取博客通过分类
     * @param categoryId
     * @return
     */
    public Map<String,Object> getByCategory(Long categoryId);

    /**
     * 获取博客通过标签
     * @param tagId
     * @return
     */
     Map<String,Object> getByTag(Long tagId);

    /**
     * 获取博客通过id
     * @param id
     * @return
     */
    Blog getById(Long id);

    /**
     * 保存博客
     * @param blogDto
     */
    void save(BlogDto blogDto) throws IOException;

    /**
     * 获取所有博客简信息
     * @return
     */
    List<Blog> getAll();

    /**
     * 通过年份分组获取博客
     * @return
     */
    List<Map<String,Object>> getAllGroupByYear();

    /**
     * 阅读量加一
     * @param id
     */
    void addReadNum(Long id);

    /**
     * 删除博客以及中间表
     * @param id
     */
    void deleteById(Long id);

    /**
     * 模糊查询博客
     * @param key
     * @return
     */
    List<Blog> getByLike(@Param("key") String key);
}
