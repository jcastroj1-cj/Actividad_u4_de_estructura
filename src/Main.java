import modelo.LugarTuristico;
import arbol.ArbolTuristico;
import Grafo.GrafoTuristico;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArbolTuristico arbol = new ArbolTuristico();
    private static GrafoTuristico grafo = new GrafoTuristico();

    public static void main(String[] args) {
        cargarDatosPrueba();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("\n👋 Hasta luego!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║        🗺️  SISTEMA DE MAPA TURÍSTICO             ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  --- ÁRBOL ---                                   ║");
        System.out.println("║  1.  Insertar raíz del árbol                     ║");
        System.out.println("║  2.  Insertar lugar hijo                         ║");
        System.out.println("║  3.  Consultar lugar (árbol)                     ║");
        System.out.println("║  4.  Actualizar lugar (árbol)                    ║");
        System.out.println("║  5.  Eliminar lugar (árbol)                      ║");
        System.out.println("║  6.  Recorrido Pre-Orden (DFS)                   ║");
        System.out.println("║  7.  Recorrido por niveles (BFS)                 ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  --- GRAFO ---                                   ║");
        System.out.println("║  8.  Agregar lugar al grafo                      ║");
        System.out.println("║  9.  Agregar ruta entre lugares                  ║");
        System.out.println("║  10. Consultar lugar (grafo)                     ║");
        System.out.println("║  11. Actualizar lugar (grafo)                    ║");
        System.out.println("║  12. Eliminar lugar (grafo)                      ║");
        System.out.println("║  13. Recorrido DFS (grafo)                       ║");
        System.out.println("║  14. Recorrido BFS (grafo)                       ║");
        System.out.println("║  15. Ver mapa completo                           ║");
        System.out.println("║  16. Ruta más corta (Dijkstra)                   ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  0.  Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1  -> insertarRaiz();
            case 2  -> insertarHijo();
            case 3  -> consultarArbol();
            case 4  -> actualizarArbol();
            case 5  -> eliminarArbol();
            case 6  -> arbol.recorridoPreOrden();
            case 7  -> arbol.recorridoPorNiveles();
            case 8  -> agregarLugarGrafo();
            case 9  -> agregarRuta();
            case 10 -> consultarGrafo();
            case 11 -> actualizarGrafo();
            case 12 -> eliminarGrafo();
            case 13 -> dfsGrafo();
            case 14 -> bfsGrafo();
            case 15 -> grafo.mostrarGrafo();
            case 16 -> rutaMasCorta();
            case 0  -> {}
            default -> System.out.println("⚠️  Opción inválida.");
        }
    }

    // ─────────────────────────────────────────────
    // MÉTODOS ÁRBOL
    // ─────────────────────────────────────────────
    private static void insertarRaiz() {
        System.out.println("\n── INSERTAR RAÍZ ──");
        LugarTuristico lugar = pedirDatosLugar();
        arbol.insertarRaiz(lugar);
    }

    private static void insertarHijo() {
        System.out.println("\n── INSERTAR LUGAR HIJO ──");
        String codigoPadre = leerTexto("Código del lugar padre: ");
        LugarTuristico lugar = pedirDatosLugar();
        arbol.insertarHijo(codigoPadre, lugar);
    }

    private static void consultarArbol() {
        String codigo = leerTexto("\nCódigo del lugar a consultar: ");
        arbol.consultar(codigo);
    }

    private static void actualizarArbol() {
        System.out.println("\n── ACTUALIZAR LUGAR ──");
        String codigo      = leerTexto("Código del lugar a actualizar: ");
        String nuevoNombre = leerTexto("Nuevo nombre: ");
        String nuevoTipo   = leerTexto("Nuevo tipo (PLAYA/MUSEO/PARQUE/MONUMENTO/RESERVA): ");
        String nuevaDesc   = leerTexto("Nueva descripción: ");
        arbol.actualizar(codigo, nuevoNombre, nuevoTipo, nuevaDesc);
    }

    private static void eliminarArbol() {
        String codigo = leerTexto("\nCódigo del lugar a eliminar: ");
        arbol.eliminar(codigo);
    }

    // ─────────────────────────────────────────────
    // MÉTODOS GRAFO
    // ─────────────────────────────────────────────
    private static void agregarLugarGrafo() {
        System.out.println("\n── AGREGAR LUGAR AL GRAFO ──");
        LugarTuristico lugar = pedirDatosLugar();
        grafo.agregarLugar(lugar);
    }

    private static void agregarRuta() {
        System.out.println("\n── AGREGAR RUTA ──");
        String origen   = leerTexto("Código lugar origen: ");
        String destino  = leerTexto("Código lugar destino: ");
        double distancia = leerDouble("Distancia en km: ");
        grafo.agregarRuta(origen, destino, distancia);
    }

    private static void consultarGrafo() {
        String codigo = leerTexto("\nCódigo del lugar a consultar: ");
        grafo.consultarLugar(codigo);
    }

    private static void actualizarGrafo() {
        System.out.println("\n── ACTUALIZAR LUGAR EN GRAFO ──");
        String codigo      = leerTexto("Código del lugar a actualizar: ");
        String nuevoNombre = leerTexto("Nuevo nombre: ");
        String nuevoTipo   = leerTexto("Nuevo tipo: ");
        String nuevaDesc   = leerTexto("Nueva descripción: ");
        grafo.actualizarLugar(codigo, nuevoNombre, nuevoTipo, nuevaDesc);
    }

    private static void eliminarGrafo() {
        String codigo = leerTexto("\nCódigo del lugar a eliminar: ");
        grafo.eliminarLugar(codigo);
    }

    private static void dfsGrafo() {
        String codigo = leerTexto("\nCódigo del lugar de inicio para DFS: ");
        grafo.dfs(codigo);
    }

    private static void bfsGrafo() {
        String codigo = leerTexto("\nCódigo del lugar de inicio para BFS: ");
        grafo.bfs(codigo);
    }

    private static void rutaMasCorta() {
        System.out.println("\n── RUTA MÁS CORTA (DIJKSTRA) ──");
        String origen  = leerTexto("Código lugar origen: ");
        String destino = leerTexto("Código lugar destino: ");
        grafo.rutaMasCorta(origen, destino);
    }

    // ─────────────────────────────────────────────
    // DATOS DE PRUEBA
    // ─────────────────────────────────────────────
    private static void cargarDatosPrueba() {
        System.out.println("⏳ Cargando datos de prueba...");

        // Árbol
        arbol.insertarRaiz(new LugarTuristico("L001", "Colombia", "PAÍS", "Nacional", "País de destino turístico"));
        arbol.insertarHijo("L001", new LugarTuristico("L002", "Cartagena", "CIUDAD", "Cartagena", "Ciudad histórica"));
        arbol.insertarHijo("L001", new LugarTuristico("L003", "Medellín", "CIUDAD", "Medellín", "Ciudad de la eterna primavera"));
        arbol.insertarHijo("L002", new LugarTuristico("L004", "Murallas de Cartagena", "MONUMENTO", "Cartagena", "Patrimonio histórico"));
        arbol.insertarHijo("L003", new LugarTuristico("L005", "El Poblado", "PARQUE", "Medellín", "Zona turística"));

        // Grafo
        grafo.agregarLugar(new LugarTuristico("G001", "Islas del Rosario", "PLAYA", "Cartagena", "Archipiélago turístico"));
        grafo.agregarLugar(new LugarTuristico("G002", "Parque Tayrona", "RESERVA", "Santa Marta", "Parque natural"));
        grafo.agregarLugar(new LugarTuristico("G003", "Valle del Cocora", "RESERVA", "Salento", "Valle de palmas de cera"));
        grafo.agregarLugar(new LugarTuristico("G004", "Guatapé", "MONUMENTO", "Guatapé", "Peñol y embalse"));
        grafo.agregarRuta("G001", "G002", 210.5);
        grafo.agregarRuta("G002", "G003", 580.0);
        grafo.agregarRuta("G003", "G004", 95.0);
        grafo.agregarRuta("G001", "G004", 650.0);

        System.out.println("✅ Datos de prueba cargados.\n");
    }

    // ─────────────────────────────────────────────
    // UTILIDADES
    // ─────────────────────────────────────────────
    private static LugarTuristico pedirDatosLugar() {
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String tipo   = leerTexto("Tipo (PLAYA/MUSEO/PARQUE/MONUMENTO/RESERVA): ").toUpperCase();
        String ciudad = leerTexto("Ciudad: ");
        String desc   = leerTexto("Descripción: ");
        return new LugarTuristico(codigo, nombre, tipo, ciudad, desc);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Ingrese un número válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Ingrese un número válido.");
            }
        }
    }
}