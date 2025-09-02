package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

public class AplicacionController {

    @FXML
    private ImageView btnMas;

    @FXML
    private ImageView btnMenos;

    @FXML
    private ImageView imgConejo;

    @FXML
    private Label lbFecha;

    @FXML
    private Label lbPorcentaje;

    @FXML
    private Label lbPorcentaje2;

    Font miFuente = Font.loadFont(getClass().getResource("/fonts/alertFont.ttf").toExternalForm(), 15);


    @FXML
    private ProgressIndicator prgIndicator;

    public int click = 0;


    public void initialize() {

        lbFecha.setText(String.valueOf(formatearFecha()));


    }

    private String formatearFecha() {
        LocalDate fechaActual = LocalDate.now();

        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd MMMM", new Locale("es", "ES"));

        String fechaFormateada = fechaActual.format(formatear);

        Function<String, String> texto = t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase();

        String[] partes = fechaFormateada.split(" ");
        partes[1] = texto.apply(partes[1]);
        String fechaFormateadaFinal = partes[0] + " " + partes[1];

        return fechaFormateadaFinal;
    }

    @FXML
    void btnMas_clicked(MouseEvent event) {
        if(click < 10) {
            click++;
            prgIndicator.setProgress((double) click / 10);
            porcentajeMas();
            actualizarImagen();

            if(click == 10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("¡Has terminado de beber por hoy! ☺✔ ");
                alert.setHeaderText(null);
                alert.setTitle(" ¡Enhorabuena!");

                ButtonType boton = new ButtonType("Genial ♥");
                alert.getButtonTypes().setAll(boton);

                alert.getDialogPane().lookupButton(boton).getStyleClass().add("/styleBoton.css");
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());



               /*Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("imagenes/vaso.png"));*/


                alert.showAndWait();

            }
        }

    }

    private void porcentajeMas() {
        if(click == 0) {
            lbPorcentaje.getText();
        }else if(click < 10) {
            lbPorcentaje.setText("");
            lbPorcentaje2.setText(click * 10 + "%");
        }else  {
            lbPorcentaje2.setText("");
        }
    }

    @FXML
    void btnMenos_clicked(MouseEvent event) {
        if (click > 0) {
            click--;
            prgIndicator.setProgress((double) click / 10);
            porcentajeMenos();
            actualizarImagen();

        }
    }

    private void porcentajeMenos() {
        if(click == 0) {
            lbPorcentaje.setText("0%");
            lbPorcentaje2.setText("");
        }else if(click < 10) {
            lbPorcentaje.setText("");
            lbPorcentaje2.setText(click * 10 + "%");
        }else  {
            lbPorcentaje2.setText("");
        }
    }

    private void actualizarImagen() {
        String ruta = "/imagenes/ConejoBebida/" + (click + 1) + ".png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResource(ruta)).toExternalForm());
        imgConejo.setImage(imagen);
    }

}
