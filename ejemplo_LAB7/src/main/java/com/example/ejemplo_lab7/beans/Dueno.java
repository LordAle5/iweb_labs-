package com.example.ejemplo_lab7.beans;

public class Dueno {
    private int idDueno;
    private String nombre;
    private String telefono;

    public Dueno() {}

    public int getIdDueno()          { return idDueno; }
    public String getNombre()        { return nombre; }
    public String getTelefono()      { return telefono; }
    public void setIdDueno(int id)   { this.idDueno = id; }
    public void setNombre(String n)  { this.nombre = n; }
    public void setTelefono(String t){ this.telefono = t; }
}