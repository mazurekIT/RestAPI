package pl.coderslab.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryBookService {
    private Book book;
    private List<Book> list;


    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book("9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book("9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book("9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public Book getBookById(int id) {
        Book book = list.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .get();
        return book;
    }

    public void deleteBook(int id){
        Book book = list.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .get();
        list.remove(book);
    }


    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }


}