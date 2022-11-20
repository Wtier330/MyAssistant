
package com.example.myapplication.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WTL
 * @version 1.0.0
 * @date 2022/11/17
 * @describe 该类仅作为记事本的实体类
 */
@Data
@Builder
public class Note implements Serializable {
    private int id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;

    protected String getCreateTimeAsString(String parttern) {
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        return sdf.format(this.createTime);
    }

    public String getCreateTimeAsString() {
        return getCreateTimeAsString("HH:mm:ss");
    }

    public String getCreateDateAsString() {
        return getCreateTimeAsString("yyyy-MM-dd");
    }
}
