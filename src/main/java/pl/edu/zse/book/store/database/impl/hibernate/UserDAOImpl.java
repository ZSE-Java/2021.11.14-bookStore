package pl.edu.zse.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.zse.book.store.database.IUserDAO;
import pl.edu.zse.book.store.model.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.zse.book.store.model.User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.zse.book.store.model.User WHERE login = :login");
        query.setParameter("login", login);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }

    }

    @Override
    public void addUser(User user) {
        Transaction tx = null;
        try {
            Session session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.zse.book.store.model.User WHERE id = :id");
        query.setParameter("id", id);
        try {
            User user = query.getSingleResult();
            session.close();
            return user;
        } catch (NoResultException e) {
            session.close();
            return null;
        }
    }
}
