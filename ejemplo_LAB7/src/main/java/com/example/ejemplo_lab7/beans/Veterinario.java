package com.example.ejemplo_lab7.beans;

public class Veterinario {
    private int idVeterinario;
    private String nombre;
    private String especialidad;

    public Veterinario() {}

    public int getIdVeterinario()         { return idVeterinario; }
    public String getNombre()             { return nombre; }
    public String getEspecialidad()       { return especialidad; }
    public void setIdVeterinario(int id)  { this.idVeterinario = id; }
    public void setNombre(String n)       { this.nombre = n; }
    public void setEspecialidad(String e) { this.especialidad = e; }
}