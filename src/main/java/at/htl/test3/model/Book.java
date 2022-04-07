package at.htl.test3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    final private StringProperty isbn = new SimpleStringProperty();
    final private StringProperty title = new SimpleStringProperty();
    final private ObjectProperty<LendStatus> status =
            new SimpleObjectProperty<>();

    public Book() {
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public Book(String isbn, String title, LendStatus status){
        this.isbn.setValue(isbn);
        this.title.setValue(title);
        this.status.setValue(status);
    }

    public void init(Book b){
        isbn.setValue(b.getIsbn());
        title.setValue(b.getTitle());
        status.setValue(b.getStatus());
    }

    public void reset(){
        isbn.setValue("");
        title.setValue("");
        status.setValue(LendStatus.AVAILABLE);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public LendStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<LendStatus> statusProperty() {
        return status;
    }

    public void setStatus(LendStatus status) {
        this.status.set(status);
    }
}
