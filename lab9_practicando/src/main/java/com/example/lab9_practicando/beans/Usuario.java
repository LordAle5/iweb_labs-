package com.example.lab9_practicando.beans;

public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String email;

    public Usuario() {}

    public int getIdUsuario()    { return idUsuario; }
    public String getNombres()   { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getEmail()     { return email; }

    public void setIdUsuario(int id)     { this.idUsuario = id; }
    public void setNombres(String n)     { this.nombres = n; }
    public void setApellidos(String a)   { this.apellidos = a; }
    public void setEmail(String e)       { this.email = e; }
}