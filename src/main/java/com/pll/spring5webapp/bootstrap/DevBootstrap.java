package com.pll.spring5webapp.bootstrap;

import com.pll.spring5webapp.model.Book;
import com.pll.spring5webapp.model.Author;
import com.pll.spring5webapp.model.Publisher;
import com.pll.spring5webapp.repositories.AuthorRepository;
import com.pll.spring5webapp.repositories.BookRepository;
import com.pll.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pixel = new Publisher("Pixel", "Montreal, qc");
        Book ddd = new Book("Domain Driven Design", "1234", pixel);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pixel);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher disney = new Publisher("Disney", "Florida, USA");
        Book noEJB = new Book("J2EE Development without EJB", "23444", disney);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(disney);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}