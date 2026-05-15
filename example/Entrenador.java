package org.example;

public class Entrenador {

    private int idEntrenador;
    private String nombre;
    private String especialidad;
    private String telefono;

    public Entrenador(int idEntrenador, String nombre,
                      String especialidad, String telefono) {
        this.idEntrenador = idEntrenador;
        this.nombre       = nombre;
        this.especialidad = especialidad;
        this.telefono     = telefono;
    }

    public int getIdEntrenador()    { return idEntrenador; }
    public String getNombre()       { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public String getTelefono()     { return telefono; }
}