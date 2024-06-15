package Objets;

/**
 * Objeto para almacenar los datos del contacto
 *
 * @autor jaime
 */
public class Contacto {

    /**
     * Número de teléfono
     */
    private String telefono;

    /**
     * Correo electrónico
     */
    public String correo;

    public Contacto() {
    }

    public Contacto(String telefono, String correo) {
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}