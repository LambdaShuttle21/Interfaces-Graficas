package es.pedro.address.view;

import es.pedro.address.MainApp;
import es.pedro.address.model.Person;
import es.pedro.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controlador de la vista principal de personas.
 */
public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    /**
     * Configura las columnas y reacciona a los cambios de seleccion.
     */
    @FXML
    private void initialize() {
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Recibe la referencia a la aplicacion principal.
     *
     * @param mainApp aplicacion principal
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData());
        if (!personTable.getItems().isEmpty()) {
            personTable.getSelectionModel().selectFirst();
        }
    }

    private void showPersonDetails(Person person) {
        if (person == null) {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
            return;
        }

        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());
        birthdayLabel.setText(DateUtil.format(person.getBirthday()));
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Sin seleccion");
        alert.setHeaderText("No hay ninguna persona seleccionada");
        alert.setContentText("Selecciona una persona de la tabla.");
        alert.showAndWait();
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        if (mainApp.showPersonEditDialog(tempPerson)) {
            mainApp.getPersonData().add(tempPerson);
            personTable.getSelectionModel().select(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            if (mainApp.showPersonEditDialog(selectedPerson)) {
                showPersonDetails(selectedPerson);
            }
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Sin seleccion");
        alert.setHeaderText("No hay ninguna persona seleccionada");
        alert.setContentText("Selecciona una persona de la tabla.");
        alert.showAndWait();
    }
}
