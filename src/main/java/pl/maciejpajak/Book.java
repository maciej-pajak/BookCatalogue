package pl.maciejpajak;

public class Book {
    
    public enum Columns {
        ID("id"), 
        ISBN("isbn"), 
        TITLE("title"), 
        AUTHOR("author_id"), 
        PUBLISHER("publisher"), 
        TYPE("type");
        
        private String name;
        
        private Columns(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    private long id;
    private String isbn;
    private String title;
    private long authorId;
    private String publisher;
    private String type;
    
    public Book() {
        
    }
    
    public Book(long id, String isbn, String title, long authorId, String publisher, String type) {
        super();
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
        this.publisher = publisher;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor() {
        return authorId;
    }

    public void setAuthor(long authorId) {
        this.authorId = authorId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
