package com.markerhub.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Blog;
import com.markerhub.entity.User;
import com.markerhub.entity.dto.BlogDto;
import com.markerhub.service.BlogService;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-05-25
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;


    @GetMapping("/blogs")
    public Result listByPage(@RequestParam(defaultValue = "1") Long current) {
        IPage<Blog> blogs = blogService.getAllByPage(current);
        return Result.succ(blogs);
    }


    @GetMapping("/blog/getByCategory")
    public Result getByCategory(@RequestParam(name = "category",required = true) Long category) {
        return Result.succ(blogService.getByCategory(category));
    }

    @GetMapping("/blog/getByTag")
    public Result getByTag(@RequestParam(name = "tag",required = true) Long tag) {
        return Result.succ(blogService.getByTag(tag));
    }


    @GetMapping("/blog/getById")
    public Result getById(@RequestParam(name = "id",required = true) Long id) {
        Blog blog = blogService.getById(id);
        return Result.succ(blog);
    }

    @GetMapping("/blog/total")
    public Result total() {
        int  total = blogService.count();
        return Result.succ(total);
    }

    @GetMapping("/blog/all")
    public Result getAll() {
        List<Blog> blogs = blogService.getAll();
        return Result.succ(blogs);
    }

    @GetMapping("/blog/getAllGroupByYear")
    public Result getAllGroupByYear() {
        return Result.succ(blogService.getAllGroupByYear());
    }



    @RequiresRoles("vip")
    @PostMapping("/blog/delete")
    public Result deleteById(@RequestParam(name  = "id",required = true) Long id) {
        blogService.deleteById(id);
        return Result.succ("删除成功");
    }

    @RequiresRoles("vip")
    @PostMapping("/blog/save")
    public Result save(@Validated BlogDto blogDto) throws IOException {

        MultipartFile file = blogDto.getFile();
        if(null==file || file.isEmpty()){
            return Result.fail("请上传图片");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return Result.fail("请选择jpg,jpeg,gif,png格式的图片");
        }
        blogService.save(blogDto);
        return Result.succ(null);
    }

    @PostMapping("/blog/read")
    Result readBlog(@RequestParam(value = "id",required = true) Long id){
        blogService.addReadNum(id);
        return Result.succ("已阅读");
    }

    @GetMapping("/blog/getByLike")
    public Result getByLike(@RequestParam("key") String key) {
        if(StrUtil.isBlank(key)){
            int[] array = null;
            return Result.succ(array);
        }
        return Result.succ(blogService.getByLike(key));
    }

}
