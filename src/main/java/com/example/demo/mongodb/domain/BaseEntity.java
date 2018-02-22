package com.example.demo.mongodb.domain;

import com.example.demo.mongodb.support.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author lagon
 * @time 2017/2/17 17:19
 * @description 基础实体
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    protected long id;

}
