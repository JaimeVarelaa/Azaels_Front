/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTP;

import GUI.MainMenuGUI;
import Objets.Cliente;
import Objets.Ventas;
import Objets.Contacto;
import Objets.Empleado;
import Objets.Producto;
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
 *
 * @author draks
 */
public class Ventas_Http {
       private static final HttpClient vent = HttpClient.newHttpClient();
       
       public static Ventas getVentas4ID(String id) {
        Ventas venta = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.ventas.name() + "/" + id))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = vent.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                venta = responseToVentas(response);
            } else {
                System.err.println("Error al obtener el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return venta;
    }
       
       public static ArrayList<Ventas> getVentas() {
        ArrayList<Ventas> clientes = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Constantes.url + Constantes.rutas.ventas.name()))
                        .timeout(Duration.ofMinutes(1))
                        .GET()
                        .build();

                HttpResponse<String> response = vent.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("_id");
                        Ventas venta = getVentas4ID(id);
                        if (venta != null) {
                            clientes.add(venta);
                            MainMenuGUI.fillTablaClientes(venta);
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
       
       private static Ventas responseToVentas(HttpResponse<String> response) {
        Cliente cliente = null;
        Producto producto = null;
        Empleado empleado = null;
        
        String nombre_cliente = null;
        
        String nombre_empleado=null;
        
        String nombre_producto=null;

        String id = null;
        int cant_venta;
    
        int precio_venta;

        String telefono = null;
        String correo = null;

        String fechaNacimiento = null;
        String domicilio = null;

        JSONObject jsonObject = new JSONObject(response.body());
        id = jsonObject.getString("_id");
        cant_venta = jsonObject.getInt("cantidad_vendida");
        precio_venta = jsonObject.getInt("precio_venta");
        
        

        JSONObject clientej = jsonObject.getJSONObject("cliente");
        nombre_cliente = clientej.getString("nombre");
        
        JSONObject empleadoj = jsonObject.getJSONObject("empleados");
        nombre_empleado = empleadoj.getString("nombre");

        JSONObject direccionj = jsonObject.getJSONObject("productos");
        domicilio = direccionj.getString("nombre");

        cliente = new Cliente(nombre);
        
        producto = new Producto(nombre);
        empleado = new Contacto(nombre);

        //return new Ventas(id, nombre, edad, sexo, contacto, fechaNacimiento, domicilio);
    }
}
