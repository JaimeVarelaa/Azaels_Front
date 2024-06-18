/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTP;

import Objets.Cliente;
import Objets.Credito;
import Objets.TipoCredito;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jaime
 */
public class Creditos_HTTP {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static Credito getCredito4ID(String id) {
        Credito credito = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.creditos.name() + "/" + id))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                credito = responseToCredito(response);
            } else {
                System.err.println("Error al obtener el cliente: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return credito;
    }

    public static ArrayList<Credito> getCredito4name(String name) {
        ArrayList<Credito> creditos = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constantes.url + Constantes.rutas.creditos.name() + "n/nombre?nombre=" + name.replace(" ", "%20")))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Credito credito = responseToCredito(jsonObject);
                    if (credito != null) {
                        creditos.add(credito);
                    }
                }
            } else {
                System.err.println("Error al obtener el empleado: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return creditos;
    }

    public static ArrayList<Credito> getCreditos() {
        ArrayList<Credito> creditos = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(Constantes.url + Constantes.rutas.creditos.name()))
                        .timeout(Duration.ofMinutes(1))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Credito credito = responseToCredito(jsonObject);
                        if (credito != null) {
                            creditos.add(credito);
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
        return creditos;
    }

    private static Credito responseToCredito(HttpResponse<String> response) {
        Cliente cliente = null;
        TipoCredito tipoCredito = null;
        
        String id = null;

        String nombreCliente = null;
        
        int prestamo = 0;
        String fechaInicio;
        String fechaFinal;
        
        String nombreTC = null;
        float interes = 0;

        JSONObject jsonObject = new JSONObject(response.body());
        id = jsonObject.getString("_id");

        JSONObject clientej = jsonObject.getJSONObject("cliente");
        nombreCliente = clientej.getString("nombre");

        prestamo = jsonObject.getInt("cantidad_prestamo");
        fechaInicio = jsonObject.getString("fecha_inicio");
        fechaFinal = jsonObject.getString("fecha_final");
        
        ZonedDateTime FechaInicio = ZonedDateTime.parse(fechaInicio, DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime FechaFinal = ZonedDateTime.parse(fechaFinal, DateTimeFormatter.ISO_DATE_TIME);
        
        JSONObject tipoCreditoj = jsonObject.getJSONObject("tipo_credito");
        nombreTC = tipoCreditoj.getString("nombre");
        interes = tipoCreditoj.getFloat("interes");
        
        cliente = new Cliente(nombreCliente);
        tipoCredito = new TipoCredito(nombreTC, interes);
        
        System.out.println("id: " + id + ", nombreCliente: " + nombreCliente + ", prestamo: " + prestamo
        + ", fechaInicio: " + fechaInicio + ", fechaFinal: " + fechaFinal + ", nombreTC: " + nombreTC
        + ", interes: " + interes);
        
        return new Credito(id, cliente, prestamo, FechaInicio, FechaFinal, tipoCredito);
    }

    private static Credito responseToCredito(JSONObject jsonObject) {
        Cliente cliente = null;
        TipoCredito tipoCredito = null;
        
        String id = null;

        String nombreCliente = null;
        
        int prestamo = 0;
        String fechaInicio;
        String fechaFinal;
        
        String nombreTC = null;
        float interes = 0;

        id = jsonObject.getString("_id");

        JSONObject clientej = jsonObject.getJSONObject("cliente");
        nombreCliente = clientej.getString("nombre");

        prestamo = jsonObject.getInt("cantidad_prestamo");
        fechaInicio = jsonObject.getString("fecha_inicio");
        fechaFinal = jsonObject.getString("fecha_final");
        
        ZonedDateTime FechaInicio = ZonedDateTime.parse(fechaInicio, DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime FechaFinal = ZonedDateTime.parse(fechaFinal, DateTimeFormatter.ISO_DATE_TIME);
        
        JSONObject tipoCreditoj = jsonObject.getJSONObject("tipo_credito");
        nombreTC = tipoCreditoj.getString("nombre");
        interes = tipoCreditoj.getFloat("interes");
        
        cliente = new Cliente(nombreCliente);
        tipoCredito = new TipoCredito(nombreTC, interes);
        
        System.out.println("id: " + id + ", nombreCliente: " + nombreCliente + ", prestamo: " + prestamo
        + ", fechaInicio: " + fechaInicio + ", fechaFinal: " + fechaFinal + ", nombreTC: " + nombreTC
        + ", interes: " + interes);

        return new Credito(id, cliente, prestamo, FechaInicio, FechaFinal, tipoCredito);
    }

}
