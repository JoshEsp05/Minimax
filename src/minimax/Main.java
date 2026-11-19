
package minimax;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage ventana) {

        ventana.setTitle("Proyecto Minimax - TIC TAC TOE");
        ventana.setResizable(true);
        ventana.setMaximized(true);

        ventana.setScene(crearPantallaInicio(ventana));
        ventana.show();
    }

    private Scene crearPantallaInicio(Stage ventana) {

        Label titulo = new Label("PROYECTO MINIMAX");

        titulo.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFFFFF;"
        );

        Label subtitulo = new Label(
                "Inteligencia artificial aplicada a TIC TAC TOE"
        );

        subtitulo.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: #AAB7C9;"
        );

        Label descripcion = new Label(
                "Enfrentate a una inteligencia artificial que analiza " +
                "tus movimientos y busca siempre la mejor decision."
        );

        descripcion.setWrapText(true);
        descripcion.setMaxWidth(480);
        descripcion.setAlignment(Pos.CENTER);

        descripcion.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #C5CEDB;" +
                "-fx-text-alignment: center;"
        );

        Button botonIniciar = new Button("INICIAR PARTIDA");

        botonIniciar.setPrefWidth(240);
        botonIniciar.setPrefHeight(55);

        botonIniciar.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #2878D0;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        botonIniciar.setOnMouseEntered(e ->
                botonIniciar.setStyle(
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #1D65B5;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
                )
        );

        botonIniciar.setOnMouseExited(e ->
                botonIniciar.setStyle(
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #2878D0;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
                )
        );

        botonIniciar.setOnAction(e -> {

            InterfazJuego juego = new InterfazJuego(
                    ventana,
                    () -> ventana.setScene(crearPantallaInicio(ventana))
            );

            ventana.setScene(juego.crearEscena());
        });

        Label minimax = crearEtiqueta("MINIMAX");
        Label poda = crearEtiqueta("PODA ALFA-BETA");
        Label javafx = crearEtiqueta("JAVAFX");

        HBox tecnologias = new HBox(
                12,
                minimax,
                poda,
                javafx
        );

        tecnologias.setAlignment(Pos.CENTER);

        VBox contenido = new VBox(
                22,
                titulo,
                subtitulo,
                descripcion,
                botonIniciar,
                tecnologias
        );

        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(35));
        contenido.setMaxWidth(1250);

        contenido.setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 18;"
        );

        Label pie = new Label(
                "PROYECTO DE INTELIGENCIA ARTIFICIAL"
        );

        pie.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #8290A3;"
        );

        BorderPane raiz = new BorderPane();

        raiz.setCenter(contenido);
        raiz.setBottom(pie);

        BorderPane.setAlignment(pie, Pos.CENTER);
        BorderPane.setMargin(pie, new Insets(20));

        raiz.setStyle(
                "-fx-background-color: #202735;"
        );

        return new Scene(raiz, 1300, 700);
    }

    private Label crearEtiqueta(String texto) {

        Label etiqueta = new Label(texto);

        etiqueta.setPadding(new Insets(8, 13, 8, 13));

        etiqueta.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C5CEDB;" +
                "-fx-background-color: #344257;" +
                "-fx-background-radius: 15;"
        );

        return etiqueta;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
