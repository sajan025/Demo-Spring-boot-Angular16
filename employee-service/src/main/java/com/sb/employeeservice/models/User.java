package com.sb.employeeservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements Serializable {
    public static final long uId=1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk_id")
    private Integer userPkId;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "is_deleted",columnDefinition ="tinyint(1) default 0" )
    private boolean deleted;
    @Column(name = "type")
    private String type;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> appUserRoles;


}
