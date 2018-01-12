package pl.maciejpajak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


public class DbBookService implements BookService {

    private static final String LOAD_ALL_QUERY = "";
    
    @Override
    public List<Book> getList() {
        List<Book> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            try ( ResultSet rs = con.prepareStatement(LOAD_ALL_QUERY).executeQuery() ) {
                while ( rs.next() ) {
                    list.add( newItemFromRs(rs) );
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    @Override
    public Book getById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void add(Book b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Book b) {
        // TODO Auto-generated method stub
        
    }

}
