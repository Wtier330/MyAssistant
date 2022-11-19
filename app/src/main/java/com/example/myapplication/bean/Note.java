
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
    private String id;
    private String title;
    private String content;
    private Date createTime;

    protected String getCreateTimeAsString(String parttern) {
        SimpleDateFormat s = new SimpleDateFormat(parttern);
        return s.format(this.createTime);
    }
    public String getCreateTimeAsString() {
        return getCreateTimeAsString("HH:mm");
    }
    public String getCreateDateAsString() {
        return getCreateTimeAsString("YYYY-MM-dd");
    }
}
