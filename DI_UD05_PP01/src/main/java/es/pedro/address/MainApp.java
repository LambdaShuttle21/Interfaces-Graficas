package es.pedro.address;

import es.pedro.address.model.Person;
import es.pedro.address.view.PersonEditDialogController;
import es.pedro.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la libreta de direcciones.
 */
public class MainApp extends Application {

    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Crea datos de ejemplo para mostrar el funcionamiento inicial.
     */
    public MainApp() {
        personData.add(new Person("Ana", "Garcia"));
        personData.add(new Person("Carlos", "Lopez"));
        personData.add(new Person("Lucia", "Martin"));
        personData.add(new Person("David", "Sanchez"));
        personData.add(new Person("Marta", "Romero"));
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Libreta de direcciones");

        initRootLayout();
        showPersonOverview();
    }

    /**
     * Carga el contenedor principal y muestra la escena.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/es/pedro/address/view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(760);
            primaryStage.setMinHeight(500);
            primaryStage.show();
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar RootLayout.fxml", exception);
        }
    }

    /**
     * Carga la vista de personas en el centro del contenedor principal.
     */
    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/es/pedro/address/view/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar PersonOverview.fxml", exception);
        }
    }

    /**
     * Abre un dialogo modal para crear o editar una persona.
     *
     * @param person persona que se desea editar
     * @return true si el usuario confirma los cambios
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    MainApp.class.getResource("/es/pedro/address/view/PersonEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(page));

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo cargar PersonEditDialog.fxml", exception);
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
