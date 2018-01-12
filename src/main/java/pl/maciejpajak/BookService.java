package pl.maciejpajak;

import java.util.List;

public interface BookService {
    
    public List<Book> getList();
    public Book getById(long id);
    public void delete(long id);
    public void add(Book b);
    public void update(Book b);
    
}
