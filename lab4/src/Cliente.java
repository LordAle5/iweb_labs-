import java.security.PrivateKey;
import java.util.PrimitiveIterator;
import java.util.ArrayList;

public class Cliente {

    private static int contadorID=1;

    private int ID;
    private String nombre;
    private String DNI;
    private String correo;
    private String telefono;
    private ArrayList<Mascota> mascotasAdoptadas;

    public Cliente(String nombre, String dni, String correo, String telefono) {
        this.ID = contadorID++;
        this.nombre = nombre;
        this.DNI = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.mascotasAdoptadas = new ArrayList<>();
    }

    public int getId() { return ID; }
    public String getNombre() { return nombre; }
    public String getDni() { return DNI; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public ArrayList<Mascota> getMascotasAdoptadas(){return mascotasAdoptadas;}

    public void agregarMascota (Mascota m){
        mascotasAdoptadas.add(m);
    }

    public void mostrarInfo(){
        System.out.println("  ID       : " + ID);
        System.out.println("  Nombre   : " + nombre);
        System.out.println("  DNI      : " + DNI);
        System.out.println("  Correo   : " + correo);
        System.out.println("  Teléfono : " + telefono);

        if (!mascotasAdoptadas.isEmpty()) {
            System.out.println("  Mascotas adoptadas:");
            for (Mascota m : mascotasAdoptadas) {
                System.out.println("  --------");
                m.mostrarInfo();
            }
        }

    }
}
