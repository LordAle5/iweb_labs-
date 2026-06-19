package com.example.lab9_practicando.beans;

public class TicketTipo {
    private int idTicketTipo;
    private int idEvento;
    private String nombre;
    private double precio;
    private int cupoTotal;
    private int cupoDisponible;

    public TicketTipo() {}

    public int getIdTicketTipo()     { return idTicketTipo; }
    public int getIdEvento()         { return idEvento; }
    public String getNombre()        { return nombre; }
    public double getPrecio()        { return precio; }
    public int getCupoTotal()        { return cupoTotal; }
    public int getCupoDisponible()   { return cupoDisponible; }

    public void setIdTicketTipo(int id)     { this.idTicketTipo = id; }
    public void setIdEvento(int id)         { this.idEvento = id; }
    public void setNombre(String n)         { this.nombre = n; }
    public void setPrecio(double p)         { this.precio = p; }
    public void setCupoTotal(int c)         { this.cupoTotal = c; }
    public void setCupoDisponible(int c)    { this.cupoDisponible = c; }
}