package gui;


import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;


import logica.ControllerFactory;
import logica.IConPaquete;
import excepciones.PaqueteYaExiste;

import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class CrearPaquetedeTiposdepublicacióndeOfertasLaborales extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtPeriodo;
	private JTextField txtDescuento;
	private JTextArea textArea;

	private JComboBox<Integer> diaComboBox;
	private JComboBox<String> mesComboBox;
	private JComboBox<Integer> anioComboBox;
    
    private Integer[] obtenerDias() {
        Integer[] dias = new Integer[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = i + 1;
        }
        return dias;
    }

    private String[] obtenerMeses() {
        return new String[]{
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
    }

    private Integer[] obtenerAnios() {
        Integer[] anios = new Integer[124];
        int anioActual = LocalDate.now().getYear();
        for (int i = 0; i < 124; i++) {
            anios[i] = anioActual - i;
        }
        return anios;
    }
		


	/**
	 * Create the frame.
	 */
	public CrearPaquetedeTiposdepublicacióndeOfertasLaborales() {
		setResizable(true);
		setClosable(true);
		setTitle("CrearPaquetedeTiposdepublicacióndeOfertasLaborales");
		setSize(563, 331);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 39, 0, 138, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 22, 59, 0, 0, 0, 0, 0, 0, 58, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 2;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Descripcion");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 2;
		getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Peroiodo de validez");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtPeriodo = new JTextField();
		GridBagConstraints gbc_txtPeriodo = new GridBagConstraints();
		gbc_txtPeriodo.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_txtPeriodo.gridwidth = 2;
		gbc_txtPeriodo.insets = new Insets(0, 0, 5, 0);
		gbc_txtPeriodo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPeriodo.gridx = 3;
		gbc_txtPeriodo.gridy = 3;
		getContentPane().add(txtPeriodo, gbc_txtPeriodo);
		txtPeriodo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descuento");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtDescuento = new JTextField();
		GridBagConstraints gbc_txtDescuento = new GridBagConstraints();
		gbc_txtDescuento.gridwidth = 2;
		gbc_txtDescuento.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescuento.gridx = 3;
		gbc_txtDescuento.gridy = 4;
		getContentPane().add(txtDescuento, gbc_txtDescuento);
		txtDescuento.setColumns(10);
		
		JLabel LinkFechaNacimiento = new JLabel("Fecha de alta");
		GridBagConstraints gbc_LinkFechaNacimiento = new GridBagConstraints();
		gbc_LinkFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_LinkFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_LinkFechaNacimiento.gridx = 2;
		gbc_LinkFechaNacimiento.gridy = 5;
		getContentPane().add(LinkFechaNacimiento, gbc_LinkFechaNacimiento);

		diaComboBox = new JComboBox<>(obtenerDias());
		GridBagConstraints gbc_diaComboBox = new GridBagConstraints();
		gbc_diaComboBox.anchor = GridBagConstraints.EAST;
		gbc_diaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_diaComboBox.gridx = 3;
		gbc_diaComboBox.gridy = 5;
		getContentPane().add(diaComboBox, gbc_diaComboBox);

		mesComboBox = new JComboBox<>(obtenerMeses());
		GridBagConstraints gbc_mesComboBox = new GridBagConstraints();
		gbc_mesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_mesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mesComboBox.gridx = 4;
		gbc_mesComboBox.gridy = 5;
		getContentPane().add(mesComboBox, gbc_mesComboBox);

		anioComboBox = new JComboBox<>(obtenerAnios());
		GridBagConstraints gbc_anioComboBox = new GridBagConstraints();
		gbc_anioComboBox.anchor = GridBagConstraints.WEST;
		gbc_anioComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_anioComboBox.gridx = 5;
		gbc_anioComboBox.gridy = 5;
		getContentPane().add(anioComboBox, gbc_anioComboBox);
		

		JButton btnNewButton_2 = new JButton("Aceptar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				crearPaquete();
			}
		});
		
        
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 8;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 8;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		

	}
	
	private void crearPaquete() {
	    try {
	        String nombrePaquete = txtNombre.getText();
	        String descripcion = textArea.getText();
	        int periodo = Integer.parseInt(txtPeriodo.getText());
	        float descuento = Float.parseFloat(txtDescuento.getText());
	        
	        if (descuento >= 0 && descuento < 100) { // Verifica si el descuento es válido
	            int dia = (Integer) diaComboBox.getSelectedItem();
	            int mes = mesComboBox.getSelectedIndex() + 1;
	            int anio = (Integer) anioComboBox.getSelectedItem();
	            LocalDate localDate = LocalDate.of(anio, mes, dia);

	            ControllerFactory fac = ControllerFactory.getInstance();
	            IConPaquete icp = fac.getIControladorPaquete();
	            icp.crearPaquete(nombrePaquete, descripcion, periodo, descuento, localDate);

	            JOptionPane.showMessageDialog(this, "Paquete creado con éxito!");
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "El descuento debe ser un valor válido (mayor o igual a 0 y menor que 100).");
	        }

	    } catch (PaqueteYaExiste e) {
	        JOptionPane.showMessageDialog(this, "Ya existe un paquete con ese nombre. Por favor, ingrese un nombre diferente o cancele la operación.");
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para los campos.");
	    }
	}



}

