package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length=100)
    private String title;
    @Lob
    private String content;
    @ColumnDefault("0")
    private int count;//조회수
    @ManyToOne(fetch = FetchType.EAGER) // many=many, user=one
    @JoinColumn(name = "userId")
    private User user; // db는 오브젝트를 저장할수없다. fk,자바는 저장가능
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)//mappedby 연관관계의 주인이아니다.fk아님, db에 칼럼만들지마!
    private List<Reply> reply;
    @CreationTimestamp
    private Timestamp createDate;
}
