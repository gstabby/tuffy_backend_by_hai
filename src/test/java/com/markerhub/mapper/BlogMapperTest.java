package com.markerhub.mapper;

import com.markerhub.VueblogApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogMapperTest  {

    @Autowired
    BlogMapper blogMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void getById() {
        blogMapper.getById(1L);
    }
}