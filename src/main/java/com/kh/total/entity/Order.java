package com.kh.total.entity;

import com.kh.total.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블, 컬럼 이름이 키워드가 아닌지 확인(종종 오류)
@Getter@Setter

public class Order {
    @Id
    @GeneratedValue // 아무것도 안 넣으면 auto
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING) //ORDINAL 은 순서
    private OrderStatus orderStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>(); // 참조를 하기 위한 허상

}
