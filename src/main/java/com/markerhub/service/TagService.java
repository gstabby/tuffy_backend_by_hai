package com.markerhub.service;

import com.markerhub.entity.Blog;
import com.markerhub.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hai
 * @since 2020-08-29
 */
public interface TagService extends IService<Tag> {

    List<Tag> getAll();

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    Boolean deleteTag(Long tagId);
}
