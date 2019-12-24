package com.xiaoban.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: 用户类
 * @Author: xiaoban
 * @Date: 2019/8/22 5:05 PM
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String sid;
    private String key;
    private String dept;
    private String professional;
    private String sex;
    private String qq;
    private String photo;
    private String tel;
    private Integer roomId;
    private Integer seatId;
    private Integer state;
    private Integer group;
    private String sushe;
    private String birthday;
    private String addr;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
