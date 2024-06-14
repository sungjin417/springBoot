package com.kh.totalEx.entity;

import com.kh.totalEx.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@Entity // JPA Entity 클래스 임을 지정, Entity 클래스는 반드시 기본키를 가져야 함
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;           // 상품 코드
    @Column(nullable = false, length = 50)
    private String itemNm;     // 상품명
    @Column(nullable = false)
    private int price;         // 가격
    @Column(nullable = false)
    private int stockNumber;   // 재고 수량
    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
