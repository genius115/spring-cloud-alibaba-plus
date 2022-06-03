package com.xiaomai.springboot.service.mail;

/**
 * @author Madison
 * @date 2022/6/3
 */
public class SinaMailService implements MailService {
    @Override
    public String sendMail(String msg) {
        return "SinaMailService---"+msg;
    }
}
