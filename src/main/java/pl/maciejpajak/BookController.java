package pl.maciejpajak;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L,"9788324631766","Thinking in Java", 1,"Helion","programming");
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<Book> showAll(Model model,HttpServletRequest request) {
        return bookService.getList();
    }
    
    // TODO response status created
    @PostMapping("/")
    public void add(@RequestBody Book book) {
        bookService.add(book);
    }
    
    @GetMapping("/{id}")
    public Book getInfo(@PathVariable long id) {
        return bookService.getById(id);
    }
    
    @PutMapping("/{id}")
    public void edit(@RequestBody Book book, @PathVariable long id) {
        if (id == book.getId()) {
            bookService.update(book);
        }
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }
}
