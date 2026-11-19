
package minimax;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PanelArbol extends VBox {

    private Label instrucciones;
    private Label tituloRespuestas;

    private VBox zonaRespuestas;
    private VBox zonaExplicacion;
    private GridPane zonaMovimientos;

    public PanelArbol() {

        setSpacing(12);
        setPadding(new Insets(18));
        setPrefWidth(540);
        setMinWidth(480);
        setMaxWidth(570);

        Label titulo = new Label("ANÁLISIS DE MINIMAX");

        titulo.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        instrucciones = new Label(
                "Realiza un movimiento para observar el análisis de la IA."
        );

        instrucciones.setWrapText(true);

        instrucciones.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #AAB7C9;"
        );

        Label tituloMovimientos = new Label(
                "MOVIMIENTOS POSIBLES DE LA IA"
        );

        tituloMovimientos.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        // Tablero de movimientos 3x3
        zonaMovimientos = new GridPane();

        zonaMovimientos.setHgap(9);
        zonaMovimientos.setVgap(9);
        zonaMovimientos.setAlignment(Pos.CENTER);

        tituloRespuestas = new Label("RESPUESTAS POSIBLES");

        tituloRespuestas.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        zonaRespuestas = new VBox(10);

        ScrollPane desplazamiento = new ScrollPane(zonaRespuestas);

        desplazamiento.setFitToWidth(true);
        desplazamiento.setPrefHeight(190);
        desplazamiento.setMaxHeight(190);

        desplazamiento.setStyle(
                "-fx-background: #202735;" +
                "-fx-background-color: #202735;"
        );

        // Explicacion inicial
        zonaExplicacion = new VBox(8);

        Label tituloExplicacion = new Label(
                "¿CÓMO INTERPRETAR EL ANÁLISIS?"
        );

        tituloExplicacion.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label valores = new Label(
                "+1  = Victoria para la IA\n" +
                " 0  = Empate\n" +
                "-1  = Derrota para la IA"
        );

        valores.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #C5CEDB;"
        );

        Label alfaBeta = new Label(
                "La poda Alfa-Beta permite omitir movimientos " +
                "que no pueden mejorar el resultado de la búsqueda. " +
                "Así, la IA evita analizar algunas alternativas innecesarias."
        );

        alfaBeta.setWrapText(true);

        alfaBeta.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #AAB7C9;"
        );

        zonaExplicacion.getChildren().addAll(
                tituloExplicacion,
                valores,
                alfaBeta
        );

        zonaExplicacion.setPadding(new Insets(12));

        zonaExplicacion.setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 9;"
        );

        getChildren().addAll(
                titulo,
                instrucciones,
                tituloMovimientos,
                zonaMovimientos,
                tituloRespuestas,
                desplazamiento,
                zonaExplicacion
        );

        setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 15;"
        );
    }

    public void actualizar(ResultadoBusqueda resultado) {

        zonaExplicacion.setVisible(false);
        zonaExplicacion.setManaged(false);

        zonaMovimientos.getChildren().clear();
        zonaRespuestas.getChildren().clear();

        if (resultado == null || resultado.getRaiz() == null) {
            limpiar();
            return;
        }

        Nodo raiz = resultado.getRaiz();

        Nodo mejorMovimiento = resultado.getMejorMovimiento();

        instrucciones.setText(
                "Selecciona una casilla para explorar las respuestas. " +
                "Nodos visitados: " + resultado.getNodosVisitados() +
                " | Movimientos omitidos: " + resultado.getNodosPodados()
        );

        // Crear las nueve casillas de analisis
        for (int fila = 0; fila < 3; fila++) {

            for (int columna = 0; columna < 3; columna++) {

                Button casilla = new Button(
                        "Fila " + (fila + 1) +
                        "\nColumna " + (columna + 1) +
                        "\nSin evaluar"
                );

                casilla.setPrefSize(130, 100);
                casilla.setWrapText(true);

                casilla.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: #344257;" +
                        "-fx-background-radius: 9;"
                );

                Nodo movimientoEncontrado = null;

                for (Nodo movimiento : raiz.getHijos()) {

                    if (movimiento.getFila() == fila &&
                        movimiento.getColumna() == columna) {

                        movimientoEncontrado = movimiento;
                        break;
                    }
                }

                if (movimientoEncontrado != null) {

                    Nodo movimiento = movimientoEncontrado;

                    boolean esMejor =
                            mejorMovimiento != null &&
                            movimiento.getFila() == mejorMovimiento.getFila() &&
                            movimiento.getColumna() == mejorMovimiento.getColumna();

                    String texto =
                            "O\nValor: " + movimiento.getValor() +
                            "\nAlfa: " + mostrarValor(movimiento.getAlfa()) +
                            "\nBeta: " + mostrarValor(movimiento.getBeta());

                    if (esMejor) {
                        texto += "\n MEJOR JUGADA";
                    }

                    casilla.setText(texto);

                    casilla.setStyle(
                            "-fx-font-size: 12px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: " +
                            obtenerColor(movimiento.getValor()) + ";" +
                            "-fx-border-color: " +
                            (esMejor ? "#FACC15" : "transparent") + ";" +
                            "-fx-border-width: " +
                            (esMejor ? "4" : "0") + ";" +
                            "-fx-border-radius: 9;" +
                            "-fx-background-radius: 9;"
                    );

                    casilla.setOnAction(e ->
                            mostrarRespuestas(movimiento)
                    );

                } else {

                    casilla.setDisable(true);
                    casilla.setOpacity(0.55);
                }

                zonaMovimientos.add(casilla, columna, fila);
            }
        }

        // Mostrar inicialmente la mejor jugada
        if (mejorMovimiento != null) {
            mostrarRespuestas(mejorMovimiento);
        }
    }

    private void mostrarRespuestas(Nodo movimiento) {

        zonaRespuestas.getChildren().clear();

        tituloRespuestas.setText(
                "RESPUESTAS A O (" +
                (movimiento.getFila() + 1) + ", " +
                (movimiento.getColumna() + 1) + ")"
        );

        Label valor = new Label(
                "Resultado de esta jugada: " + movimiento.getValor()
        );

        valor.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        zonaRespuestas.getChildren().add(valor);

        if (movimiento.getHijos().isEmpty()) {

            Label sinRespuestas = new Label(
                    "Esta jugada termina la partida o no tiene más movimientos."
            );

            sinRespuestas.setWrapText(true);
            sinRespuestas.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #AAB7C9;"
            );

            zonaRespuestas.getChildren().add(sinRespuestas);

        } else {

            Label explicacion = new Label(
                    "La IA analiza las posibles respuestas del jugador X:"
            );

            explicacion.setWrapText(true);

            explicacion.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #AAB7C9;"
            );

            zonaRespuestas.getChildren().add(explicacion);

            GridPane respuestas = new GridPane();

            respuestas.setHgap(8);
            respuestas.setVgap(8);

            for (Nodo respuesta : movimiento.getHijos()) {

                Button tarjeta = new Button(
                        "X\nFila " + (respuesta.getFila() + 1) +
                        ", Columna " + (respuesta.getColumna() + 1) +
                        "\nValor: " + respuesta.getValor()
                );

                tarjeta.setPrefSize(115, 70);
                tarjeta.setWrapText(true);
                tarjeta.setDisable(true);

                tarjeta.setStyle(
                        "-fx-font-size: 11px;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: " +
                        obtenerColor(respuesta.getValor()) + ";" +
                        "-fx-background-radius: 8;"
                );

                int indice = respuestas.getChildren().size();

                respuestas.add(
                        tarjeta,
                        indice % 3,
                        indice / 3
                );
            }

            zonaRespuestas.getChildren().add(respuestas);
        }

        if (movimiento.isPodado()) {

            Label poda = new Label(
                    "PODA ALFA-BETA: se omitieron " +
                    movimiento.getMovimientosPodados() +
                    " movimientos en esta rama."
            );

            poda.setWrapText(true);

            poda.setStyle(
                    "-fx-text-fill: #D8B4FE;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-color: #581C87;" +
                    "-fx-padding: 9;" +
                    "-fx-background-radius: 8;"
            );

            zonaRespuestas.getChildren().add(poda);
        }
    }

    private String obtenerColor(int valor) {

        if (valor == 1) {
            return "#16A34A";
        }

        if (valor == -1) {
            return "#DC2626";
        }

        return "#2563EB";
    }

    private String mostrarValor(int valor) {

        if (valor == Integer.MIN_VALUE) {
            return "-∞";
        }

        if (valor == Integer.MAX_VALUE) {
            return "+∞";
        }

        return String.valueOf(valor);
    }

    public void limpiar() {

        zonaMovimientos.getChildren().clear();
        zonaRespuestas.getChildren().clear();

        instrucciones.setText(
                "Realiza un movimiento para observar el análisis de la IA."
        );

        tituloRespuestas.setText("RESPUESTAS POSIBLES");

        zonaExplicacion.setVisible(true);
        zonaExplicacion.setManaged(true);
    }
}
