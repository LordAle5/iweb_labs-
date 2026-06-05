
package com.example.ejemplo_lab7.beans;

public class Mascota {
    private int idMascota;
    private String nombre;
    private int edad;
    private double peso;

    // Bean dentro de Bean → para mostrar nombres en vez de IDs
    private Especie especie;
    private Veterinario veterinario;
    private Dueno dueno;

    public Mascota() {}

    public int getIdMascota()           { return idMascota; }
    public String getNombre()           { return nombre; }
    public int getEdad()                { return edad; }
    public double getPeso()             { return peso; }
    public Especie getEspecie()         { return especie; }
    public Veterinario getVeterinario() { return veterinario; }
    public Dueno getDueno()             { return dueno; }

    public void setIdMascota(int id)          { this.idMascota = id; }
    public void setNombre(String n)           { this.nombre = n; }
    public void setEdad(int e)                { this.edad = e; }
    public void setPeso(double p)             { this.peso = p; }
    public void setEspecie(Especie e)         { this.especie = e; }
    public void setVeterinario(Veterinario v) { this.veterinario = v; }
    public void setDueno(Dueno d)             { this.dueno = d; }
}