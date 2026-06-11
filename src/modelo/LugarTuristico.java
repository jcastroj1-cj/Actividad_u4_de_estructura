package modelo;


public class LugarTuristico {

    private String codigo;
    private String nombre;
    private String tipo;     // PLAYA, MUSEO, PARQUE, MONUMENTO, RESERVA
    private String ciudad;
    private String descripcion;

    public LugarTuristico(String codigo, String nombre, String tipo,
                          String ciudad, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
    }
}