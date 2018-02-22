package com.example.demo.mybatis.po;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 刘巍 on 2017/12/17.
 */
@Setter
@Getter
@ToString
public class User implements Serializable {

    private int id;
    private String name;
    private String address;
    private Date birthday;

}
