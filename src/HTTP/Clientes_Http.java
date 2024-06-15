package HTTP;

import GUI.MainMenuGUI;
import Objets.Cliente;
import Objets.Contacto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Realización de solicitudes HTTP referente a cliente
 *
 * @autor jaime
 */
public class Clientes_Http {

    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Obtención de todos los clientes
     *
     * @param id
     * @return
     */
    public static Cliente getClientes4ID(String id) {
        Cliente cliente = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.clientes.name() + "/" + id))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                cliente = responseToCliente(response);
            } else {
                System.err.println("Error al obtener el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    /**
     * Obtención de todos los clientes
     *
     */
    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Constantes.url + Constantes.rutas.clientes.name()))
                        .timeout(Duration.ofMinutes(1))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("_id");
                        Cliente cliente = getClientes4ID(id);
                        if (cliente != null) {
                            clientes.add(cliente);
                            MainMenuGUI.fillTablaClientes(cliente);
                        }
                    }
                } else {
                    System.err.println("Error al obtener los clientes: " + response.statusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        thread.start();
        return clientes;
    }

    private static Cliente responseToCliente(HttpResponse<String> response) {
        Contacto contacto = null;

        String id = null;
        String nombre = null;
        int edad = -1;
        String sexo = null;

        String telefono = null;
        String correo = null;

        String fechaNacimiento = null;
        String domicilio = null;

        JSONObject jsonObject = new JSONObject(response.body());
        id = jsonObject.getString("_id");
        nombre = jsonObject.getString("nombre");
        edad = jsonObject.getInt("edad");
        sexo = jsonObject.getString("sexo");
        fechaNacimiento = jsonObject.getString("fecha_nacimiento");

        JSONObject contactoj = jsonObject.getJSONObject("contacto");
        telefono = contactoj.getString("telefono");
        correo = contactoj.getString("correo");

        JSONObject direccionj = jsonObject.getJSONObject("direccion");
        domicilio = direccionj.getString("domicilio");

        contacto = new Contacto(telefono, correo);

        return new Cliente(id, nombre, edad, sexo, contacto, fechaNacimiento, domicilio);
    }
}
