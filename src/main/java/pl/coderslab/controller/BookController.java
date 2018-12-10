package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.model.Book;
import pl.coderslab.model.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    MemoryBookService memoryBookService;

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        Book book = new Book("9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
        memoryBookService.getList().add(book);
        return book;
    }

    @RequestMapping("/booklist")
    public List<Book> listBooks() {
        return memoryBookService.getList();
    }



    @RequestMapping("/book/{id}")
    public Book getBookId(@PathVariable int id) {
        return memoryBookService.getBookById(id);
    }

    @RequestMapping("/del/{id}")
    public void delBookId(@PathVariable int id) {
        memoryBookService.deleteBook(id);
    }

}


