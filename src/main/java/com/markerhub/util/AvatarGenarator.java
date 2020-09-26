package com.markerhub.util;

import cn.hutool.core.util.IdUtil;
import com.github.afkbrb.avatar.Avatar;
import com.github.afkbrb.avatar.AvatarConfig;

import java.awt.*;
import java.io.File;

/**
 * 生成头像
 */
public class AvatarGenarator {

    public static String saveAndGetPath() {
        AvatarConfig config = new AvatarConfig();
        config.setPadding(16);
        config.setCellSize(32);
        config.setCellCount(8);
        config.setTransparent(true);
        Color color = new Color(
                            (new Double(Math.random() * 128)).intValue() + 128,
                            (new Double(Math.random() * 128)).intValue() + 128,
                            (new Double(Math.random() * 128)).intValue() + 128
                       );
        config.setForeColors(color);
        Avatar avatar = new Avatar(config);
        String realFilename = IdUtil.randomUUID()+".png";
        String projectPath = System.getProperty("user.dir");
        String imagePath = projectPath + "\\src\\main\\resources\\avatar";
        String targetPath=imagePath +"\\"+ realFilename;
        File directory = new File(imagePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        avatar.saveAsPNG(targetPath);
        return realFilename;
    }
}
