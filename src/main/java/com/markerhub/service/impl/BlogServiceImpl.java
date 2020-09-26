package com.markerhub.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.Blog;
import com.markerhub.entity.Category;
import com.markerhub.entity.Tag;
import com.markerhub.entity.dto.BlogDto;
import com.markerhub.mapper.BlogMapper;
import com.markerhub.mapper.CategoryMapper;
import com.markerhub.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.service.CategoryService;
import com.markerhub.service.TagService;
import com.markerhub.util.FileUtil;
import com.markerhub.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    final static private Long SIZE = 5L;

    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;


    @Override
    public IPage<Blog> getAllByPage(Long current) {
        Page<Blog> page = new Page<Blog>(current,SIZE);
        QueryWrapper<Blog> wrapper = new QueryWrapper<Blog> ();
        wrapper.orderByDesc("created");
        return this.baseMapper.getAllByPage(page,wrapper);
    }

    @Override
    public Map<String,Object> getByCategory(Long categoryId) {
        List<Blog> blogs = baseMapper.getByCategory(categoryId);
        Category category = categoryService.getById(categoryId);
        Map<String,Object> data = new HashMap();
        data.put("blogs",blogs);
        data.put("category",category);
        return data;
    }

    @Override
    public Map<String,Object> getByTag(Long tagId){
        List<Blog> blogs = this.baseMapper.getByTag(tagId);
        Tag tag = tagService.getById(tagId);
        Map<String,Object> data = new HashMap();
        data.put("blogs",blogs);
        data.put("tag",tag);
        return data;
    }

    @Override
    public Blog getById(Long id) {
        return this.baseMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class )
    public void save(BlogDto blogDto) throws IOException {
        Map<String,Object> map = new HashMap<>();

        String path = FileUtil.uplodeFile(blogDto.getFile());
        map.put("userId",ShiroUtil.getProfile().getId().longValue());
        map.put("title",blogDto.getTitle());
        map.put("description",blogDto.getDescription());
        map.put("content",blogDto.getContent());
        map.put("created", DateUtil.date());
        map.put("status",1);
        map.put("category",blogDto.getCategory());
        map.put("path",path);
        this.baseMapper.save(map);

        Long[] tags = blogDto.getTags();
        for(int i = 0;i<tags.length;i++){
            this.baseMapper.saveBlogTag(tags[i],((Number)map.get("id")).longValue());
        }

    }

    @Override
    public List<Blog> getAll() {
        return this.baseMapper.getAll();
    }

    @Override
    public List<Map<String, Object>> getAllGroupByYear() {
        List list = this.baseMapper.getBlogCreateDateList();
        List<Map<String, Object>> data = new ArrayList();
        if(null!=list || list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map map = new HashMap();
                String date = (String) list.get(i);
                map.put("date",date);
                List<Map<String,Object>> blogList = this.baseMapper.getBlogByYear(date);
                map.put("blogs",blogList);
                data.add(map);
            }
        }
        return data;
    }

    @Override
    public void addReadNum(Long id) {
        this.baseMapper.addReadNum(id);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Blog> getByLike(String key) {
        return this.baseMapper.getByLike("%"+key+"%");
    }
}
