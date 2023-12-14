package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name= "delevery_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", cascade = ALL)
    private Order order;

    @Embedded
    private Address address;
}
