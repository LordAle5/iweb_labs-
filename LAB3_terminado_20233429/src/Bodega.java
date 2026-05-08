import java.util.ArrayList;

public class Bodega {

    private String Id;
    private String Nombre;
    private String Ubicacion;
    private int Capacidad;
    private ArrayList<Item> listaItem;
    private double costoXDiaMt;

    public Bodega (String Id,String Nombre,String Ubicacion,int Capacidad,double costoXDiaMt){

        this.Id = Id;
        this.Nombre = Nombre;
        this.Ubicacion = Ubicacion;
        this.Capacidad =Capacidad;
        this.costoXDiaMt = costoXDiaMt;
        this.listaItem= new ArrayList<>();
    }

    public String getId(){return Id;}
    public String getNombre(){return Nombre;}
    public String getUbicacion(){return Ubicacion;}
    public int getCapacidad(){return Capacidad;}
    public ArrayList<Item> getListaItem(){return listaItem;}
    public double getCostoXDiaMt(){return costoXDiaMt;}

    public void agregarItem(Item item){
        listaItem.add(item);
    }
}
