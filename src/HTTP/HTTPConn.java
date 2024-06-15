package HTTP;

import java.net.http.HttpClient;

/**
 * Creación del cliente HTTP
 *
 * @author jaime
 */
public class HTTPConn {

    /**
     * Retorna el cliente HTTP
     *
     * @return cliente
     */
    public static HttpClient getHTTPCliente() {
        HttpClient cliente = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        return cliente;
    }
}
