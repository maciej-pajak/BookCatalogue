package pl.maciejpajak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MemoryBookService implements BookService {
    private Map<Long, Book> map;
    private long currentId;

    public MemoryBookService() {
        map = new HashMap<>();
        currentId = 0;
        initializeList();
    }
    
    private void initializeList() {
        add(new Book("9788324631766", "Thinking in Java", "author1", "Helion", "programming"));
        add(new Book("9788324627738", "Rusz glowa, Java.", "author1", "Helion",
                "programming"));
        add(new Book("9780130819338", "Java 2. Podstawy", "author1", "Helion",
                "programming"));
    }

    public Map<Long, Book> getMap() {
        return map;
    }
    
    public List<Book> getList() {
        return map.values().stream().collect(Collectors.toList());
    }
    
    public Book getById(long id) {
        return map.get(id);
    }

    public void delete(long id) {
        map.remove(id);
    }

    public void add(Book b) {
        if (b != null) {
            b.setId(getNextId());
            map.put(b.getId(), b);
        }
    }

    public void update(Book b) {
        if (b != null) {
            Book find = map.get(b.getId());
            if (find != null) {
                map.put(find.getId(), b);
            }
        }
    }
    
    private long getNextId() {
        return currentId++;
    }
}
