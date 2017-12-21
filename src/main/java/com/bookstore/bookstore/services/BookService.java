package com.bookstore.bookstore.services;

import com.bookstore.bookstore.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findOne(Long id);

    Book save(Book book);

    List<Book> blurrySearch(String title);

    void removeOne(Long id);
}
