package arbol;

import modelo.LugarTuristico;
import java.util.ArrayList;
import java.util.List;

public class NodoArbol {

    private LugarTuristico lugar;
    private List<NodoArbol> hijos;

    public NodoArbol(LugarTuristico lugar) {
        this.lugar = lugar;
        this.hijos = new ArrayList<>();
    }

    // GETTERS
    public LugarTuristico getLugar() { return lugar; }
    public List<NodoArbol> getHijos() { return hijos; }

    // Agregar un hijo a este nodo
    public void agregarHijo(NodoArbol hijo) {
        hijos.add(hijo);
    }

    // Verificar si es hoja (sin hijos)
    public boolean esHoja() {
        return hijos.isEmpty();
    }
}