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
                        Cliente cliente = responseToCliente(jsonObject);
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
    
    
    private static Cliente responseToCliente(JSONObject jsonObject) {
        Contacto contacto = null;

        String id = null;
        String nombre = null;
        int edad = -1;
        String sexo = null;

        String telefono = null;
        String correo = null;

        String fechaNacimiento = null;
        String domicilio = null;

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
    
    public static boolean addCliente(Cliente cliente) {
    try {
        JSONObject json = new JSONObject();
        json.put("nombre", cliente.getNombre());
        json.put("edad", cliente.getEdad());
        json.put("sexo", cliente.getSexo());
        json.put("fecha_nacimiento", cliente.getFechaNacimiento());
        
        JSONObject contacto = new JSONObject();
        contacto.put("telefono", cliente.getContacto().getTelefono());
        contacto.put("correo", cliente.getContacto().getCorreo());
        
        json.put("contacto", contacto);
        
        JSONObject direccion = new JSONObject();
        direccion.put("domicilio", cliente.getDomicilio());
        
        json.put("direccion", direccion);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Constantes.url + Constantes.rutas.clientes.name()))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 201) {
            return true;
        } else {
            System.err.println("Error al agregar el cliente: " + response.statusCode());
        }
    } catch (IOException | InterruptedException ex) {
        ex.printStackTrace();
    }
    return false;
}
    
    public static boolean updateCliente(Cliente cliente) {
        try {
            JSONObject json = new JSONObject();
            json.put("nombre", cliente.getNombre());
            json.put("edad", cliente.getEdad());
            json.put("sexo", cliente.getSexo());
            json.put("fecha_nacimiento", cliente.getFechaNacimiento());

            JSONObject contacto = new JSONObject();
            contacto.put("telefono", cliente.getContacto().getTelefono());
            contacto.put("correo", cliente.getContacto().getCorreo());

            json.put("contacto", contacto);

            JSONObject direccion = new JSONObject();
            direccion.put("domicilio", cliente.getDomicilio());

            json.put("direccion", direccion);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.clientes.name() + "/" + cliente.getId()))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json.toString())) // Usamos PATCH aquí
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                
                return true;
            } else {
                System.err.println("Error al actualizar el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteCliente(String clienteId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.clientes.name() + "/" + clienteId))
                    .timeout(Duration.ofMinutes(1))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Cliente eliminado exitosamente: " + clienteId);
                return true;
            } else {
                System.err.println("Error al eliminar el cliente: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
