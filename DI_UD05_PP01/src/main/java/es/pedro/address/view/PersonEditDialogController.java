package es.pedro.address.view;

import es.pedro.address.model.Person;
import es.pedro.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador del dialogo usado para editar personas.
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked;

    @FXML
    private void initialize() {
        birthdayField.setPromptText("dd.MM.yyyy");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Copia los datos de la persona a los campos.
     *
     * @param person persona que se desea editar
     */
    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (!isInputValid()) {
            return;
        }

        person.setFirstName(firstNameField.getText().trim());
        person.setLastName(lastNameField.getText().trim());
        person.setStreet(streetField.getText().trim());
        person.setPostalCode(Integer.parseInt(postalCodeField.getText().trim()));
        person.setCity(cityField.getText().trim());
        person.setBirthday(DateUtil.parse(birthdayField.getText().trim()));

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Comprueba que todos los campos contienen datos validos.
     *
     * @return true si todos los campos son validos
     */
    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        validateRequired(firstNameField, "El nombre es obligatorio.", errorMessage);
        validateRequired(lastNameField, "Los apellidos son obligatorios.", errorMessage);
        validateRequired(streetField, "La calle es obligatoria.", errorMessage);
        validateRequired(cityField, "La ciudad es obligatoria.", errorMessage);

        String postalCode = textOf(postalCodeField);
        if (postalCode.isEmpty()) {
            errorMessage.append("El codigo postal es obligatorio.\n");
        } else {
            try {
                int value = Integer.parseInt(postalCode);
                if (value < 0) {
                    errorMessage.append("El codigo postal no puede ser negativo.\n");
                }
            } catch (NumberFormatException exception) {
                errorMessage.append("El codigo postal debe ser un numero entero.\n");
            }
        }

        String birthday = textOf(birthdayField);
        if (birthday.isEmpty()) {
            errorMessage.append("La fecha de nacimiento es obligatoria.\n");
        } else if (!DateUtil.validDate(birthday)) {
            errorMessage.append("La fecha debe usar el formato dd.MM.yyyy.\n");
        }

        if (errorMessage.isEmpty()) {
            return true;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Campos no validos");
        alert.setHeaderText("Corrige los campos indicados");
        alert.setContentText(errorMessage.toString());
        alert.showAndWait();
        return false;
    }

    private void validateRequired(TextField field, String message, StringBuilder errors) {
        if (textOf(field).isEmpty()) {
            errors.append(message).append('\n');
        }
    }

    private String textOf(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }
}
