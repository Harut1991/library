package mainpackage.service;

import mainpackage.dao.IBookDao;
import mainpackage.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by spire on 2/3/19.
 */
@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao bookDao;

    @Override
    public Book save(Book book) {
        return this.bookDao.save(book);
    }

    @Override
    public Book getOne(long id) {
        return this.bookDao.getOne(id);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDao.getAll();
    }
}
