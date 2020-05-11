package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Todo {
    @Id
    private String id;
    //内容
    private String text;
    //备注
    @Column(name = "note")
    private String desc;
    //时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;
    //是否完成
    private Boolean done;
    @ManyToOne
    private Catalog catalog;

    @Transient
    private String category;

}
