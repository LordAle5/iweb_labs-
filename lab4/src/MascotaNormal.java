public class MascotaNormal extends Mascota {

    public MascotaNormal (String nombre, String especie, int edad){
        super(nombre, especie, edad);
    }

    @Override
    public void mostrarInfo(){
        System.out.println("  Tipo    : Normal");
        super.mostrarInfo();
    }
}
