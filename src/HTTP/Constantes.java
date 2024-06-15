package HTTP;

/**
 * Constantes para realizar la conexión HTTP
 */
public class Constantes {

    /**
     * URL base del servidor
     */
    public static String url = "https://back-copy-bdd.vercel.app/";

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
