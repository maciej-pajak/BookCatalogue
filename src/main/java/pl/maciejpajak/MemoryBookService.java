package pl.maciejpajak;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MemoryBookService implements BookService {
    private List<Book> list;    //TODO change to map

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", 2L, "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.", 3L, "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", 4L, "Helion",
                "programming"));
    }

    public List<Book> getList() {
        return list;
    }
    
    public Book getById(long id) {
        Optional<Book> book = list.stream().filter(e -> e.getId() == id).findFirst();
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public void delete(long id) {
        list = list.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
    }

    @Override
    public void add(Book b) {
        list.add(b);
    }

    @Override
    public void update(Book b) {
        // TODO
    }
}
