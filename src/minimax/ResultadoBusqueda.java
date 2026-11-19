
package minimax;

public class ResultadoBusqueda {

    private Nodo raiz;
    private Nodo mejorMovimiento;

    private int nodosVisitados;
    private int nodosPodados;

    public ResultadoBusqueda(
            Nodo raiz,
            Nodo mejorMovimiento,
            int nodosVisitados,
            int nodosPodados) {

        this.raiz = raiz;
        this.mejorMovimiento = mejorMovimiento;
        this.nodosVisitados = nodosVisitados;
        this.nodosPodados = nodosPodados;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Nodo getMejorMovimiento() {
        return mejorMovimiento;
    }

    public int getNodosVisitados() {
        return nodosVisitados;
    }

    public int getNodosPodados() {
        return nodosPodados;
    }

    public int getValorMejorMovimiento() {

        if (mejorMovimiento == null) {
            return 0;
        }

        return mejorMovimiento.getValor();
    }
}

