/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTP;

import GUI.MainMenuGUI;
import Objets.Contacto;
import Objets.Empleado;
import Objets.Puesto;
import Objets.Tienda;
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
 * @author jaime
 */
public class Empleados_HTTP {

    private static final HttpClient employee = HttpClient.newHttpClient();

    public static Empleado getEmpleado4ID(String id) {
        Empleado empleado = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.empleados.name() + "/" + id))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = employee.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                empleado = responseToEmpleado(response);
            } else {
                System.err.println("Error al obtener el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return empleado;
    }

    public static ArrayList<Empleado> getEmpleado4name(String name) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.empleados.name() + "n/nombre?nombre=" + name.replace(" ", "%20")))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = employee.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Empleado empleado = responseToEmpleado(jsonObject);
                    if (empleado != null) {
                        empleados.add(empleado);
                    }
                }
            } else {
                System.err.println("Error al obtener el empleado: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return empleados;
    }

    public static ArrayList<Empleado> getEmpleados(Empleado trabajador) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Constantes.url + Constantes.rutas.empleados.name()))
                        .timeout(Duration.ofMinutes(1))
                        .GET()
                        .build();

                HttpResponse<String> response = employee.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Empleado empleado = responseToEmpleado(jsonObject);
                        if (empleado != null) {
                            empleados.add(empleado);
                            if (trabajador.getTienda().getNombre().equals(empleado.getTienda().getNombre())) {
                                MainMenuGUI.fillTablaEmpleados(empleado);
                            }
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
        return empleados;
    }

    private static Empleado responseToEmpleado(HttpResponse<String> response) {
        Contacto contacto = null;
        Puesto puesto = null;
        Tienda tienda = null;

        String id = null;
        String nombre = null;
        int edad = -1;
        String sexo = null;
        String estudios = null;

        String telefono = null;

        String domicilio = null;

        String nSS = null;

        String nombrePuesto = null;
        int sueldo = 0;
        int bonos = 0;

        String nombreTienda = null;
        String direccionTienda = null;

        JSONObject jsonObject = new JSONObject(response.body());
        id = jsonObject.getString("_id");
        nombre = jsonObject.getString("nombre");
        edad = jsonObject.getInt("edad");
        sexo = jsonObject.getString("sexo");
        estudios = jsonObject.getString("grado_academico");

        JSONObject contactoj = jsonObject.getJSONObject("contacto");
        telefono = contactoj.getString("telefono");

        JSONObject direccionj = jsonObject.getJSONObject("direccion");
        domicilio = direccionj.getString("domicilio");

        nSS = jsonObject.getString("seguro_social");

        JSONObject puestoj = jsonObject.getJSONObject("puesto");
        nombrePuesto = puestoj.getString("nombre");
        sueldo = puestoj.getInt("sueldo");
        bonos = puestoj.getInt("bonos");

        JSONObject tiendaj = jsonObject.getJSONObject("tienda");
        nombreTienda = tiendaj.getString("nombre");
        direccionTienda = tiendaj.getString("direccion");

        contacto = new Contacto(telefono);
        puesto = new Puesto(nombre, sueldo, bonos);
        tienda = new Tienda(nombreTienda, direccionTienda);

        /*System.out.println("id: " + id);
        System.out.println("nombre: " + nombre);
        System.out.println("edad: " + edad);
        System.out.println("sexo: " + sexo);
        System.out.println("estudios: " + estudios);
        System.out.println("telefono: " + telefono);
        System.out.println("domicilio: " + domicilio);
        System.out.println("nSS: " + nSS);
        System.out.println("nombrePuesto: " + nombrePuesto);
        System.out.println("sueldo: " + sueldo);
        System.out.println("bonos: " + bonos);*/
        return new Empleado(id, nombre, edad, sexo, estudios, contacto, domicilio,
                nSS, puesto, tienda);
    }

    private static Empleado responseToEmpleado(JSONObject jsonObject) {
        Contacto contacto = null;
        Puesto puesto = null;
        Tienda tienda = null;

        String id = null;
        String nombre = null;
        int edad = -1;
        String sexo = null;
        String estudios = null;

        String telefono = null;

        String domicilio = null;

        String nSS = null;

        String nombrePuesto = null;
        int sueldo = 0;
        int bonos = 0;

        String nombreTienda = null;
        String direccionTienda = null;

        id = jsonObject.getString("_id");
        nombre = jsonObject.getString("nombre");
        edad = jsonObject.getInt("edad");
        sexo = jsonObject.getString("sexo");
        estudios = jsonObject.getString("grado_academico");

        JSONObject contactoj = jsonObject.getJSONObject("contacto");
        telefono = contactoj.getString("telefono");

        JSONObject direccionj = jsonObject.getJSONObject("direccion");
        domicilio = direccionj.getString("domicilio");

        nSS = jsonObject.getString("seguro_social");

        JSONObject puestoj = jsonObject.getJSONObject("puesto");
        nombrePuesto = puestoj.getString("nombre");
        sueldo = puestoj.getInt("sueldo");
        bonos = puestoj.getInt("bonos");

        JSONObject tiendaj = jsonObject.getJSONObject("tienda");
        nombreTienda = tiendaj.getString("nombre");
        direccionTienda = tiendaj.getString("direccion");

        contacto = new Contacto(telefono);
        puesto = new Puesto(nombrePuesto, sueldo, bonos);
        tienda = new Tienda(nombreTienda, direccionTienda);

        /*System.out.println("id: " + id);
        System.out.println("nombre: " + nombre);
        System.out.println("edad: " + edad);
        System.out.println("sexo: " + sexo);
        System.out.println("estudios: " + estudios);
        System.out.println("telefono: " + telefono);
        System.out.println("domicilio: " + domicilio);
        System.out.println("nSS: " + nSS);
        System.out.println("nombrePuesto: " + nombrePuesto);
        System.out.println("sueldo: " + sueldo);
        System.out.println("bonos: " + bonos);*/
        return new Empleado(id, nombre, edad, sexo, estudios, contacto, domicilio,
                nSS, puesto, tienda);
    }

}
