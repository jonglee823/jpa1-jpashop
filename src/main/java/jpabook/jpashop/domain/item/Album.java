package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
@SuperBuilder
@NoArgsConstructor
public class Album extends Item{

    private String artist;

    private String etc;
}
