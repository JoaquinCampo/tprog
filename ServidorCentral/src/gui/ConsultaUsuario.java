package gui;

import javax.swing.*;

import datatypes.*;
import excepciones.*;

import java.awt.*;
import logica.ControllerFactory;
import logica.IConUsuario;

public class ConsultaUsuario extends JInternalFrame {

    private JList<String> lstUsuarios;
    private DefaultListModel<String> listModel;

    private JPanel detailsPanel;
    private GridBagConstraints gbc;
    
    private void mostrarDescripcionEmpresa(String descripcion) {

        StringBuilder formattedDescription = new StringBuilder();
        int charCount = 0;
        for (char c : descripcion.toCharArray()) {
            formattedDescription.append(c);
            charCount++;
            if (charCount % 100 == 0) {
                formattedDescription.append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, formattedDescription.toString(), "Descripción de Empresa", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cargarUsuarios() {

        try {
            ControllerFactory fac = ControllerFactory.getInstance();
            IConUsuario icu = fac.getIControladorUsuario();

            for (String usuario : icu.listarUsuarios()) {
                listModel.addElement(usuario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetallesUsuario(String nickname) {
        try {
            detailsPanel.removeAll();
            ControllerFactory fac = ControllerFactory.getInstance();
            IConUsuario icu = fac.getIControladorUsuario();

            DTUsuario detalles = icu.elegirUsuario(nickname);
            gbc.gridy = 0;
            gbc.weighty = 0;
            

            addDetail("Nickname:", detalles.getNickname(), false, false);
            addDetail("Nombre:", detalles.getNombre(), false, false);
            addDetail("Apellido:", detalles.getApellido(), false, false);
            addDetail("Correo:", detalles.getCorreo(), false, false);

            if (detalles instanceof DTEmpresa) {
                DTEmpresa empresa = (DTEmpresa) detalles;
                addDetail("Web:", empresa.getWeb(), false, false);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                JTextArea descripcion = new JTextArea(empresa.getDescripcion(),10,10);
                descripcion.setEditable(false);
                descripcion.setLineWrap(true);
                descripcion.setWrapStyleWord(true);
                JScrollPane descripcionConScroll = new JScrollPane(descripcion);
                detailsPanel.add(descripcionConScroll,gbc);
                gbc.gridy++;
                mostrarOfertasLaborales(empresa.getOfertasLaborales());
            } else if (detalles instanceof DTPostulante) {
                DTPostulante postulante = (DTPostulante) detalles;
                addDetail("Nacionalidad:", postulante.getNacionalidad(), false, false);
                addDetail("Fecha de Nacimiento:", postulante.getFechaNacimiento().toString(), false, false);
                mostrarOfertasLaborales(postulante.getOfertas());
            }

            gbc.weighty = 1;
            JLabel filler = new JLabel();
            detailsPanel.add(filler, gbc);

            detailsPanel.revalidate();
            detailsPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error mostrando detalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarOfertasLaborales(java.util.Set<DTOferta> ofertas) {
        DefaultListModel<String> ofertasModel = new DefaultListModel<>();
        for (DTOferta oferta : ofertas) {
            ofertasModel.addElement(oferta.getNombre());
        }
        
        JList<String> lstOfertas = new JList<>(ofertasModel);
        lstOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lstOfertas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedOferta = lstOfertas.getSelectedValue();
                for (DTOferta oferta : ofertas) {
                    if (oferta.getNombre().equals(selectedOferta)) {
                        mostrarDetalleOferta(oferta);
                        lstOfertas.clearSelection();
                        break;
                    }
                }
            }
        });

        JScrollPane ofertasScrollPane = new JScrollPane(lstOfertas);
        
        JLabel lblTitle = new JLabel("Ofertas Laborales:");
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        detailsPanel.add(lblTitle, gbc);

        gbc.gridy++;
        detailsPanel.add(ofertasScrollPane, gbc);

        gbc.gridy++;
    }

    private void mostrarDetalleOferta(DTOferta oferta) {
        JOptionPane.showMessageDialog(this, "Nombre: " + oferta.getNombre() + "\nDescripción: " + oferta.getDescripcion() + "\nCiudad: " + oferta.getCiudad(), "Detalle de Oferta", JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void addDetail(String label, String value, boolean isFullWidth, boolean isDescription) {
        JLabel lbl = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        detailsPanel.add(lbl, gbc);

        if (isFullWidth) {
            if (isDescription) {
                JTextArea descriptionArea = new JTextArea(value);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setLineWrap(true);
                descriptionArea.setEditable(false);
                descriptionArea.setOpaque(false); 
                descriptionArea.setBorder(null);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                detailsPanel.add(descriptionArea, gbc);
            } else {
                JLabel val = new JLabel(value);
                gbc.gridx = 1;
                gbc.gridwidth = 1;
                detailsPanel.add(val, gbc);
            }
        } else {
            JLabel val = new JLabel(value);
            gbc.gridx = 1;
            detailsPanel.add(val, gbc);
        }

        gbc.gridy++;
    }


    public ConsultaUsuario() {
        super("Consulta de Usuario", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        lstUsuarios = new JList<>(listModel);
        lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && lstUsuarios.getSelectedIndex() != -1) {
                mostrarDetallesUsuario(lstUsuarios.getSelectedValue());
            }
        });
        
        JScrollPane listScrollPane = new JScrollPane(lstUsuarios);

        detailsPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;	
        gbc.insets = new Insets(2, 2, 2, 2);

        JScrollPane detailsScrollPane = new JScrollPane(detailsPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, detailsScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);

        add(splitPane, BorderLayout.CENTER);

        cargarUsuarios();
    }
}