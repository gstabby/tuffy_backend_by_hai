package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.Category;
import com.markerhub.entity.Tag;
import com.markerhub.service.CategoryService;
import com.markerhub.service.TagService;
import io.swagger.annotations.Authorization;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hai
 * @since 2020-08-29
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping(value = "/all")
    public Result getAll(){
        List<Tag> tagsList = tagService.getAll();
        return Result.succ(tagsList);
    }

    @RequiresRoles("vip")
    @PostMapping(value = "/saveOrUpdate")
    public Result edit(@Validated Tag tag){
        tagService.saveOrUpdate(tag);
        return Result.succ(null);
    }

    @RequiresRoles("vip")
    @PostMapping(value = "/delete")
    public Result deleteById(@RequestParam(name  = "id",required = true) Long id){
        Boolean result = tagService.deleteTag(id);
        if(result){
            return Result.succ(null);
        }else{
            return Result.fail("标签存在文章无法删除");
        }

    }
}
