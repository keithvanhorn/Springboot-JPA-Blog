package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert 주입할때 널필드를 제외
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length=30,unique = true)
    private String username;
    @Column(nullable = false,length=100)
    private String password;
    @Column(nullable = false,length=50)
    private String email;
    @CreationTimestamp
    private Timestamp createDate;
//    @ColumnDefault("'user'")
    //db는 roletype 없음
    @Enumerated(EnumType.STRING)
    private RoleType role;


}
