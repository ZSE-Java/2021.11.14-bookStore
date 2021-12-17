package pl.edu.zse.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.zse.book.store.database.IBookDAO;
import pl.edu.zse.book.store.model.Book;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.zse.book.store.model.Book");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.zse.book.store.model.Book WHERE id = :id");
        query.setParameter("id", id);
        try {
            Book book = query.getSingleResult();
            session.close();
            return Optional.of(book);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateBook(Book book) {
        Transaction tx = null;
        try {
            Session session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        }

    }
}
