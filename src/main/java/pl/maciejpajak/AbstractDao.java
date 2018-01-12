package pl.maciejpajak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pl.maciejpajak.model.objects.DataType;

public abstract class AbstractDao<T extends DataType<T>> {
    
    private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
    
    protected abstract T newItemFromRs(ResultSet rs) throws SQLException;
    
    protected abstract String getLoadAllQuery();
    protected abstract String getCountQuery();
    protected abstract String getLoadByIdQuery();
    
    protected abstract PreparedStatement saveNewStatement(Connection con, T item) throws SQLException;
    protected abstract PreparedStatement updateExistingStatement(Connection con, T item) throws SQLException;
    protected abstract PreparedStatement deleteStatement(Connection con, T item) throws SQLException;
    
    public List<T> loadAll() {
        List<T> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            try ( ResultSet rs = con.prepareStatement( getLoadAllQuery() ).executeQuery() ) {
                while ( rs.next() ) {
                    list.add( newItemFromRs(rs) );
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

//    protected abstract String getLoadWithLimitFormatQuery();
    
//    public T[] loadSortedWithLimit(ColumnsEnumInterface sortColumnName, DaoInterface.SortType sortType, int limit, int offset) {
//        List<T> list = new ArrayList<>();
//        String sqlFormat = getLoadWithLimitFormatQuery();
//        try (Connection con = DbUtil.getConn()) {
//            try ( PreparedStatement ps = con.prepareStatement(String.format(sqlFormat, sortColumnName.getName(), sortType.getName())) ) {
//                ps.setInt(1, limit);
//                ps.setInt(2, offset);
//                try (ResultSet rs = ps.executeQuery()) {
//                    while ( rs.next() ) {
//                        list.add( newItemFromRs(rs) );
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("error: " + e.getMessage());
//        }
//        return list.toArray( getNewArray(list.size()) );
//    }
    
    public int getCount() { 
        int count = -1;
        try (Connection con = DbUtil.getConnection()) {
            try ( ResultSet rs = con.prepareStatement( getCountQuery() ).executeQuery() ) {
                while ( rs.next() ) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }
    
    public T loadById(int id) {
        T res = null;
        try (Connection con = DbUtil.getConnection()) {
            try ( PreparedStatement ps = con.prepareStatement( getLoadByIdQuery() ) ) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if ( rs.next() ) {
                        res = newItemFromRs(rs);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return res;
    }
    
    public void saveToDb(T item) {
        try (Connection con = DbUtil.getConnection()) {
            if ( item.getId() == 0 ) {
                saveNewToDb(con, item);
            } else {
                updateExistingInDb(con, item);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    
    public void add(T item) throws SQLException {
        try (Connection con = DbUtil.getConnection()) {
            try ( PreparedStatement ps = saveNewStatement(con, item) ) {
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if ( rs.next() ) {
                        item.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        
    }
    
    private void update(T item) throws SQLException {
        try (Connection con = DbUtil.getConnection()) {
            try ( PreparedStatement ps = updateExistingStatement(con, item) ) {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    
    public void delete(T item) {
        try (Connection con = DbUtil.getConnection()) {
            if ( item.getId() != 0 ) {
                try (PreparedStatement ps = deleteStatement(con, item) ) {
                    ps.executeUpdate();
                }
                item.setId(0);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
 
}

