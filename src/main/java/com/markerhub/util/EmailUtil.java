package com.markerhub.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件工具
 * @author 86182
 */
@Component
@ConfigurationProperties(prefix = "emailconfig")
@Data
public class EmailUtil {
    private Map<String,Object> email = new HashMap<>();

    public String send(Collection<String> tos, String subject, String content) {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost((String) email.get("host"));
        mailAccount.setPort((Integer) email.get("port"));
        mailAccount.setAuth((Boolean) email.get("auth"));
        mailAccount.setFrom((String) email.get("from"));
        mailAccount.setUser((String) email.get("user"));
        mailAccount.setPass((String) email.get("pass"));
        return MailUtil.send(mailAccount, tos,subject,content,false);
    }


}
