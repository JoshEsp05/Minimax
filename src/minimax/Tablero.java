
package minimax;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private char[][] celdas;

    public Tablero() {
        celdas = new char[3][3];

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                celdas[fila][columna] = '-';
            }
        }
    }

    // Crear una copia del tablero
    public Tablero(Tablero original) {
        celdas = new char[3][3];

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                celdas[fila][columna] =
                    original.celdas[fila][columna];
            }
        }
    }

    // Colocar una ficha
    public boolean colocarFicha(int fila, int columna, char ficha) {

        if (fila < 0 || fila >= 3 ||
            columna < 0 || columna >= 3) {
            return false;
        }

        if (celdas[fila][columna] != '-') {
            return false;
        }

        celdas[fila][columna] = ficha;
        return true;
    }

    // Obtener una posición
    public char obtenerFicha(int fila, int columna) {
        return celdas[fila][columna];
    }

    // Obtener las posiciones disponibles
    public List<int[]> obtenerMovimientos() {

        List<int[]> movimientos = new ArrayList<>();

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {

                if (celdas[fila][columna] == '-') {
                    movimientos.add(new int[]{fila, columna});
                }
            }
        }

        return movimientos;
    }

    // Comprobar si existe un ganador
    public char obtenerGanador() {

        // Filas
        for (int i = 0; i < 3; i++) {

            if (celdas[i][0] != '-' &&
                celdas[i][0] == celdas[i][1] &&
                celdas[i][1] == celdas[i][2]) {

                return celdas[i][0];
            }
        }

        // Columnas
        for (int i = 0; i < 3; i++) {

            if (celdas[0][i] != '-' &&
                celdas[0][i] == celdas[1][i] &&
                celdas[1][i] == celdas[2][i]) {

                return celdas[0][i];
            }
        }

        // Diagonal principal
        if (celdas[0][0] != '-' &&
            celdas[0][0] == celdas[1][1] &&
            celdas[1][1] == celdas[2][2]) {

            return celdas[0][0];
        }

        // Diagonal secundaria
        if (celdas[0][2] != '-' &&
            celdas[0][2] == celdas[1][1] &&
            celdas[1][1] == celdas[2][0]) {

            return celdas[0][2];
        }

        return '-';
    }

    // Comprobar si el tablero está lleno
    public boolean estaLleno() {
        return obtenerMovimientos().isEmpty();
    }

    // Mostrar el tablero en consola
    public void mostrarTablero() {

        for (int fila = 0; fila < 3; fila++) {

            for (int columna = 0; columna < 3; columna++) {
                System.out.print(celdas[fila][columna] + " ");
            }

            System.out.println();
        }
    }
}
