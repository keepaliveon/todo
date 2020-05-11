package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    //用户ID即微信OpenId
    @Id
    private String openId;
    //昵称
    private String nickName;
    //头像url
    private String avatarUrl;
    //注册时间
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
    //邮箱地址
    private String email;
    //目录
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Catalog> catalogs = new ArrayList<>();
}
