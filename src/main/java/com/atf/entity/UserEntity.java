package com.atf.entity;

import lombok.Data;

/**
 * @ClassName: UserEntity
 * @description : user user 对应的Entity
 * @author      杨梁
 * @version     1.0  
 * @date        2018-05-21
 */
@Data
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
    // 
    private Integer id;
    // 
    private String username;
    // 
    private String password;
    // 
    private String reallyname;
    // 
    private String email;
    // 
    private Integer role;
    //
    private String roleCn;
    //
    private String dept;
    // 
    private String tel;
    // 
    private String phone;
    // 预留验证信息
    private String verifyInfo;
    // 
    private java.util.Date lastLoginDate;
    // 
    private String lastLoginIp;
    // 
    private Integer status;
    // 创建时间
    private java.util.Date createTime;
    // 修改者
    private Integer modifierId;
    // 修改时间
    private java.sql.Timestamp modifiedTime;
    // 创建者
    private Integer creatorId;
}