package com.example.lab9_practicando.dto;

// Para listar reservas con info del evento, usuario y ticket
public class ReservaDto {
    private int idItem;
    private String tituloEvento;
    private String fechaEvento;
    private String nombres;
    private String apellidos;
    private String email;
    private String nombreTicket;
    private int cantidad;

    public ReservaDto() {}

    public int getIdItem()           { return idItem; }
    public String getTituloEvento()  { return tituloEvento; }
    public String getFechaEvento()   { return fechaEvento; }
    public String getNombres()       { return nombres; }
    public String getApellidos()     { return apellidos; }
    public String getEmail()         { return email; }
    public String getNombreTicket()  { return nombreTicket; }
    public int getCantidad()         { return cantidad; }

    public void setIdItem(int id)           { this.idItem = id; }
    public void setTituloEvento(String t)   { this.tituloEvento = t; }
    public void setFechaEvento(String f)    { this.fechaEvento = f; }
    public void setNombres(String n)        { this.nombres = n; }
    public void setApellidos(String a)      { this.apellidos = a; }
    public void setEmail(String e)          { this.email = e; }
    public void setNombreTicket(String n)   { this.nombreTicket = n; }
    public void setCantidad(int c)          { this.cantidad = c; }
}