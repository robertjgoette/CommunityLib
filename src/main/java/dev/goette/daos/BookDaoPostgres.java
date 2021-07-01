package dev.goette.daos;

import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;
import dev.goette.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoPostgres implements BookDao{
    @Override
    public Book createBook(Book book) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert  into book (title,author,available,quality,return_date) values(?, ?, ?, ?, ?)returning book_id";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setInt(4, book.getQuality());
            ps.setLong(5, book.getReturnDate());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();// Result set is a curser that points to the records returned. It intally poits to nothing
            // you have .next() to get it to point to the first record
            int key = rs.getInt(1);
            book.setBookId(key);
            return book;

        } catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Book getBookById(int id) { // When you add a throws to the method siginature. That means you have to up date youtr interface
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from book where book_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setQuality(rs.getInt("quality"));
            book.setReturnDate(rs.getInt("return_date"));
            book.setAvailable(rs.getBoolean("available"));

            return book;

        } catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            throw new ResourceNotFound("Resource of " + id + " not found");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from book";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Book> books = new ArrayList<>();

            while(rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setQuality(rs.getInt("quality"));
                book.setReturnDate(rs.getInt("return_date"));
                book.setAvailable(rs.getBoolean("available"));
                books.add(book);
            }
            return books;

        } catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update book set title = ?,author = ?,available = ?,quality = ?, return_date = ? where book_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setInt(4, book.getQuality());
            ps.setLong(5, book.getReturnDate());
            ps.setLong(6, book.getBookId());

            ps.execute();
            return book;

        }catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteBookById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "delete from book where book_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            return true;

        }catch (SQLException sqlException){
            //You are required to check
            sqlException.printStackTrace();
            return false;
        }
    }
}
