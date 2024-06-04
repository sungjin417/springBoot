package com.kh.total.repository;

import com.kh.total.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm); // select item_nm from item where item_nm = 'xxx'
    List<Item> findByItemNmAndPrice(String itemNm, int price);
    // OR 조건 처리 하기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail); // OR 조건 쿼리문
    // LessThan : 보다 작다 라는 의미
    List<Item> findByPriceLessThan(int price);
    // OrderBy :
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);
    // Between
    List<Item> findByPriceBetween(int minPrice, int maxPrice);
    // 문자열 검색
    List<Item> findByItemNmContaining(String keyword);
}
