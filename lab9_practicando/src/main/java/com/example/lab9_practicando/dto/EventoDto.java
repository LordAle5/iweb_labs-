package com.example.lab9_practicando.dto;

// Para listar eventos — campos exactos que pide el lab
public class EventoDto {
    private int idEvento;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String lugar;

    public EventoDto() {}

    public int getIdEvento()       { return idEvento; }
    public String getTitulo()      { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getFecha()       { return fecha; }
    public String getLugar()       { return lugar; }

    public void setIdEvento(int id)      { this.idEvento = id; }
    public void setTitulo(String t)      { this.titulo = t; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public void setFecha(String f)       { this.fecha = f; }
    public void setLugar(String l)       { this.lugar = l; }
}