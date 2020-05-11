import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    private FXMLLoader loader;
    @FXML
    private Scene scene;
    @FXML
    private Stage main;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("view/MainWindow.fxml"));
        Parent mainWindow = loader.load();
        scene = new Scene(mainWindow);
        main = new Stage();
        main.setTitle("PDF Maker");
        main.getIcons().add(new Image("images/Icon.png"));
        main.setResizable(false);
        main.setScene(scene);
        main.show();
    }
}
