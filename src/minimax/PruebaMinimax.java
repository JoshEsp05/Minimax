
package minimax;

public class PruebaMinimax {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        // Estado inicial de prueba
        tablero.colocarFicha(0, 0, 'X');
        tablero.colocarFicha(0, 1, 'O');

        tablero.colocarFicha(1, 1, 'X');
        tablero.colocarFicha(2, 2, 'O');

        System.out.println("TABLERO ACTUAL:");
        tablero.mostrarTablero();

        // Ejecutar Minimax
        Minimax ia = new Minimax();

        ResultadoBusqueda resultado = ia.buscar(tablero);

        Nodo mejorMovimiento =
                resultado.getMejorMovimiento();

        System.out.println("\nANALISIS DE LA IA:");

        if (mejorMovimiento != null) {

            System.out.println("Movimiento elegido:");

            System.out.println("Fila: "
                    + mejorMovimiento.getFila());

            System.out.println("Columna: "
                    + mejorMovimiento.getColumna());

            System.out.println("Valor obtenido: "
                    + resultado.getValorMejorMovimiento());
        }

        System.out.println("\nESTADISTICAS:");

        System.out.println("Nodos visitados: "
                + resultado.getNodosVisitados());

        System.out.println("Nodos podados: "
                + resultado.getNodosPodados());

        System.out.println("\nTABLERO DESPUES DEL MOVIMIENTO:");

        if (mejorMovimiento != null) {
            mejorMovimiento.getTablero().mostrarTablero();
        }
    }
}
