package com.example.ejemplo_lab7.beans;

public class Especie {
    private int idEspecie;
    private String nombre;

    public Especie() {}

    public int getIdEspecie()        { return idEspecie; }
    public String getNombre()        { return nombre; }
    public void setIdEspecie(int id) { this.idEspecie = id; }
    public void setNombre(String n)  { this.nombre = n; }
}