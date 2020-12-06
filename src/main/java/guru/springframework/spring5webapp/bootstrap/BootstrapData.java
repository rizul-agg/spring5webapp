package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Jon Thompson");
        publisher.setCity("Boston");
        publisher.setCountry("USA");

        publisherRepository.save(publisher);

        System.out.println("Publisher set as: "+ publisher.getName());

        Book sts = new Book("Spring Framework 5.0","9883928");
        Author rod = new Author("Rod","Tucker");
        sts.getAuthors().add(rod);
        rod.getBooks().add(sts);

        publisher.getBooks().add(sts);
        sts.setPublisher(publisher);

        authorRepository.save(rod);
        bookRepository.save(sts);
        publisherRepository.save(publisher);

        System.out.println("Number of books: "+ bookRepository.count());
        System.out.println("Publisher No of books: "+ publisher.getBooks().size());
    }
}
