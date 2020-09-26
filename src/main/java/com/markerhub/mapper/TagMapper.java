package com.markerhub.mapper;

import com.markerhub.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hai
 * @since 2020-08-29
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 获取所有标签及包含文章数
     * @return
     */
    List<Tag> getAll();

    /**
     * 合计标签下的博客数量
     * @param tagId
     * @return
     */
    int countBlogbyTag(@Param("tagId")Long tagId);

}
