package mainpackage.repository;

import mainpackage.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by spire on 2/3/19.
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
