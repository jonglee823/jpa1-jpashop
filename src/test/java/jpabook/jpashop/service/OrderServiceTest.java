package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품주문")
    public void 상품주문() throws Exception{
        //회원 생성
        Member member = createMember();

        Book item = Book.builder()
                .name("book1")
                .price(3000)
                .stockQuantity(100)
                .isbn("1234")
                .build();
        itemRepository.save(item);

        int count = 100;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), count);

        Order order = orderService.findOne(orderId);

        //then
        Assertions.assertEquals(OrderStatus.ORDER, order.getStatus());
        Assertions.assertEquals(1, orderId);
        Assertions.assertEquals(order.getTotalPrice(), item.getPrice() * count);
        Assertions.assertEquals(item.getStockQuantity(), 0);
    }

    @Test
    @DisplayName("주문 취소")
    public void 주문취소(){
        Member member = createMember();
        Book item = Book.builder()
                .name("book1")
                .price(3000)
                .stockQuantity(100)
                .isbn("1234")
                .build();
        itemRepository.save(item);

        int count = 100;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), count);

        Order order = orderService.findOne(orderId);

        order.cancel();

        Assertions.assertEquals(order.getStatus() , OrderStatus.CANCEL);
        Assertions.assertEquals(count, item.getStockQuantity());
    }

    @Test
    @DisplayName("재고수량 초과 테스트")
    public void 재고수량초과테스트() throws Exception {
        Member member = createMember();
        Book item = Book.builder()
                .name("book1")
                .price(3000)
                .stockQuantity(1)
                .isbn("1234")
                .build();
        itemRepository.save(item);

        int count = 100;

        Assertions.assertThrows(NotEnoughStockException.class, ()-> orderService.order(member.getId(), item.getId(), count));

    }



    public Member createMember(){
        Address address = Address.builder()
                .city("SEOUL")
                .zipcode("1234")
                .street("seocho")
                .build();

        //given
        Member member = Member.builder()
                .name("JHLEE")
                .address(address)
                .build();
        memberRepository.save(member);
        return member;
    }
}