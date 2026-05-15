package org.example;

public class Clase {

    private int idClase;
    private String nombreClase;
    private int idEntrenador;
    private int cuposDisponibles;
    private double precio;

    public Clase(int idClase, String nombreClase, int idEntrenador,
                 int cuposDisponibles, double precio) {
        this.idClase          = idClase;
        this.nombreClase      = nombreClase;
        this.idEntrenador     = idEntrenador;
        this.cuposDisponibles = cuposDisponibles;
        this.precio           = precio;
    }

    public int getIdClase()           { return idClase; }
    public String getNombreClase()    { return nombreClase; }
    public int getIdEntrenador()      { return idEntrenador; }
    public int getCuposDisponibles()  { return cuposDisponibles; }
    public double getPrecio()         { return precio; }
}