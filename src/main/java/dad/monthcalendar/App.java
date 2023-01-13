package dad.monthcalendar;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MonthCalendar controller = new MonthCalendar();
        controller.setMonth(1);
        controller.setYear(LocalDate.now().getYear());

        primaryStage.setTitle("Prueba");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.show();

    }
}
