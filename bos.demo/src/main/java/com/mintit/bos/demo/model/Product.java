package com.mintit.bos.demo.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private Long prodId;
    private String prodName;
    private Integer prodPrice;
    private String modelName;
    private String prodType;
    private String spec;
    private Date regDate;

//    public void setProdId(Long prodId){
//        this.prodId = prodId;
//    }

}