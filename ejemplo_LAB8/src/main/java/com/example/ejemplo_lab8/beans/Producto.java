package com.example.ejemplo_lab8.beans;


public class Producto {
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Producto() {}

    public int getIdProducto()      { return idProducto; }
    public int getIdCategoria()     { return idCategoria; }
    public String getNombre()       { return nombre; }
    public String getDescripcion()  { return descripcion; }
    public double getPrecio()       { return precio; }
    public int getStock()           { return stock; }

    public void setIdProducto(int id)        { this.idProducto = id; }
    public void setIdCategoria(int id)       { this.idCategoria = id; }
    public void setNombre(String n)          { this.nombre = n; }
    public void setDescripcion(String d)     { this.descripcion = d; }
    public void setPrecio(double p)          { this.precio = p; }
    public void setStock(int s)              { this.stock = s; }
}
