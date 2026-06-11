package Grafo;

import modelo.LugarTuristico;
import java.util.*;

public class GrafoTuristico {

    private Map<String, LugarTuristico> nodos;
    private Map<String, List<Arista>> adyacencia;

    public GrafoTuristico() {
        nodos = new LinkedHashMap<>();
        adyacencia = new LinkedHashMap<>();
    }

    public boolean estaVacio() {
        return nodos.isEmpty();
    }