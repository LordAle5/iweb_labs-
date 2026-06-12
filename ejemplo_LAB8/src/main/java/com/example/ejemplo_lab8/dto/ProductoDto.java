package com.example.ejemplo_lab8.dto;

// DTO para listar productos con nombre de categoría
// y stock calculado (stock - lo que hay en carrito)
public class ProductoDto {
    private int id;
    private String nombre;
    private String categoriaNombre; // ← viene del JOIN con categoria
    private double precio;
    private int stock;              // ← calculado: stock - cantidad en carrito

    public ProductoDto() {}

    public int getId()                  { return id; }
    public String getNombre()           { return nombre; }
    public String getCategoriaNombre()  { return categoriaNombre; }
    public double getPrecio()           { return precio; }
    public int getStock()               { return stock; }

    public void setId(int id)                       { this.id = id; }
    public void setNombre(String n)                 { this.nombre = n; }
    public void setCategoriaNombre(String c)        { this.categoriaNombre = c; }
    public void setPrecio(double p)                 { this.precio = p; }
    public void setStock(int s)                     { this.stock = s; }
}