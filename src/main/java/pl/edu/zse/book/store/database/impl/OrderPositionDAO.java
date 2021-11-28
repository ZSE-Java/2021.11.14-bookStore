package pl.edu.zse.book.store.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.zse.book.store.database.IBookDAO;
import pl.edu.zse.book.store.database.IOrderPositionDAO;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.model.OrderPosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderPositionDAO implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    IBookDAO bookDAO;


    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition VALUES (NULL, ?, ?, ?)";

            PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderPosition.getBook().getId());
            ps.setInt(2, orderPosition.getQuantity());
            ps.setInt(3, orderId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderPosition> getAllPositionsByOrderId(int orderId) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderPosition orderPosition = new OrderPosition();
                orderPosition.setId(rs.getInt("id"));
                orderPosition.setQuantity(rs.getInt("quantity"));

                int bookId = rs.getInt("book_id");

                Book book = this.bookDAO.getBookById(bookId).get();

                orderPosition.setBook(book);

                result.add(orderPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
