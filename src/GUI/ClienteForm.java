/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author draks
 */
import Objets.Cliente;
import Objets.Contacto;
import javax.swing.*;

public class ClienteForm extends JDialog {

    private JTextField nombreField;
    private JTextField edadField;
    private JComboBox<String> sexoComboBox;
    private JTextField telefonoField;
    private JTextField correoField;
    private JTextField domicilioField;
    private JTextField fechaNacimientoField;
    private JButton saveButton;
    private Cliente newCliente;
    private boolean succeeded;

    public ClienteForm(JFrame parent) {
        super(parent, "Nuevo Cliente", true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nombreField = new JTextField(20);
        edadField = new JTextField(20);
        sexoComboBox = new JComboBox<>(new String[]{"Femenino", "Masculino"});
        telefonoField = new JTextField(20);
        correoField = new JTextField(20);
        domicilioField = new JTextField(20);
        fechaNacimientoField = new JTextField(20);

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Edad:"));
        panel.add(edadField);
        panel.add(new JLabel("Sexo:"));
        panel.add(sexoComboBox);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoField);
        panel.add(new JLabel("Correo:"));
        panel.add(correoField);
        panel.add(new JLabel("Domicilio:"));
        panel.add(domicilioField);
        panel.add(new JLabel("Fecha Nacimiento:"));
        panel.add(fechaNacimientoField);

        saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveCliente());
        panel.add(saveButton);

        getContentPane().add(panel);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void saveCliente() {
        try {
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());
            String sexo = (String) sexoComboBox.getSelectedItem();
            String telefono = telefonoField.getText();
            String correo = correoField.getText();
            String domicilio = domicilioField.getText();
            String fechaNacimiento = fechaNacimientoField.getText();

            newCliente = new Cliente(null, nombre, edad, sexo, new Contacto(telefono, correo), fechaNacimiento, domicilio);
            succeeded = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Cliente getNewCliente() {
        return newCliente;
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}

