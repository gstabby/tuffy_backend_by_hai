package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markerhub.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 查询所有博客
     * @return
     */
    IPage<Blog> getAllByPage(IPage<Blog> page, Wrapper<Blog> wrapper);


    /**
     * 根据标签获取博客
     * @param tagId
     * @return
     */
    List<Blog> getByTag(@Param("tagId") Long tagId);


    /**
     *
     * @param category
     * @return
     */
    List<Blog> getByCategory( @Param("category") Long category);



    /**
     * 通过id获取博客
     * @param id
     * @return
     */
    Blog getById(@Param("id") Long id);


    /**
     * 保存博客
     * @param map
     * @return
     */
    void save(Map map);

    /**
     * 保存博客的标签
     * @param tagId
     * @param blogId
     */
    void saveBlogTag(@Param("tagId") long tagId,@Param("blogId") Long blogId);

    /**
     * 获取博客简单信息
     * @return
     */
    List<Blog> getAll();


    /**
     * 获取博客的年份集合
     * @return
     */
    List<String> getBlogCreateDateList();

    /**
     * 根据年份获取博客
     * @param year
     * @return
     */
    List<Map<String,Object>> getBlogByYear(@Param("year")String year);


    /**
     * 阅读量加一
     * @param id
     */
    void addReadNum(@Param("id") Long id);

    /**
     * 删除博客
     * @param id
     */
    void deleteByid(@Param("id") Long id);

    /**
     * 模糊查询博客
     * @param key
     * @return
     */
    List<Blog> getByLike(@Param("key") String key);


}
