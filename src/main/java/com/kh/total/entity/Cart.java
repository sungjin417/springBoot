package com.kh.total.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter@Setter@ToString
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String CartName;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member; // member 객체에 대한 참조 변수


}
