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

    public void agregarLugar(LugarTuristico lugar) {
        String key = lugar.getCodigo().toUpperCase();
        if (nodos.containsKey(key)) {
            System.out.println("❌ Ya existe un lugar con código: " + lugar.getCodigo());
            return;
        }
        nodos.put(key, lugar);
        adyacencia.put(key, new ArrayList<>());
        System.out.println("✅ Lugar agregado al grafo: " + lugar.getNombre());
    }

    public void consultarLugar(String codigo) {
        LugarTuristico lugar = nodos.get(codigo.toUpperCase());
        if (lugar == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }
        System.out.println("\n🔍 LUGAR ENCONTRADO:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println(lugar);
        List<Arista> conexiones = adyacencia.get(codigo.toUpperCase());
        if (conexiones.isEmpty()) {
            System.out.println("Sin rutas conectadas.");
        } else {
            System.out.println("Rutas desde aquí:");
            conexiones.forEach(a -> System.out.println("   → " +
                    nodos.get(a.destino).getNombre() + " (" + a.distanciaKm + " km)"));
        }
        System.out.println("─────────────────────────────────────────────");
    }

    public void actualizarLugar(String codigo, String nuevoNombre, String nuevoTipo, String nuevaDescripcion) {
        LugarTuristico lugar = nodos.get(codigo.toUpperCase());
        if (lugar == null) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }
        lugar.setNombre(nuevoNombre);
        lugar.setTipo(nuevoTipo);
        lugar.setDescripcion(nuevaDescripcion);
        System.out.println("✅ Lugar actualizado en el grafo.");
        System.out.println(lugar);
    }

    public void eliminarLugar(String codigo) {
        String key = codigo.toUpperCase();
        if (!nodos.containsKey(key)) {
            System.out.println("❌ No se encontró un lugar con código: " + codigo);
            return;
        }
        nodos.remove(key);
        adyacencia.remove(key);
        adyacencia.values().forEach(lista -> lista.removeIf(a -> a.destino.equals(key)));
        System.out.println("✅ Lugar eliminado del grafo: " + codigo);
    }

    // ─── CRUD ARISTAS ────────────────────────────────────────

    public void agregarRuta(String codigoOrigen, String codigoDestino, double distanciaKm) {
        String o = codigoOrigen.toUpperCase();
        String d = codigoDestino.toUpperCase();
        if (!nodos.containsKey(o) || !nodos.containsKey(d)) {
            System.out.println("❌ Uno o ambos lugares no existen en el grafo.");
            return;
        }
        adyacencia.get(o).add(new Arista(d, distanciaKm));
        adyacencia.get(d).add(new Arista(o, distanciaKm));
        System.out.println("✅ Ruta agregada: " + nodos.get(o).getNombre() +
                " ↔ " + nodos.get(d).getNombre() + " (" + distanciaKm + " km)");
    }

    public void eliminarRuta(String codigoOrigen, String codigoDestino) {
        String o = codigoOrigen.toUpperCase();
        String d = codigoDestino.toUpperCase();
        if (!nodos.containsKey(o) || !nodos.containsKey(d)) {
            System.out.println("❌ Uno o ambos lugares no existen en el grafo.");
            return;
        }
        adyacencia.get(o).removeIf(a -> a.destino.equals(d));
        adyacencia.get(d).removeIf(a -> a.destino.equals(o));
        System.out.println("✅ Ruta eliminada entre " + nodos.get(o).getNombre() +
                " y " + nodos.get(d).getNombre());
    }

    // ─── RECORRIDOS ─────────────────────────────────────────

    public void bfs(String codigoInicio) {
        String inicio = codigoInicio.toUpperCase();
        if (!nodos.containsKey(inicio)) {
            System.out.println("❌ Lugar de inicio no encontrado.");
            return;
        }
        System.out.println("\n🗺️  RECORRIDO BFS desde: " + nodos.get(inicio).getNombre());
        System.out.println("─────────────────────────────────────────────");

        Set<String> visitados = new LinkedHashSet<>();
        Queue<String> cola = new LinkedList<>();
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            System.out.println("  📍 " + nodos.get(actual).getNombre()
                    + " [" + nodos.get(actual).getTipo() + "]");
            for (Arista arista : adyacencia.get(actual)) {
                if (!visitados.contains(arista.destino)) {
                    visitados.add(arista.destino);
                    cola.add(arista.destino);
                }
            }
        }
        System.out.println("─────────────────────────────────────────────");
    }

    public void dfs(String codigoInicio) {
        String inicio = codigoInicio.toUpperCase();
        if (!nodos.containsKey(inicio)) {
            System.out.println("❌ Lugar de inicio no encontrado.");
            return;
        }
        System.out.println("\n🔎 RECORRIDO DFS desde: " + nodos.get(inicio).getNombre());
        System.out.println("─────────────────────────────────────────────");
        Set<String> visitados = new LinkedHashSet<>();
        dfsRecursivo(inicio, visitados);
        System.out.println("─────────────────────────────────────────────");
    }

    private void dfsRecursivo(String codigo, Set<String> visitados) {
        visitados.add(codigo);
        System.out.println("  📍 " + nodos.get(codigo).getNombre()
                + " [" + nodos.get(codigo).getTipo() + "]");
        for (Arista arista : adyacencia.get(codigo)) {
            if (!visitados.contains(arista.destino)) {
                dfsRecursivo(arista.destino, visitados);
            }
        }
    }

    // Ruta más corta con Dijkstra
    public void rutaMasCorta(String codigoOrigen, String codigoDestino) {
        String o = codigoOrigen.toUpperCase();
        String d = codigoDestino.toUpperCase();
        if (!nodos.containsKey(o) || !nodos.containsKey(d)) {
            System.out.println("❌ Uno o ambos lugares no existen.");
            return;
        }

        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> previo = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        for (String nodo : nodos.keySet()) distancias.put(nodo, Double.MAX_VALUE);
        distancias.put(o, 0.0);
        pq.add(o);

        while (!pq.isEmpty()) {
            String actual = pq.poll();
            if (actual.equals(d)) break;
            for (Arista arista : adyacencia.get(actual)) {
                double nuevaDist = distancias.get(actual) + arista.distanciaKm;
                if (nuevaDist < distancias.get(arista.destino)) {
                    distancias.put(arista.destino, nuevaDist);
                    previo.put(arista.destino, actual);
                    pq.add(arista.destino);
                }
            }
        }

        if (distancias.get(d) == Double.MAX_VALUE) {
            System.out.println("❌ No existe ruta entre " + o + " y " + d);
            return;
        }

        LinkedList<String> camino = new LinkedList<>();
        for (String nodo = d; nodo != null; nodo = previo.get(nodo)) camino.addFirst(nodo);

        System.out.println("\n🛣️  RUTA MÁS CORTA:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("De: " + nodos.get(o).getNombre() + " → " + nodos.get(d).getNombre());
        System.out.print("Camino: ");
        System.out.println(String.join(" → ", camino.stream().map(c -> nodos.get(c).getNombre()).toList()));
        System.out.printf("Distancia total: %.1f km%n", distancias.get(d));
        System.out.println("─────────────────────────────────────────────");
    }

    public void mostrarGrafo() {
        if (estaVacio()) { System.out.println("❌ El grafo está vacío."); return; }
        System.out.println("\n🌐 MAPA TURÍSTICO (GRAFO COMPLETO):");
        System.out.println("─────────────────────────────────────────────");
        nodos.forEach((codigo, lugar) -> {
            System.out.print("📍 " + lugar.getNombre());
            List<Arista> conexiones = adyacencia.get(codigo);
            if (conexiones.isEmpty()) {
                System.out.println(" (sin conexiones)");
            } else {
                System.out.print(" → ");
                conexiones.forEach(a ->
                        System.out.print(nodos.get(a.destino).getNombre() +
                                "(" + a.distanciaKm + "km) "));
                System.out.println();
            }
        });
        System.out.println("─────────────────────────────────────────────");
    }

    // ─── CLASE INTERNA ARISTA ───────────────────────────────

    private static class Arista {
        String destino;
        double distanciaKm;

        Arista(String destino, double distanciaKm) {
            this.destino = destino;
            this.distanciaKm = distanciaKm;
        }
    }
}