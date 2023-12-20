package jpabook.jpashop.request;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String name;
    private OrderStatus orderStatus;

}