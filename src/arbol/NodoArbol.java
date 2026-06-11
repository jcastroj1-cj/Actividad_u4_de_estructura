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
    // Insertar la raíz del árbol
    public void insertarRaiz(LugarTuristico lugar) {
        if (raiz != null) {
            System.out.println("❌ El árbol ya tiene una raíz: " + raiz.getLugar().getNombre());
            return;
        }
        raiz = new NodoArbol(lugar);
        System.out.println("✅ Raíz insertada: " + lugar.getNombre());
    }

    // Insertar un lugar como hijo de otro lugar existente
    public void insertarHijo(String codigoPadre, LugarTuristico lugarHijo) {
        if (estaVacio()) {
            System.out.println("❌ El árbol está vacío. Inserte primero la raíz.");
            return;
        }

        NodoArbol padre = buscarNodo(raiz, codigoPadre);
        if (padre == null) {
            System.out.println("❌ No se encontró el lugar padre con código: " + codigoPadre);
            return;
        }

        padre.agregarHijo(new NodoArbol(lugarHijo));
        System.out.println("✅ Lugar insertado: " + lugarHijo.getNombre() +
                " bajo: " + padre.getLugar().getNombre());
    }
    // Buscar un nodo por código (recorrido recursivo)
    private NodoArbol buscarNodo(NodoArbol nodo, String codigo) {
        if (nodo == null) return null;
        if (nodo.getLugar().getCodigo().equalsIgnoreCase(codigo)) return nodo;

        for (NodoArbol hijo : nodo.getHijos()) {
            NodoArbol encontrado = buscarNodo(hijo, codigo);
            if (encontrado != null) return encontrado;
        }
        return null;
    }
    // Consultar un lugar por código
    public void consultar(String codigo) {
        if (estaVacio()) {
            System.out.println("❌ El árbol está vacío.");
            return;
        }

        NodoArbol nodo = buscarNodo(raiz, codigo);
        if (nodo == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }

        System.out.println("\n🔍 LUGAR ENCONTRADO:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println(nodo.getLugar());

        if (!nodo.getHijos().isEmpty()) {
            System.out.println("Sublugar es:");
            nodo.getHijos().forEach(h -> System.out.println("   → " + h.getLugar().getNombre()));
        } else {
            System.out.println("Este lugar no tiene sublugar es.");
        }
        System.out.println("─────────────────────────────────────────────");
    }
    // Actualizar un lugar por código
    public void actualizar(String codigo, String nuevoNombre, String nuevoTipo, String nuevaDescripcion) {
        if (estaVacio()) {
            System.out.println("❌ El árbol está vacío.");
            return;
        }

        NodoArbol nodo = buscarNodo(raiz, codigo);
        if (nodo == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }

        nodo.getLugar().setNombre(nuevoNombre);
        nodo.getLugar().setTipo(nuevoTipo);
        nodo.getLugar().setDescripcion(nuevaDescripcion);

        System.out.println("✅ Lugar actualizado correctamente.");
        System.out.println(nodo.getLugar());
    }
    
}