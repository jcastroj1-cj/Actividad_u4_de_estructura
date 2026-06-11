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
    // GETTERS
    public String getCodigo()      { return codigo; }
    public String getNombre()      { return nombre; }
    public String getTipo()        { return tipo; }
    public String getCiudad()      { return ciudad; }
    public String getDescripcion() { return descripcion; }

    // SETTERS
    public void setCodigo(String codigo)           { this.codigo = codigo; }
    public void setNombre(String nombre)           { this.nombre = nombre; }
    public void setTipo(String tipo)               { this.tipo = tipo; }
    public void setCiudad(String ciudad)           { this.ciudad = ciudad; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "Código: " + codigo +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Ciudad: " + ciudad +
                " | Descripción: " + descripcion;
    }
    Cuando lo tengas, agrega los últimos dos métodos equals y hashCode. ¿Listo?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LugarTuristico)) return false;
        LugarTuristico that = (LugarTuristico) o;
        return codigo.equalsIgnoreCase(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.toUpperCase().hashCode();
    }

}