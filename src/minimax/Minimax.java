
package minimax;

import java.util.List;

public class Minimax {

    private int nodosVisitados;
    private int nodosPodados;

    private Nodo raiz;

    public Minimax() {
        nodosVisitados = 0;
        nodosPodados = 0;
    }

    public Nodo obtenerMejorMovimiento(Tablero tablero) {

        nodosVisitados = 0;
        nodosPodados = 0;

        raiz = new Nodo(tablero, 'O', -1, -1);

        int mejorValor = Integer.MIN_VALUE;
        Nodo mejorMovimiento = null;

        List<int[]> movimientos = tablero.obtenerMovimientos();

        for (int[] movimiento : movimientos) {

            Tablero nuevoTablero = new Tablero(tablero);

            nuevoTablero.colocarFicha(
                    movimiento[0],
                    movimiento[1],
                    'O'
            );

            Nodo hijo = new Nodo(
                    nuevoTablero,
                    'X',
                    movimiento[0],
                    movimiento[1]
            );

            hijo.setNivel(1);

            raiz.agregarHijo(hijo);

            int valor = minimax(
                    nuevoTablero,
                    false,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    hijo
            );

            hijo.setValor(valor);

            if (valor > mejorValor) {
                mejorValor = valor;
                mejorMovimiento = hijo;
            }
        }

        raiz.setValor(mejorValor);

        return mejorMovimiento;
    }

    private int minimax(
            Tablero tablero,
            boolean esMaximizador,
            int alfa,
            int beta,
            Nodo nodoActual) {

        nodosVisitados++;

        char ganador = tablero.obtenerGanador();

        if (ganador == 'O') {
            nodoActual.setValor(1);
            nodoActual.setAlfa(alfa);
            nodoActual.setBeta(beta);
            return 1;
        }

        if (ganador == 'X') {
            nodoActual.setValor(-1);
            nodoActual.setAlfa(alfa);
            nodoActual.setBeta(beta);
            return -1;
        }

        if (tablero.estaLleno()) {
            nodoActual.setValor(0);
            nodoActual.setAlfa(alfa);
            nodoActual.setBeta(beta);
            return 0;
        }

        if (esMaximizador) {

            int mejorValor = Integer.MIN_VALUE;

            for (int[] movimiento : tablero.obtenerMovimientos()) {

                Tablero nuevoTablero = new Tablero(tablero);

                nuevoTablero.colocarFicha(
                        movimiento[0],
                        movimiento[1],
                        'O'
                );

                Nodo hijo = new Nodo(
                        nuevoTablero,
                        'X',
                        movimiento[0],
                        movimiento[1]
                );

                hijo.setNivel(nodoActual.getNivel() + 1);

                nodoActual.agregarHijo(hijo);

                int valor = minimax(
                        nuevoTablero,
                        false,
                        alfa,
                        beta,
                        hijo
                );

                hijo.setValor(valor);

                mejorValor = Math.max(mejorValor, valor);

                alfa = Math.max(alfa, mejorValor);

                if (beta <= alfa) {

                    marcarPodados(
                            nodoActual,
                            nodoActual.getHijos().size() - 1,
                            tablero.obtenerMovimientos().size()
                    );

                    break;
                }
            }

            nodoActual.setValor(mejorValor);
            nodoActual.setAlfa(alfa);
            nodoActual.setBeta(beta);

            return mejorValor;

        } else {

            int mejorValor = Integer.MAX_VALUE;

            for (int[] movimiento : tablero.obtenerMovimientos()) {

                Tablero nuevoTablero = new Tablero(tablero);

                nuevoTablero.colocarFicha(
                        movimiento[0],
                        movimiento[1],
                        'X'
                );

                Nodo hijo = new Nodo(
                        nuevoTablero,
                        'O',
                        movimiento[0],
                        movimiento[1]
                );

                hijo.setNivel(nodoActual.getNivel() + 1);

                nodoActual.agregarHijo(hijo);

                int valor = minimax(
                        nuevoTablero,
                        true,
                        alfa,
                        beta,
                        hijo
                );

                hijo.setValor(valor);

                mejorValor = Math.min(mejorValor, valor);

                beta = Math.min(beta, mejorValor);

                if (beta <= alfa) {

                    marcarPodados(
                            nodoActual,
                            nodoActual.getHijos().size() - 1,
                            tablero.obtenerMovimientos().size()
                    );

                    break;
                }
            }

            nodoActual.setValor(mejorValor);
            nodoActual.setAlfa(alfa);
            nodoActual.setBeta(beta);

            return mejorValor;
        }
    }

    private void marcarPodados(
            Nodo nodo,
            int indice,
            int totalMovimientos) {

        int restantes = totalMovimientos - indice - 1;

        if (restantes > 0) {

            nodosPodados += restantes;

            nodo.setPodado(true);

            nodo.setMovimientosPodados(
                    nodo.getMovimientosPodados() + restantes
            );
        }
    }

    public int getNodosVisitados() {
        return nodosVisitados;
    }

    public int getNodosPodados() {
        return nodosPodados;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public ResultadoBusqueda buscar(Tablero tablero) {

        Nodo mejorMovimiento = obtenerMejorMovimiento(tablero);

        return new ResultadoBusqueda(
                raiz,
                mejorMovimiento,
                nodosVisitados,
                nodosPodados
        );
    }
}
