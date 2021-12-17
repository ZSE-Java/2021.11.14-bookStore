package pl.edu.zse.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.zse.book.store.database.IOrderDAO;
import pl.edu.zse.book.store.model.Order;

import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        Transaction tx = null;
        try {
            Session session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.zse.book.store.model.Order WHERE user_id = :id");
        query.setParameter("id", id);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }
}
