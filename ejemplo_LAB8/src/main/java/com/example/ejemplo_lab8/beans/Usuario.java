package com.example.ejemplo_lab8.beans;

public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;

    public Usuario() {}

    public int getIdUsuario()      { return idUsuario; }
    public String getNombres()     { return nombres; }
    public String getApellidos()   { return apellidos; }
    public String getEmail()       { return email; }
    public String getEstado()      { return estado; }

    // Método útil para mostrar nombre completo en el Navbar
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public void setIdUsuario(int id)        { this.idUsuario = id; }
    public void setNombres(String n)        { this.nombres = n; }
    public void setApellidos(String a)      { this.apellidos = a; }
    public void setEmail(String e)          { this.email = e; }
    public void setEstado(String e)         { this.estado = e; }
}