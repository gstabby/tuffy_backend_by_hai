package com.markerhub.controller;


import cn.hutool.core.bean.BeanUtil;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Blog;
import com.markerhub.entity.Category;
import com.markerhub.service.CategoryService;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/all")
    public Result getAll(){
        List<Category> categoryList = categoryService.getAll();
        return Result.succ(categoryList);
    }

    @RequiresRoles("vip")
    @PostMapping(value = "/saveOrUpdate")
    public Result saveOrUpdate(@Validated Category category){
        categoryService.saveOrUpdate(category);
        return Result.succ(null);
    }

    @RequiresRoles("vip")
    @PostMapping(value = "/delete")
    public Result deleteById(@RequestParam(name  = "id",required = true) Long id){
        int result = categoryService.deleteNoBlog(id);
        if(result>0){
            return Result.succ(null);
        }else{
            return  Result.fail("该分类下存在博客无法删除");
        }

    }

}
