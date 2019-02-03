package mainpackage.dao;

import mainpackage.model.Book;
import mainpackage.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by spire on 2/3/19.
 */
@Transactional
@Repository
public class BookDao implements IBookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book getOne(long id) {
        return this.bookRepository.getOne(id);
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }
}
