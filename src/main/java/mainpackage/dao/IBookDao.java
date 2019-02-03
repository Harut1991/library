package mainpackage.dao;

import mainpackage.model.Book;

import java.util.List;

/**
 * Created by spire on 2/3/19.
 */
public interface IBookDao {
    Book save(Book book);
    Book getOne(long id);
    List<Book> getAll();
}
