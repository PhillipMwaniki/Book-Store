package com.bookstore.bookstore.resource;

import com.bookstore.bookstore.domain.Book;
import com.bookstore.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookResource {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/bookList")
    public List<Book> getBookList() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/add/image", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) {

        try {
//            Book book = bookService.findOne(id);
            System.out.println("Request ID: " + id);
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            String fileName = id+".png";

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/static/image/books/"+fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity("Upload success", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload failed", HttpStatus.BAD_REQUEST);
        }
    }
}
