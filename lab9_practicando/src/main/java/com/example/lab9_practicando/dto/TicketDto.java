package com.example.lab9_practicando.dto;

// Para listar tickets con info del evento — exacto a lo pedido
public class TicketDto {
    private int idTicketTipo;
    private String tituloEvento;
    private String descripcionEvento;
    private String fechaEvento;
    private String lugarEvento;
    private String nombreTicket;
    private double precio;
    private int cupoDisponible;

    public TicketDto() {}

    public int getIdTicketTipo()        { return idTicketTipo; }
    public String getTituloEvento()     { return tituloEvento; }
    public String getDescripcionEvento(){ return descripcionEvento; }
    public String getFechaEvento()      { return fechaEvento; }
    public String getLugarEvento()      { return lugarEvento; }
    public String getNombreTicket()     { return nombreTicket; }
    public double getPrecio()           { return precio; }
    public int getCupoDisponible()      { return cupoDisponible; }

    public void setIdTicketTipo(int id)            { this.idTicketTipo = id; }
    public void setTituloEvento(String t)          { this.tituloEvento = t; }
    public void setDescripcionEvento(String d)     { this.descripcionEvento = d; }
    public void setFechaEvento(String f)           { this.fechaEvento = f; }
    public void setLugarEvento(String l)           { this.lugarEvento = l; }
    public void setNombreTicket(String n)          { this.nombreTicket = n; }
    public void setPrecio(double p)                { this.precio = p; }
    public void setCupoDisponible(int c)           { this.cupoDisponible = c; }
}