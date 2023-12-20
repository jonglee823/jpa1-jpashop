package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.request.BookRequest;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value="/items/new")
    public String createForm(Model model){
        model.addAttribute("bookRequest", new BookRequest());
        return "items/createItemForm";
    }

    @PostMapping(value="/items/new")
    public String create(BookRequest request){
        Book book = new Book(request);

        itemService.save(book);
        return "redirect:/items";
    }

    @GetMapping(value="/items")
    public String list(Model model){
        List<Item> items= itemService.findAll();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value="/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);
        BookRequest request = new BookRequest(item);
        model.addAttribute("bookRequest", request);
        return "items/updateItemForm";
    }

    @PostMapping(value="/items/{itemId}/edit")
    public String update(@PathVariable Long itemId, BookRequest bookRequest){
        itemService.updateItem(itemId, bookRequest.getName(), bookRequest.getPrice(), bookRequest.getStockQuantity());

        return "redirect:/items";
    }
}
