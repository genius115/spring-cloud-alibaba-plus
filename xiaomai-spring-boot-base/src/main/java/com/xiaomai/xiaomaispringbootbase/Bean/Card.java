package com.xiaomai.xiaomaispringbootbase.Bean;

import lombok.Data;

/**
 * @author Madison
 * @date 2022/6/21
 */
@Data
public class Card {
    private Integer cardId;
    //⾝份证号
    private String cardNum;
    //地址
    private String address;
}
