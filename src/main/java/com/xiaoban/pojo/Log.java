package com.xiaoban.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/8/23 6:57 PM
 */
@Data
public class Log implements Serializable {
    private Integer id;
    private String uuid;
    private String name;
    private String info;
    private Date created;
}
