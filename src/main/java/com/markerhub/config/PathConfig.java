package com.markerhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PathConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String  projectPath = System.getProperty("user.dir");
        String  imagePath = projectPath + "\\src\\main\\resources\\image\\";
        String  avatarPath = projectPath + "\\src\\main\\resources\\avatar\\";
        registry.addResourceHandler("/image/**").addResourceLocations("file:///"+imagePath);
        registry.addResourceHandler("/avatar/**").addResourceLocations("file:///"+avatarPath);
        super.addResourceHandlers(registry);
    }

}
