package com.markerhub.service.impl;

import com.markerhub.entity.Blog;
import com.markerhub.entity.Category;
import com.markerhub.entity.Tag;
import com.markerhub.mapper.TagMapper;
import com.markerhub.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-08-29
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> getAll() {
        return this.baseMapper.getAll();
    }

    @Override
    public Boolean deleteTag(Long tagId) {
        int count = this.baseMapper.countBlogbyTag(tagId);
        if(count==0){
            this.baseMapper.deleteById(tagId);
            return true;
        }
        return false;
    }

}
