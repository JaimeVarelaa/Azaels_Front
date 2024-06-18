package HTTP;

/**
 * Constantes para realizar la conexión HTTP
 */
public class Constantes {

    /**
     * URL base del servidor
     */
    //public static String url = "http://localhost:3000/";
    public static String url = "https://back-one-zeta.vercel.app/";

    /**
     * Puerto del servidor
     */
    public static String port = "3000/";

    /**
     * Rutas para obtener los datos
     */
    public enum rutas {
        clientes,
        compras,
        creditos,
        empleados,
        gastos,
        inventario,
        productos,
        puestos,
        tiendas,
        stock,
        ventas
    }
}
