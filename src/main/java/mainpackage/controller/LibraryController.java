package mainpackage.controller;
import mainpackage.message.request.BookForm;
import mainpackage.model.Book;
import mainpackage.model.User;
import mainpackage.service.IBookService;
import mainpackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by spire on 2/3/19.
 */
@RestController
@RequestMapping("/api/")
public class LibraryController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> create(@RequestBody BookForm bookForm){
        Book book = new Book(bookForm.getAuthor(),bookForm.getName(), bookForm.getCount());
        return new ResponseEntity<Book>(this.bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Book>> get(){
        List<Book> books = this.bookService.getAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @PutMapping("books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> edit(@PathVariable("id") long id, @RequestBody BookForm bookForm){
        Book book = this.bookService.getOne(id);
        System.out.println(book.toString());
        book.setCount(bookForm.getCount());
        return new ResponseEntity<Book>(this.bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("books/getBookForUser/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getBookForUser(@PathVariable("id") long id){
        User currentuser = this.userService.currentUser();
        Book book = this.bookService.getOne(id);
        if (book.getCount() == 0 || currentuser.getBooks().contains(book)){
            return new ResponseEntity<String>("you can't get this book", HttpStatus.BAD_REQUEST);
        }
        currentuser.addBook(book);
        if(this.userService.save(currentuser) != null){
            book.setCount((short) (book.getCount() - 1));
            this.bookService.save(book);
        }
        return new ResponseEntity<String>("done", HttpStatus.OK);
    }

    @DeleteMapping("books/returnBook/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> returnBook(@PathVariable("id") long id){
        User currentUser = this.userService.currentUser();
        Book book = this.bookService.getOne(id);
        if (!currentUser.getBooks().contains(book)){
            return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
        }
        currentUser.deleteBook(book);
        if (this.userService.save(currentUser) != null){
            book.setCount((short) (book.getCount() + 1));
            this.bookService.save(book);
        }
        return new ResponseEntity<String>("done", HttpStatus.OK);
    }

}
