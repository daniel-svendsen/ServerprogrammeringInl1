package se.yrgo.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;

public class HarnessTest {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // Skapa tre böcker
        Book book1 = new Book("184984", "The Great Gatsby");
        Book book2 = new Book("1894984", "To Kill a Mockingbird");
        Book book3 = new Book("18798", "1984");

        // Skapa en författare och lägg till böckerna
        Author author = new Author("F. Scott Fitzgerald");
        author.addBook(book1);
        author.addBook(book2);
        author.addBook(book3);

        // Spara författaren och böckerna i databasen
        session.save(author);
        session.save(book1);
        session.save(book2);
        session.save(book3);

        // Hämta författaren med id
        Author author2 = session.get(Author.class, 9);

        // Hämta alla böcker som tillhör författaren
        if (author2 != null) {
            for (Book book : author2.getBookCollection()) {
                System.out.println("Book Title: " + book.getTitle());
            }
        } else {
            System.out.println("Author not found!");
        }

        tx.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
