package at.htl.test3.repository;

import at.htl.test3.model.Book;
import at.htl.test3.model.LendStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookRepository {
    Connection conn;

    public BookRepository() {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db;create=true","app","app");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks(){
        List<Book> result = new LinkedList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from book");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Book b = new Book(
                        rs.getString("ISBN"),
                        rs.getString("TITLE"),
                        LendStatus.LENT.fromLetter(rs.getString("STATUS").charAt(0))
                         //rs.getString("STATUS").equals("A") ?
                         //        LendStatus.AVAILABLE : LendStatus.LENT;
                );
                result.add(b);
            }
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //result.add(new Book("123","TestBook1", LendStatus.AVAILABLE));
        //result.add(new Book("456","TestBook2", LendStatus.LENT));
        return result;
    }

    public void deleteBook(Book b) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from book where isbn=?");
            pstmt.setString(1,b.getIsbn());
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Book b){
        try {
            PreparedStatement pstmt = conn.prepareStatement("update book set title=?,status=? where isbn=?");
            pstmt.setString(1,b.getTitle());
            pstmt.setString(2,""+b.getStatus().letter);
            pstmt.setString(3,b.getIsbn());
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
