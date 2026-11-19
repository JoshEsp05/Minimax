
package minimax;

public class PruebaNodo {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        tablero.colocarFicha(0, 0, 'X');
        tablero.colocarFicha(1, 1, 'O');

        Nodo nodo = new Nodo(tablero, 'X', 1, 1);

        nodo.setValor(1);

        System.out.println("Informacion del nodo:");

        System.out.println("Jugador siguiente: "
                + nodo.getJugador());

        System.out.println("Valor del nodo: "
                + nodo.getValor());

        System.out.println("Fila: "
                + nodo.getFila());

        System.out.println("Columna: "
                + nodo.getColumna());

        System.out.println("\nTablero del nodo:");

        nodo.getTablero().mostrarTablero();

        System.out.println("\nCantidad de hijos: "
                + nodo.getHijos().size());
    }
}
