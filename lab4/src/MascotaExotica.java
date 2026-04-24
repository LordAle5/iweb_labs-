public class MascotaExotica extends Mascota {

    private String paisOrigen;

    public MascotaExotica (String nombre, String especie, int edad, String paisOrigen){
        super (nombre,especie, edad);
        this.paisOrigen = paisOrigen;
    }

    public String getPaisOrigen() { return paisOrigen; }

    @Override
    public void mostrarInfo() {
        System.out.println("  Tipo    : Exótica");
        super.mostrarInfo();
        System.out.println("  País    : " + paisOrigen);
    }
}
