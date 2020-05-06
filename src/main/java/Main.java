import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    //        вручную тестировал парсер
//        File file = new File("src/test/pages.pdf");
//        HashMap<Integer, String> parsed = PDFParser.testParse();
//        for(Integer i : parsed.keySet()) {
//            System.out.println(i + " " + parsed.get(i));
//        }
//        }
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("launch()");
        System.out.println("bounch()");
        loader = new FXMLLoader(getClass().getResource("view/MainWindow.fxml"));
        Parent mainWindow = loader.load();
        scene = new Scene(mainWindow);
        main = new Stage();
        main.setScene(scene);
        main.show();
    }
}
