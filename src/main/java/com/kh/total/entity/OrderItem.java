package com.kh.total.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
// @Table : 안 넣어도 됨 (이름을 바꿔줘야 하는 경우에 사용)
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;
    private int count;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;


}
