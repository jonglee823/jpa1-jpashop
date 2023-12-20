package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item){
        // merge사용시 null로 입력된 객체는 Null로 업데이트됨..
        // 따라서 실무에서는 merge 보다는 업데이트 가능한 부분만 변경
//        if(null == item.getId()){
//            em.persist(item);
//        }else{
//            em.merge(item);
//        }

        em.persist(item);

        return item.getId();
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
