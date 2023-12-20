package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles(value="test")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("insertNewItem")
    public void 새상품등록(){
        //give
        Book item = Book.builder()
                        .name("book1")
                        .price(3000)
                        .stockQuantity(1000)
                        .isbn("1234")
                        .build();

        Long saveId = itemService.save(item);

        //when
        Item findItem = itemService.findOne(saveId);

        //then
        Assertions.assertEquals(item, findItem);
    }

    @Test
    @DisplayName("MinusStockQuantity")
    public void 상품재고마이너스테스트(){
        //give
        Book item = Book.builder()
                .name("book1")
                .price(3000)
                .stockQuantity(100)
                .isbn("1234")
                .build();

        Long saveId = itemService.save(item);

        //EXPECTED
        Assertions.assertThrows(NotEnoughStockException.class, ()-> item.removeStock(120));







    }



}