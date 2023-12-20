package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;             //주문 가격

    private int count;                  //주문 수량

    @Builder
    public OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
        item.removeStock(count);
    }

    /**
     * 주문 취소
     */
    public void cancel(){
        getItem().addStock(count);
    }

    /**
     * 주문 상품 전체 가격 조회
     */
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }
}
