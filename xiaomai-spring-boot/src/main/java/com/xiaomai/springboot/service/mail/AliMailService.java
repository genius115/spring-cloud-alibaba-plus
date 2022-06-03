package com.xiaomai.springboot.service.mail;

/**
 * @author Madison
 * @date 2022/6/3
 */
public class AliMailService implements MailService{
    @Override
    public String sendMail(String msg) {
        return "AliMailService---"+msg;
    }
}
