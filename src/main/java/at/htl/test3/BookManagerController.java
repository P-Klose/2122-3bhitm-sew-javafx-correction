package at.htl.test3;

import at.htl.test3.model.Book;
import at.htl.test3.model.LendStatus;
import at.htl.test3.repository.BookRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BookManagerController {


    @FXML
    private Button btNew;

    @FXML
    private Button btSave;

    @FXML
    private RadioButton rbAvalable;

    @FXML
    private RadioButton rbRent;

    @FXML
    private TextField tfIsbn;

    @FXML
    private TextField tfTitle;

    @FXML
    private ToggleGroup tgStatus;

    @FXML
    private TreeView<Object> tvTree;

    @FXML
    void onNew(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
        repo.update(model);
        refreshBooks();
    }


    BookRepository repo;
    TreeItem available;
    TreeItem lent;

    Book model;

    @FXML
    public void initialize() {
        repo = new BookRepository();
        model= new Book("","",LendStatus.AVAILABLE);

        tfIsbn.textProperty().bindBidirectional(model.isbnProperty());
        tfTitle.textProperty().bindBidirectional(model.titleProperty());
            ChangeListener listener = new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                            if (observableValue == tgStatus.selectedToggleProperty()) {
                                    model.statusProperty().set(rbAvalable.isSelected() ? LendStatus.AVAILABLE : LendStatus.LENT);
                            } else {
                                    if (model.getStatus() == LendStatus.AVAILABLE) {
                                            rbAvalable.setSelected(true);
                                    } else {
                                            rbRent.setSelected(true);
                                    }
                            }
                    }
            };
            model.statusProperty().addListener(listener);
            tgStatus.selectedToggleProperty().addListener(listener);

        TreeItem root = new TreeItem();
        tvTree.setRoot(root);
        tvTree.setShowRoot(false);

        available = new TreeItem("Verfügbar");
        available.setExpanded(true);
        lent = new TreeItem("Verliehen");
        lent.setExpanded(true);

        root.getChildren().addAll(available, lent);

        refreshBooks();

        ContextMenu ctMenu = new ContextMenu();
        MenuItem miDelete = new MenuItem("Delete");
        ctMenu.getItems().add(miDelete);
        tvTree.setContextMenu(ctMenu);
        miDelete.setOnAction(actionEvent -> deleteBook());
    }

    private void deleteBook() {
            Book book = (Book) tvTree.getSelectionModel().getSelectedItem().getValue();
            repo.deleteBook(book);
            refreshBooks();
    }

    public void refreshBooks() {

        available.getChildren().clear();
        lent.getChildren().clear();

        for (Book book : repo.getAllBooks()) {
            TreeItem temp = new TreeItem();
            temp.setValue(book);
            if (book.getStatus() == LendStatus.AVAILABLE) {
                available.getChildren().add(temp);
            } else {
                lent.getChildren().add(temp);
            }
        }

    }

    @FXML
    void onTreeClick(MouseEvent event) {
        if(event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)){
            TreeItem item = tvTree.getSelectionModel().getSelectedItem();
            Object o = item.getValue();
            if(o!= null && o instanceof Book){
                this.model.init((Book)o);
            }

        }
    }

}

  /*
        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (observableValue == grpStatus.selectedToggleProperty()) {
                    book.statusProperty().set(rbAvailable.isSelected() ? LendStatus.AVAILABLE : LendStatus.LENT);
                } else {
                    if (book.getStatus() == LendStatus.AVAILABLE) {
                        rbAvailable.setSelected(true);
                    } else {
                        rbLent.setSelected(true);
                    }
                }
            }
        };
*/
