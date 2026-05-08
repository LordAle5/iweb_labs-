import java.time.LocalDate;

public class Item {

    private String Id;
    private String Tipo;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private double Volumen;
    private double Peso;
    private Bodega bodega;

    public Item (String Id,String Tipo,LocalDate fechaIngreso,LocalDate fechaSalida,double Volumen, double Peso,Bodega bodega ){
        this.Id = Id;
        this.Tipo = Tipo;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.Volumen = Volumen;
        this.Peso = Peso;
        this.bodega = bodega;
    }

    public String getId(){return Id;}
    public String getTipo(){return Tipo;}
    public LocalDate getFechaIngreso(){return fechaIngreso;}
    public LocalDate getFechaSalida(){return fechaSalida;}
    public double getVolumen(){return Volumen;}
    public double getPeso(){return Peso;}
    public Bodega getbodega(){return bodega;}

}
