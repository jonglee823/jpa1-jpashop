package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.jpashop.request.BookRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
@NoArgsConstructor
@SuperBuilder
public class Book extends Item{

    private String author;

    private String isbn;

    public Book(BookRequest bookRequest){
        super.setId(bookRequest.getId());
        super.setName(bookRequest.getName());
        super.setPrice(bookRequest.getPrice());
        super.setStockQuantity(bookRequest.getStockQuantity());
        this.author = bookRequest.getAuthor();
        this.isbn = bookRequest.getIsbn();
    }
}
