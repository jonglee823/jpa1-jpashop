package jpabook.jpashop.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.request.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Long save(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    /**
     *
     * @param orderSearch
     * @return
     */
    public List<Order> findAll(OrderSearch orderSearch){
        return em.createQuery("select o from Order o join o.member m" +
                " where o.status = :status" +
                " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getName())
                .setMaxResults(1000)
                .getResultList();
    }
}
