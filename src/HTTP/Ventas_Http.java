/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTP;

import GUI.MainMenuGUI;
import static GUI.MainMenuGUI.fillTablaVentas2;
import Objets.Cliente;
import Objets.Empleado;
import Objets.Producto;
import Objets.Venta;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author draks
 */
public class Ventas_Http {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static HashMap<String, Integer> empleadoVenta = new HashMap();

    public static Venta getVenta4ID(String id) {
        Venta venta = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.ventas.name() + "/" + id))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                venta = responseToVenta(response);
            } else {
                System.err.println("Error al obtener el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return venta;
    }

    public static ArrayList<Venta> getVenta4name(String name) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.ventas.name() + "n/nombre?nombre=" + name.replace(" ", "%20")))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Venta venta = responseToVenta(jsonObject);
                    if (venta != null) {
                        ventas.add(venta);
                    }
                }
            } else {
                System.err.println("Error al obtener el empleado: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return ventas;
    }

    public static ArrayList<Venta> getVentas() {
        ArrayList<Venta> ventas = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Constantes.url + Constantes.rutas.ventas.name()))
                        .timeout(Duration.ofMinutes(1))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Venta venta = responseToVenta(jsonObject);
                        if (venta != null) {
                            ventas.add(venta);
                            String nombreEmpleado = venta.getEmpleado().getNombre();
                            int precioVenta = venta.getPrecio_venta();
                            if (empleadoVenta.containsKey(nombreEmpleado)) {
                                int valorActual = empleadoVenta.get(nombreEmpleado);
                                empleadoVenta.put(nombreEmpleado, valorActual + precioVenta);
                            } else {
                                empleadoVenta.put(nombreEmpleado, precioVenta);
                            }

                            MainMenuGUI.fillTablaVentas(venta);
                        }
                    }
                    fillTablaVentas2(empleadoVenta);
                } else {
                    System.err.println("Error al obtener los clientes: " + response.statusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        thread.start();
        return ventas;
    }

    private static Venta responseToVenta(HttpResponse<String> response) {
        Producto producto = null;
        Cliente cliente = null;
        Empleado empleado = null;

        String id = null;

        String tipo_pago;
        int cant_venta;
        int precio_venta;

        String nombreProducto = null;
        String nombreCliente = null;
        String nombreEmpleado = null;

        JSONObject jsonObject = new JSONObject(response.body());
        id = jsonObject.getString("_id");

        JSONObject productoj = jsonObject.getJSONObject("producto");
        nombreProducto = productoj.getString("nombre");

        JSONObject clientej = jsonObject.getJSONObject("cliente");
        nombreCliente = clientej.getString("nombre");

        JSONObject empleadoj = jsonObject.getJSONObject("empleado");
        nombreEmpleado = empleadoj.getString("nombre");

        JSONObject tp = jsonObject.getJSONObject("tipo_pago");
        tipo_pago = tp.getString("nombre");

        cant_venta = jsonObject.getInt("cantidad_vendida");
        precio_venta = jsonObject.getInt("precio_venta");

        producto = new Producto(nombreProducto);
        cliente = new Cliente(nombreCliente);
        empleado = new Empleado(nombreEmpleado);

        /*        System.out.println("id: " + id + ", tipo_pago: " + tipo_pago + ", cant_venta: " + cant_venta
                + ", precio_venta: " + precio_venta + ", nombreProducto: " + nombreProducto
                + ", nombreCliente: " + nombreCliente + ", nombreEmpleado: " + nombreEmpleado);*/
        return new Venta(id, producto, cliente, empleado, tipo_pago, cant_venta, precio_venta);
    }

    private static Venta responseToVenta(JSONObject jsonObject) {
        Producto producto = null;
        Cliente cliente = null;
        Empleado empleado = null;

        String id = null;

        String tipo_pago;
        int cant_venta;
        int precio_venta;

        String nombreProducto = null;
        String nombreCliente = null;
        String nombreEmpleado = null;

        id = jsonObject.getString("_id");

        JSONObject productoj = jsonObject.getJSONObject("producto");
        nombreProducto = productoj.getString("nombre");

        JSONObject clientej = jsonObject.getJSONObject("cliente");
        nombreCliente = clientej.getString("nombre");

        JSONObject empleadoj = jsonObject.getJSONObject("empleado");
        nombreEmpleado = empleadoj.getString("nombre");

        JSONObject tp = jsonObject.getJSONObject("tipo_pago");
        tipo_pago = tp.getString("nombre");

        cant_venta = jsonObject.getInt("cantidad_vendida");
        precio_venta = jsonObject.getInt("precio_venta");

        producto = new Producto(nombreProducto);
        cliente = new Cliente(nombreCliente);
        empleado = new Empleado(nombreEmpleado);

        /*System.out.println("id: " + id + ", tipo_pago: " + tipo_pago + ", cant_venta: " + cant_venta
                + ", precio_venta: " + precio_venta + ", nombreProducto: " + nombreProducto
                + ", nombreCliente: " + nombreCliente + ", nombreEmpleado: " + nombreEmpleado);*/
        return new Venta(id, producto, cliente, empleado, tipo_pago, cant_venta, precio_venta);
    }
}
