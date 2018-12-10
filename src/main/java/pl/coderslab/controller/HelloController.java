package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.model.Book;
import pl.coderslab.model.MemoryBookService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HelloController {
@Autowired
    MemoryBookService memoryBookService;

    @RequestMapping("/")
    public String helloworld(Model model) {

        LocalDateTime localDateTime = LocalDateTime.now();
        int localTime = localDateTime.getHour();
        if (localTime >=8 &&localTime<20){
            model.addAttribute("color", "black");
            model.addAttribute("backgroundColor", "white");
        }else {
            model.addAttribute("backgroundColor", "black");
            model.addAttribute("color", "white");
        }
        return "home";
    }

    @RequestMapping("/books")
    public String getBooksById(Model model) {
        List<Book> list = memoryBookService.getList();
        model.addAttribute("list",list);
        return "books";
    }

    @RequestMapping("/books/delete")
    public String delBooksById(Model model) {
        List<Book> list = memoryBookService.getList();
        model.addAttribute("list",list);
        return "delete";
    }

}
