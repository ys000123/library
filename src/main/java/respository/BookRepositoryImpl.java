package respository;

import config.DatabaseConnector;
import domain.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    private static final String TABLE = "books";

    @Override
    public void save(Book book) {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DatabaseConnector.getConnection();
            String sql = String.format("INSERT INTO %s(title, author, ISBN, quantity) VALUES(?, ?, ?, ?)", TABLE);
            pst = con.prepareStatement(sql);
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getISBN());
            pst.setInt(4, book.getQuantity());
            pst.executeUpdate();
        } catch (SQLException ignored) {
        } finally {
            close(con, pst, null);
        }
    }

    @Override
    public boolean existByIsbn(String isbn) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnector.getConnection();
            String sql = String.format("SELECT * FROM %s WHERE isbn = ?", TABLE);
            pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ignored){
            return false;
        } finally {
            close(con, pst, rs);
        }
    }

    @Override
    public void release(Book book) {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DatabaseConnector.getConnection();
            String sql = String.format("UPDATE %s SET quantity = ? WHERE book_id = ?", TABLE);
            pst = con.prepareStatement(sql);
            pst.setInt(1, book.getQuantity());
            pst.setLong(2, book.getBookId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pst, null);
        }
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        Connection con = null;
        PreparedStatement  stat = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnector.getConnection();
            var sql = String.format("SELECT * FROM %s WHERE book_id = ?", TABLE);
            stat = con.prepareStatement(sql);
            stat.setLong(1, bookId);
            rs = stat.executeQuery();

            return rs.next() ? Optional.of(new Book(
                    rs.getLong("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("ISBN"),
                    rs.getInt("quantity")
            )) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, stat, rs);
        }
    }

    @Override
    public List<Book> findAll() {
        Connection con = null;
        PreparedStatement  stat = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnector.getConnection();
            var sql = String.format("SELECT * from %s", TABLE);
            stat = con.prepareStatement(sql);
            rs = stat.executeQuery();

            List<Book> response = new LinkedList<>();
            while (rs.next()) {
                response.add(new Book(
                        rs.getLong("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("ISBN"),
                        rs.getInt("quantity")
                ));
            }
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, stat, rs);
        }
    }

    @Override
    public List<Book> findAllByCondition(String author) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnector.getConnection();
            var sql = String.format("SELECT * FROM %s WHERE author LIKE ?", TABLE);
            pst = con.prepareStatement(sql);
            pst.setString(1,  "%" + author + "%");
            rs = pst.executeQuery();

            List<Book> response = new LinkedList<>();
            while (rs.next()) {
                response.add(new Book(
                        rs.getLong("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("ISBN"),
                        rs.getInt("quantity")
                ));
            }
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pst, rs);
        }
    }

    private static void close(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (pst != null)
                pst.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
