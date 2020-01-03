package com.me.sso.entity;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Data
@Table(name = "me_user")
public class SsoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String sex;
    @Length(max = 10, min = 1)
    private String userName;
    private String pwd;
    @Email
    private String email;
    @Length(max = 11,min = 11)
    private String phone;
}
