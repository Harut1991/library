package mainpackage.service;

import mainpackage.model.Book;

import java.util.List;

/**
 * Created by spire on 2/3/19.
 */
public interface IBookService {
    Book save(Book book);
    Book getOne(long id);
    List<Book> getAll();
}
