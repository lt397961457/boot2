package com.yly.testboot2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "table_user")
public class UserEntity implements Serializable {
    @Id()
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column
    private Integer age;
    private String addr;
    //数据库列名会自动转换成phone_num
    private String phoneNum;
}
