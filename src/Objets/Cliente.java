package Objets;

/**
 * Objeto para almacenar la información de los clientes
 *
 * @author jaime
 */
public class Cliente {

    /**
     * ID del cliente
     */
    private String id;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Edad del cliente
     */
    private int edad;

    /**
     * Sexo del cliente
     */
    private String sexo;

    /**
     * Datos de contacto del cliente
     */
    private Contacto contacto;

    /**
     * Fecha de nacimiento del cliente
     */
    private String fechaNacimiento;

    /**
     * Domicilio del cliente
     */
    private String domicilio;

    /**
     *
     * @param id
     * @param nombre
     * @param edad
     * @param sexo
     * @param contacto
     * @param fecha_nacimiento
     * @param domicilio
     */
    public Cliente(String id, String nombre, int edad, String sexo, Contacto contacto, String fecha_nacimiento, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.contacto = contacto;
        this.fechaNacimiento = fecha_nacimiento;
        this.domicilio = domicilio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
