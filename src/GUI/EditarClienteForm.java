/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import HTTP.Clientes_Http;
import javax.swing.*;
import java.awt.*;
import Objets.Cliente;
import Objets.Contacto;
/**
 *
 * @author draks
 */
public class EditarClienteForm extends JFrame {

    private Cliente cliente; // Cliente que se va a editar

    // Componentes de la interfaz gráfica
    private JTextField nombreField;
    private JTextField edadField;
    private JComboBox<String> sexoComboBox; // Cambiado a JComboBox
    private JTextField telefonoField;
    private JTextField correoField;
    private JTextField domicilioField;
    private JTextField fechaNacimientoField;

    // Constructor que recibe el cliente a editar
    public EditarClienteForm(Cliente cliente) {
        this.cliente = cliente;

        initComponents();
        llenarCampos(); // Método para llenar los campos de la GUI con los datos del cliente
    }

    // Método para inicializar y configurar componentes de la GUI
    private void initComponents() {
        setTitle("Editar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300); // Aumentamos el tamaño para un diseño más espaciado

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        nombreField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nombreField, gbc);

        // Edad
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Edad:"), gbc);
        edadField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(edadField, gbc);

        // Sexo
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Sexo:"), gbc);
        sexoComboBox = new JComboBox<>(new String[]{"Femenino", "Masculino"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sexoComboBox, gbc);

        // Teléfono
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Teléfono:"), gbc);
        telefonoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(telefonoField, gbc);

        // Correo
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Correo:"), gbc);
        correoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(correoField, gbc);

        // Domicilio
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Domicilio:"), gbc);
        domicilioField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(domicilioField, gbc);

        // Fecha de Nacimiento
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        fechaNacimientoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(fechaNacimientoField, gbc);

        // Botón Guardar
        JButton guardarButton = new JButton("Guardar Cambios");
        guardarButton.addActionListener(e -> guardarCambios());
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_END; // Alinear a la derecha
        panel.add(guardarButton, gbc);

        // Agregar el panel al JFrame
        add(panel);
    }

    // Método para llenar los campos de la GUI con los datos del cliente
    private void llenarCampos() {
        nombreField.setText(cliente.getNombre());
        edadField.setText(String.valueOf(cliente.getEdad()));
        // Configurar el JComboBox según el sexo del cliente
        sexoComboBox.setSelectedItem(cliente.getSexo());
        telefonoField.setText(cliente.getContacto().getTelefono());
        correoField.setText(cliente.getContacto().getCorreo());
        domicilioField.setText(cliente.getDomicilio());
        fechaNacimientoField.setText(cliente.getFechaNacimiento());
    }

    // Método para guardar los cambios editados en el cliente
    private void guardarCambios() {
    try {
        // Actualizar los datos del cliente con los valores de los campos de la GUI
        cliente.setNombre(nombreField.getText());
        cliente.setEdad(Integer.parseInt(edadField.getText()));
        cliente.setSexo((String) sexoComboBox.getSelectedItem());
        cliente.getContacto().setTelefono(telefonoField.getText());
        cliente.getContacto().setCorreo(correoField.getText());
        cliente.setDomicilio(domicilioField.getText());
        cliente.setFechaNacimiento(fechaNacimientoField.getText());

        // Llamar al método para actualizar el cliente en el servidor
        boolean actualizacionExitosa = Clientes_Http.updateCliente(cliente);

        // Mostrar mensaje de acuerdo al resultado de la actualización
        if (actualizacionExitosa) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la ventana después de guardar los cambios
        dispose();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // Imprime el stack trace para depurar el error
    }
}

    // Método principal para ejecutar la ventana de edición (solo para pruebas)
   
}