package arbol;

import modelo.LugarTuristico;

public class ArbolTuristico {

    private NodoArbol raiz;

    public ArbolTuristico() {
        this.raiz = null;
    }

    // Verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // Obtener la raíz
    public NodoArbol getRaiz() {
        return raiz;
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
        if (estaVacio()) { System.out.println("❌ El árbol está vacío."); return; }

        NodoArbol nodo = buscarNodo(raiz, codigo);
        if (nodo == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }
        System.out.println("\n🔍 LUGAR ENCONTRADO:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println(nodo.getLugar());
        if (!nodo.getHijos().isEmpty()) {
            System.out.println("Sublugares:");
            nodo.getHijos().forEach(h -> System.out.println("   → " + h.getLugar().getNombre()));
        } else {
            System.out.println("Este lugar no tiene sublugares.");
        }
        System.out.println("─────────────────────────────────────────────");
    }

    // Actualizar un lugar por código
    public void actualizar(String codigo, String nuevoNombre, String nuevoTipo, String nuevaDescripcion) {
        if (estaVacio()) { System.out.println("❌ El árbol está vacío."); return; }

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

    // Eliminar un lugar por código
    public void eliminar(String codigo) {
        if (estaVacio()) { System.out.println("❌ El árbol está vacío."); return; }

        if (raiz.getLugar().getCodigo().equalsIgnoreCase(codigo)) {
            if (!raiz.getHijos().isEmpty()) {
                System.out.println("❌ No se puede eliminar la raíz porque tiene sublugares.");
                return;
            }
            raiz = null;
            System.out.println("✅ Raíz eliminada.");
            return;
        }

        NodoArbol padre = buscarPadre(raiz, codigo);
        if (padre == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }
        padre.getHijos().removeIf(h -> h.getLugar().getCodigo().equalsIgnoreCase(codigo));
        System.out.println("✅ Lugar eliminado correctamente.");
    }

    // Buscar el padre de un nodo
    private NodoArbol buscarPadre(NodoArbol nodo, String codigoHijo) {
        for (NodoArbol hijo : nodo.getHijos()) {
            if (hijo.getLugar().getCodigo().equalsIgnoreCase(codigoHijo)) return nodo;
            NodoArbol resultado = buscarPadre(hijo, codigoHijo);
            if (resultado != null) return resultado;
        }
        return null;
    }

    // Recorrido Pre-Orden (DFS)
    public void recorridoPreOrden() {
        if (estaVacio()) { System.out.println("❌ El árbol está vacío."); return; }
        System.out.println("\n🌳 RECORRIDO PRE-ORDEN (DFS):");
        System.out.println("─────────────────────────────────────────────");
        preOrden(raiz, 0);
        System.out.println("─────────────────────────────────────────────");
    }

    private void preOrden(NodoArbol nodo, int nivel) {
        if (nodo == null) return;
        String sangria = "  ".repeat(nivel);
        String icono = nivel == 0 ? "🏔️" : nodo.esHoja() ? "📍" : "📂";
        System.out.println(sangria + icono + " " + nodo.getLugar().getNombre()
                + " [" + nodo.getLugar().getTipo() + "]");
        for (NodoArbol hijo : nodo.getHijos()) preOrden(hijo, nivel + 1);
    }

    // Recorrido por Niveles (BFS)
    public void recorridoPorNiveles() {
        if (estaVacio()) { System.out.println("❌ El árbol está vacío."); return; }
        System.out.println("\n🗺️  RECORRIDO POR NIVELES (BFS):");
        System.out.println("─────────────────────────────────────────────");

        java.util.Queue<NodoArbol> cola = new java.util.LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int tamano = cola.size();
            System.out.print("Nivel " + nivel + ": ");
            for (int i = 0; i < tamano; i++) {
                NodoArbol actual = cola.poll();
                System.out.print(actual.getLugar().getNombre());
                if (i < tamano - 1) System.out.print(" → ");
                cola.addAll(actual.getHijos());
            }
            System.out.println();
            nivel++;
        }
        System.out.println("─────────────────────────────────────────────");
    }
}
