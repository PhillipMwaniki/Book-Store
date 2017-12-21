package com.bookstore.bookstore.services.Impl;

import com.bookstore.bookstore.domain.Book;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        List<Book> bookList = bookRepository.findAll();

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }
        return activeBookList;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> blurrySearch(String title) {
        List<Book> bookList = bookRepository.findByTitleContaining(title);

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }
        return activeBookList;
    }

    @Override
    public void removeOne(Long id) {
        bookRepository.delete(id);
    }
}
