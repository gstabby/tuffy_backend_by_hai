package com.markerhub.service.impl;

import com.markerhub.entity.Category;
import com.markerhub.mapper.CategoryMapper;
import com.markerhub.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-08-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getAll() {
        return this.baseMapper.getAll();
    }

    @Override
    public int deleteNoBlog(Long id) {
        return this.baseMapper.deleteNoBlog(id);
    }
}
