package pl.edu.zse.book.store.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.zse.book.store.database.IOrderDAO;
import pl.edu.zse.book.store.database.IOrderPositionDAO;
import pl.edu.zse.book.store.database.IUserDAO;
import pl.edu.zse.book.store.model.Order;
import pl.edu.zse.book.store.model.OrderPosition;
import pl.edu.zse.book.store.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Override
    public void addOrder(Order order) {
        try {
            String sql = "INSERT INTO torder VALUES (NULL, ?, ?)";

            PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUser().getId());
            statement.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateTime(LocalDateTime.ofInstant(
                        rs.getTimestamp("dateTime").toInstant(),
                        ZoneOffset.ofHours(0)));

                int userId = rs.getInt("user_id");

                User user = this.userDAO.getUserById(userId);

                order.setUser(user);

                List<OrderPosition> orderPositions =
                        this.orderPositionDAO.getAllPositionsByOrderId(order.getId());
                order.setOrderPositions(new HashSet<>(orderPositions));

                result.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
