package dad.monthcalendar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {

    // Model
    private IntegerProperty yearProperty = new SimpleIntegerProperty();
    private IntegerProperty monthProperty = new SimpleIntegerProperty();

    // View
    @FXML
    private Label monthNameLabel;
    @FXML
    private GridPane view;

    @FXML
    private List<Label> daysLabelList;

    public MonthCalendar() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monthCalendar.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bindings

        // Listeners
        monthProperty.addListener((o, ov, nv) -> {
            Month mes = Month.of(getMonth());
            monthNameLabel.setText(mes.name() + " " + mes.maxLength());
            onCambioFecha(o, ov, nv);
        });

    }

    public GridPane getView() {
        return view;
    }

    public IntegerProperty yearProperty() {
        return yearProperty;
    }

    public int getYear() {
        return yearProperty.get();
    }

    public void setYear(int newYear) {
        yearProperty.set(newYear);
    }

    public IntegerProperty monthProperty() {
        return monthProperty;
    }

    public int getMonth() {
        return monthProperty.get();
    }

    public void setMonth(int newMonth) {
        monthProperty.set(newMonth);
    }

    private void onCambioFecha(ObservableValue<? extends Number> o, Number ov, Number nv) {
        int dia1 = primerDia(yearProperty.get(), monthProperty.get());
        int diaFinal = ultimoDia(yearProperty.get(), monthProperty.get());

        // Pone los labels en blanco hasta llegar al primer dia de la semana
        for (int i = 0; i < dia1; i++) {
            daysLabelList.get(i).setText("");
        }

        /*
         * Pone los numeros de los días en el lugar que corresponde
         * i = Indice de la tabla
         * j = Indice del numero de días
         */
        for (int i = dia1, j = 1; i < dia1 + diaFinal; i++, j++) {
            daysLabelList.get(i).setText("" + j);
        }

        for (int i = dia1 + diaFinal; i < daysLabelList.size(); i++) {
            daysLabelList.get(i).setText("");
        }
        monthNameLabel.setText(obtenerMes(monthProperty.get()));
    }

    private int ultimoDia(int year, int month) {
        LocalDate date = LocalDate.of(year, month, Month.of(month).length(Year.isLeap(year)));
        return date.getDayOfMonth();
    }

    private int primerDia(int year, int month) {        
        LocalDate date = LocalDate.of(year, month, 1);
        return date.getDayOfWeek().getValue();
    }

    private String obtenerMes(int i) {
        return Month.of(i).toString();
    }

}
