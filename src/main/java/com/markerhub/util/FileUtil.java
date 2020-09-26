package com.markerhub.util;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static  String uplodeFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String fileType =  file.getOriginalFilename().substring(filename.lastIndexOf(".") );
        String realFilename = IdUtil.randomUUID();
        String renameFile = realFilename + fileType;
        String projectPath = System.getProperty("user.dir");
        String  imagePath = projectPath + "\\src\\main\\resources\\image";
        File directory = new File(imagePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //transferTo()方法将上传的文件写到服务器指定的文件
        String targetPath=imagePath +"\\"+ renameFile;
        System.out.println(targetPath+"====================================================");
        file.transferTo(new File(targetPath));
        return renameFile;
    }
}
