
package minimax;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PantallaResultados {

    public static Scene crearEscena(
            char ganador,
            int jugadasJugador,
            int jugadasIA,
            int nodosVisitados,
            int movimientosOmitidos,
            Runnable jugarNuevamente,
            Runnable volverInicio) {

        String titulo;
        String descripcion;
        String color;

        if (ganador == 'X') {

            titulo = "¡VICTORIA!";
            descripcion = "¡Felicidades! Has conseguido vencer a la IA.";
            color = "#2878D0";

        } else if (ganador == 'O') {

            titulo = "LA IA HA GANADO";
            descripcion = "La inteligencia artificial encontró la mejor secuencia.";
            color = "#D97736";

        } else {

            titulo = "¡EMPATE!";
            descripcion = "No quedan movimientos y nadie consiguió ganar.";
            color = "#AAB7C9";
        }

        Label tituloResultado = new Label(titulo);

        tituloResultado.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + color + ";"
        );

        Label mensaje = new Label(descripcion);

        mensaje.setWrapText(true);
        mensaje.setAlignment(Pos.CENTER);

        mensaje.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #C5CEDB;" +
                "-fx-text-alignment: center;"
        );

        Label subtitulo = new Label("RESUMEN DE LA PARTIDA");

        subtitulo.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #AAB7C9;"
        );

        GridPane estadisticas = new GridPane();

        estadisticas.setAlignment(Pos.CENTER);
        estadisticas.setHgap(15);
        estadisticas.setVgap(15);

        estadisticas.add(
                crearTarjeta("TUS MOVIMIENTOS", String.valueOf(jugadasJugador), "#2878D0"),
                0, 0
        );

        estadisticas.add(
                crearTarjeta("MOVIMIENTOS DE LA IA", String.valueOf(jugadasIA), "#D97736"),
                1, 0
        );

        estadisticas.add(
                crearTarjeta("NODOS VISITADOS", String.valueOf(nodosVisitados), "#FFFFFF"),
                0, 1
        );

        estadisticas.add(
                crearTarjeta("MOVIMIENTOS OMITIDOS", String.valueOf(movimientosOmitidos), "#FFFFFF"),
                1, 1
        );

        Button botonJugar = new Button("JUGAR NUEVAMENTE");

        botonJugar.setPrefWidth(230);
        botonJugar.setPrefHeight(48);

        botonJugar.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #2878D0;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        botonJugar.setOnAction(e -> jugarNuevamente.run());

        Button botonInicio = new Button("VOLVER AL INICIO");

        botonInicio.setPrefWidth(230);
        botonInicio.setPrefHeight(43);

        botonInicio.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #344257;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        botonInicio.setOnAction(e -> volverInicio.run());

        VBox contenido = new VBox(
                20,
                tituloResultado,
                mensaje,
                subtitulo,
                estadisticas,
                botonJugar,
                botonInicio
        );

        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(35));
        contenido.setMaxWidth(850);

        contenido.setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 18;"
        );

        BorderPane raiz = new BorderPane();

        raiz.setCenter(contenido);

        raiz.setStyle(
                "-fx-background-color: #202735;"
        );

        return new Scene(raiz, 1300, 700);
    }

    private static VBox crearTarjeta(
            String titulo,
            String valor,
            String color) {

        Label etiqueta = new Label(titulo);

        etiqueta.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #AAB7C9;"
        );

        Label numero = new Label(valor);

        numero.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + color + ";"
        );

        VBox tarjeta = new VBox(
                10,
                etiqueta,
                numero
        );

        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPrefSize(250, 95);
        tarjeta.setPadding(new Insets(15));

        tarjeta.setStyle(
                "-fx-background-color: #344257;" +
                "-fx-background-radius: 10;"
        );

        return tarjeta;
    }
}
