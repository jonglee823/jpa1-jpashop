package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@SuperBuilder
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name ="item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    public Item(String name, int price, int stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    @ManyToMany(mappedBy = "items")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int stockQuantity){
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0){
            throw  new NotEnoughStockException("재고는 0보다 작을 수 없습니다.");
        }
        this.stockQuantity = restStock;
    }
}