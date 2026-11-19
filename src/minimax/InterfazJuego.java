
package minimax;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class InterfazJuego {

    private Tablero tablero;
    private Button[][] botones;

    private Label estado;
    private Label estadisticas;
    private Label mejorMovimiento;

    private boolean partidaTerminada;

    private PanelArbol panelArbol;

    private Stage ventana;
    private Runnable volverInicio;

    // Estadísticas acumuladas
    private int jugadasJugador;
    private int jugadasIA;
    private int totalNodosVisitados;
    private int totalMovimientosOmitidos;

    public InterfazJuego(Stage ventana, Runnable volverInicio) {

        this.ventana = ventana;
        this.volverInicio = volverInicio;

        tablero = new Tablero();
        botones = new Button[3][3];

        partidaTerminada = false;

        jugadasJugador = 0;
        jugadasIA = 0;
        totalNodosVisitados = 0;
        totalMovimientosOmitidos = 0;
    }

    public Scene crearEscena() {

        Label titulo = new Label("TIC TAC TOE");

        titulo.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitulo = new Label(
                "Enfrentate a la inteligencia artificial"
        );

        subtitulo.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #AAB7C9;"
        );

        estado = new Label("Tu turno: juega con X");

        estado.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFFFFF;"
        );

        // Tablero del juego
        GridPane cuadrícula = new GridPane();

        cuadrícula.setAlignment(Pos.CENTER);
        cuadrícula.setHgap(10);
        cuadrícula.setVgap(10);

        for (int fila = 0; fila < 3; fila++) {

            for (int columna = 0; columna < 3; columna++) {

                Button boton = new Button("");

                boton.setPrefSize(112, 112);

                boton.setStyle(
                        "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #344257;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-cursor: hand;"
                );

                final int f = fila;
                final int c = columna;

                boton.setOnAction(e ->
                        realizarMovimientoHumano(f, c)
                );

                botones[fila][columna] = boton;

                cuadrícula.add(boton, columna, fila);
            }
        }

        // Estadísticas
        Label tituloEstadisticas = new Label("ESTADÍSTICAS");

        tituloEstadisticas.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #AAB7C9;"
        );

        estadisticas = new Label(
                "Nodos visitados: 0\n" +
                "Movimientos omitidos: 0"
        );

        estadisticas.setWrapText(false);
        estadisticas.setMinHeight(45);
        estadisticas.setPrefHeight(45);

        estadisticas.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: white;"
        );

        mejorMovimiento = new Label(
                "Mejor movimiento: pendiente"
        );

        mejorMovimiento.setWrapText(true);

        mejorMovimiento.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #C5CEDB;"
        );

        // Botón reiniciar
        Button reiniciar = new Button("REINICIAR PARTIDA");

        reiniciar.setPrefWidth(220);
        reiniciar.setPrefHeight(42);

        reiniciar.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #2878D0;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        reiniciar.setOnAction(e -> reiniciarPartida());

        // Panel de estadísticas
        VBox panelEstadisticas = new VBox(
                12,
                tituloEstadisticas,
                estadisticas,
                mejorMovimiento,
                reiniciar
        );

        panelEstadisticas.setMinWidth(370);
        panelEstadisticas.setMinHeight(160);
        panelEstadisticas.setPrefHeight(180);
        panelEstadisticas.setAlignment(Pos.CENTER_LEFT);


        panelEstadisticas.setPadding(new Insets(15));
        panelEstadisticas.setAlignment(Pos.CENTER_LEFT);

        panelEstadisticas.setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 10;"
        );

        // Zona del juego
        VBox zonaJuego = new VBox(
                12,
                titulo,
                subtitulo,
                estado,
                cuadrícula,
                panelEstadisticas
        );

        zonaJuego.setAlignment(Pos.CENTER);
        zonaJuego.setPadding(new Insets(22));
        zonaJuego.setPrefWidth(450);
        zonaJuego.setMinWidth(410);

        zonaJuego.setStyle(
                "-fx-background-color: #293548;" +
                "-fx-background-radius: 15;"
        );

        // Panel del árbol de decisiones
        panelArbol = new PanelArbol();

        // Distribución horizontal adaptable
        HBox contenido = new HBox(
                25,
                zonaJuego,
                panelArbol
        );

        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(20));
        contenido.setFillHeight(true);

        HBox.setHgrow(zonaJuego, Priority.NEVER);
        HBox.setHgrow(panelArbol, Priority.ALWAYS);

        panelArbol.setMaxWidth(Double.MAX_VALUE);
        panelArbol.setMaxHeight(Double.MAX_VALUE);

        BorderPane raiz = new BorderPane();

        raiz.setCenter(contenido);

        raiz.setStyle(
                "-fx-background-color: #202735;"
        );

        return new Scene(raiz, 1300, 700);
    }

    private void realizarMovimientoHumano(int fila, int columna) {

        if (partidaTerminada) {
            return;
        }

        boolean movimientoValido =
                tablero.colocarFicha(fila, columna, 'X');

        if (!movimientoValido) {
            return;
        }

        jugadasJugador++;

        actualizarTablero();

        if (verificarFinPartida()) {
            return;
        }

        realizarMovimientoIA();
    }

    private void realizarMovimientoIA() {

        estado.setText("La IA está analizando...");

        Minimax ia = new Minimax();

        ResultadoBusqueda resultado = ia.buscar(tablero);

        panelArbol.actualizar(resultado);

        Nodo mejor = resultado.getMejorMovimiento();

        // Acumular estadísticas de cada búsqueda
        totalNodosVisitados += resultado.getNodosVisitados();

        totalMovimientosOmitidos += resultado.getNodosPodados();

        estadisticas.setText(
                "Nodos visitados: " + totalNodosVisitados +
                "\nMovimientos omitidos: " + totalMovimientosOmitidos
        );

        if (mejor == null) {
            verificarFinPartida();
            return;
        }

        mejorMovimiento.setText(
                "Mejor movimiento: fila " +
                (mejor.getFila() + 1) +
                ", columna " +
                (mejor.getColumna() + 1) +
                "\nValor: " + resultado.getValorMejorMovimiento()
        );

        tablero.colocarFicha(
                mejor.getFila(),
                mejor.getColumna(),
                'O'
        );

        jugadasIA++;

        actualizarTablero();

        if (!verificarFinPartida()) {
            estado.setText("Tu turno: juega con X");
        }
    }

        private boolean verificarFinPartida() {

        char ganador = tablero.obtenerGanador();

        if (ganador == 'X') {

                partidaTerminada = true;

        } else if (ganador == 'O') {

                partidaTerminada = true;

        } else if (tablero.estaLleno()) {

                partidaTerminada = true;
                ganador = '-';
        }

        if (partidaTerminada) {

                actualizarTablero();

                // Esperar antes de mostrar los resultados
                char resultadoFinal = ganador;

                PauseTransition pausa = new PauseTransition(
                        Duration.seconds(.15)
                );

                pausa.setOnFinished(e ->
                        mostrarResultados(resultadoFinal)
                );

                pausa.play();

                return true;
        }

        return false;
        }

    private void mostrarResultados(char ganador) {

        Scene escenaResultados = PantallaResultados.crearEscena(
                ganador,
                jugadasJugador,
                jugadasIA,
                totalNodosVisitados,
                totalMovimientosOmitidos,

                // Acción para jugar nuevamente
                () -> {

                    InterfazJuego nuevoJuego = new InterfazJuego(
                            ventana,
                            volverInicio
                    );

                    ventana.setScene(nuevoJuego.crearEscena());
                },

                // Acción para volver al menú principal
                volverInicio
        );

        ventana.setScene(escenaResultados);
    }

    private void actualizarTablero() {

        for (int fila = 0; fila < 3; fila++) {

            for (int columna = 0; columna < 3; columna++) {

                char ficha = tablero.obtenerFicha(fila, columna);

                Button boton = botones[fila][columna];

                if (ficha == 'X') {

                    boton.setText("X");

                    boton.setStyle(
                            "-fx-font-size: 42px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-color: #2878D0;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 12;"
                    );

                } else if (ficha == 'O') {

                    boton.setText("O");

                    boton.setStyle(
                            "-fx-font-size: 42px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-color: #D97736;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 12;"
                    );

                } else {

                    boton.setText("");

                    boton.setStyle(
                            "-fx-font-size: 42px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-color: #344257;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 12;"
                    );
                }

                boton.setDisable(
                        ficha != '-' || partidaTerminada
                );
            }
        }
    }

    private void reiniciarPartida() {

        tablero = new Tablero();

        partidaTerminada = false;

        jugadasJugador = 0;
        jugadasIA = 0;
        totalNodosVisitados = 0;
        totalMovimientosOmitidos = 0;

        panelArbol.limpiar();

        estado.setText("Tu turno: juega con X");

        estadisticas.setText(
                "Nodos visitados: 0\n" +
                "Movimientos omitidos: 0"
        );

        mejorMovimiento.setText(
                "Mejor movimiento: pendiente"
        );

        actualizarTablero();
    }
}