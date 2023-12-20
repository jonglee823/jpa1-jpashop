package jpabook.jpashop.request;

import jpabook.jpashop.domain.item.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public BookRequest() {
    }

    @Builder
    public BookRequest(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public BookRequest(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.stockQuantity = book.getStockQuantity();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
    }
}
