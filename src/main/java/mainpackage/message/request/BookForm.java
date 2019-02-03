package mainpackage.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by spire on 2/3/19.
 */
public class BookForm {
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 100)
    private String author;

    @NotBlank
    @Size(min = 1, max = 3)
    private short count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }
}
