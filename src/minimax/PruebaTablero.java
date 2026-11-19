
package minimax;

public class PruebaTablero {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        tablero.colocarFicha(0, 0, 'X');
        tablero.colocarFicha(1, 1, 'X');
        tablero.colocarFicha(2, 2, 'X');

        System.out.println("Estado del tablero:");
        tablero.mostrarTablero();

        System.out.println("\nGanador:");

        char ganador = tablero.obtenerGanador();

        if (ganador != '-') {
            System.out.println("El ganador es: " + ganador);
        } else {
            System.out.println("Todavia no hay ganador.");
        }
    }
}
