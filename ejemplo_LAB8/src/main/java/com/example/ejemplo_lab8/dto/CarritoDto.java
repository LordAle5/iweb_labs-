package com.example.ejemplo_lab8.dto;


// DTO para listar el carrito con datos de producto y usuario
public class CarritoDto {
    private int idItem;
    private int idProducto;
    private String nombreProducto;
    private String nombreUsuario;
    private double precioUnit;
    private int cantidad;
    private double subtotal; // ← calculado: precio * cantidad

    public CarritoDto() {}

    public int getIdItem()              { return idItem; }
    public int getIdProducto()          { return idProducto; }
    public String getNombreProducto()   { return nombreProducto; }
    public String getNombreUsuario()    { return nombreUsuario; }
    public double getPrecioUnit()       { return precioUnit; }
    public int getCantidad()            { return cantidad; }
    public double getSubtotal()         { return subtotal; }

    public void setIdItem(int id)               { this.idItem = id; }
    public void setIdProducto(int id)           { this.idProducto = id; }
    public void setNombreProducto(String n)     { this.nombreProducto = n; }
    public void setNombreUsuario(String n)      { this.nombreUsuario = n; }
    public void setPrecioUnit(double p)         { this.precioUnit = p; }
    public void setCantidad(int c)              { this.cantidad = c; }
    public void setSubtotal(double s)           { this.subtotal = s; }
}