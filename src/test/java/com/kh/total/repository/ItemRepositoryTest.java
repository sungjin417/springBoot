package com.kh.total.repository;

import com.kh.total.constant.ItemSellStatus;
import com.kh.total.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for (int i = 0; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품면 조회 테스트")
    public void findByItemNmTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");
        for (Item e : itemList) System.out.println(e.getItemDetail());
    }

    @Test
    @DisplayName("상품명 또는 상품상세설명 조회")
    public void findByItemNmOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명4");
        for (Item e : itemList) System.out.println(e);
    }

    @Test
    @DisplayName("상품명과 가격 조회 테스트")
    public void findByItemNmAndPriceTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmAndPrice("테스트 상품", 10000);
        for (Item e : itemList) System.out.println(e.getItemDetail());
    }
    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item e : itemList) {
            System.out.println(e.toString());
        }
    }
    @Test
    @DisplayName("가격 정렬 테스트")
    public void findByPriceLessThanDescTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item e : itemList) System.out.println(e.toString());
    }
    @Test
    @DisplayName("Between 검색 테스트")
    public void findByPriceBetweenTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceBetween(10000,10007);
        for (Item e : itemList) System.out.println(e.toString());
    }
    @Test
    @DisplayName("문자열 검색 테스트")
    public void findByItemNmContainingTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmContaining("상품3");
        for (Item e : itemList) System.out.println(e.toString());
    }
    @Test
    @DisplayName("@Query를 이용한 검색 기능 구현")
    public void findByItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("설명6");
        for(Item e : itemList) log.info(e.toString());
    }
    @Test
    @DisplayName("@Query를 이용한 검색 기능 구현")
    public void findByItemDetailTest2() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetailByNative("설명6");
        for(Item e : itemList) log.info(e.toString());
    }
}