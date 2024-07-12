import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        //Author ekleme
        Author author = new Author();
        author.setName ("Orhan Pamuk");
        author.setBirthdate(LocalDate.of(1959, 7, 12));
        author.setCountry("Türkiye");
        entityManager.persist(author);

        //Category ekleme
        Category category = new Category();
        category.setName("Tarih");
        category.setDescription("Tarih türündeki kitaplar");
        entityManager.persist(category);

        //Publisher ekleme
        Publisher publisher = new Publisher();
        publisher.setName("Patika Kitapevi");
        publisher.setEstablishmentYear(2007);
        publisher.setAddress("İstanbul, TR");
        entityManager.persist(publisher);

        //Book ekleme
        Book book = new Book();
        book.setName("Şu Çılgın Türkler");
        book.setPublicationYear(2002);
        book.setStock(80);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(List.of(category));
        entityManager.persist(book);

        //BookBorrowing Ekleme
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBorrowerName("Ayşe Yılmaz");
        bookBorrowing.setBorrowingDate(LocalDate.now());
        bookBorrowing.setBook(book);
        entityManager.persist(bookBorrowing);

        transaction.commit();

    }
}
