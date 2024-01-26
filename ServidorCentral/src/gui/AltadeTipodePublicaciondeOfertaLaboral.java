package gui;

import excepciones.TipoOfertaExiste;
import logica.ControllerFactory;
import logica.IConOferta;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class AltadeTipodePublicaciondeOfertaLaboral extends JInternalFrame {
	private JTextField nombre;
	private JTextField descripcion;
	private JTextField periodo;
	private JTextField descuento;
	//private String txt;
	private IConOferta conTipo;
	private JTextField orden;
	private JComboBox boxAnio;
	private JComboBox boxMes;
	private JComboBox boxDia;

	private Integer[] obtenerDias() {
		Integer[] dias = new Integer[31];
		for (int i = 0; i < 31; i++) {
			dias[i] = i + 1;
		}
		return dias;
	}

	private String[] obtenerMeses() {
		return new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
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
	public AltadeTipodePublicaciondeOfertaLaboral() {
		ControllerFactory fac = ControllerFactory.getInstance();
		conTipo = fac.getIControladorOferta();
		
		setTitle("Alta Tipo de publicación de Ofertas Laborales");
		setBounds(100, 100, 501, 269);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 39, 0, 138, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.gridwidth = 3;
		gbc_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre.gridx = 3;
		gbc_nombre.gridy = 1;
		getContentPane().add(nombre, gbc_nombre);
		nombre.setColumns(10);

		JLabel lblnombre = new JLabel("Descripción");
		GridBagConstraints gbc_lblnombre = new GridBagConstraints();
		gbc_lblnombre.anchor = GridBagConstraints.EAST;
		gbc_lblnombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblnombre.gridx = 2;
		gbc_lblnombre.gridy = 2;
		getContentPane().add(lblnombre, gbc_lblnombre);

		descripcion = new JTextField();
		GridBagConstraints gbc_descripcion = new GridBagConstraints();
		gbc_descripcion.gridwidth = 3;
		gbc_descripcion.insets = new Insets(0, 0, 5, 0);
		gbc_descripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_descripcion.gridx = 3;
		gbc_descripcion.gridy = 2;
		getContentPane().add(descripcion, gbc_descripcion);
		descripcion.setColumns(10);

		JLabel lblorden = new JLabel("orden");
		GridBagConstraints gbc_lblorden = new GridBagConstraints();
		gbc_lblorden.anchor = GridBagConstraints.EAST;
		gbc_lblorden.insets = new Insets(0, 0, 5, 5);
		gbc_lblorden.gridx = 2;
		gbc_lblorden.gridy = 3;
		getContentPane().add(lblorden, gbc_lblorden);

		orden = new JTextField();
		GridBagConstraints gbc_orden = new GridBagConstraints();
		gbc_orden.gridwidth = 3;
		gbc_orden.insets = new Insets(0, 0, 5, 0);
		gbc_orden.fill = GridBagConstraints.HORIZONTAL;
		gbc_orden.gridx = 3;
		gbc_orden.gridy = 3;
		getContentPane().add(orden, gbc_orden);
		orden.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("periodo de validez");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton_2 = new JButton("Aceptar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					darAlta();
				} catch (TipoOfertaExiste e1) {
					JOptionPane.showInputDialog(this);
				}
			}
		});

		periodo = new JTextField();
		GridBagConstraints gbc_periodo = new GridBagConstraints();
		gbc_periodo.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_periodo.gridwidth = 3;
		gbc_periodo.insets = new Insets(0, 0, 5, 0);
		gbc_periodo.fill = GridBagConstraints.HORIZONTAL;
		gbc_periodo.gridx = 3;
		gbc_periodo.gridy = 4;
		getContentPane().add(periodo, gbc_periodo);
		periodo.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		descuento = new JTextField();
		GridBagConstraints gbc_descuento = new GridBagConstraints();
		gbc_descuento.gridwidth = 3;
		gbc_descuento.insets = new Insets(0, 0, 5, 0);
		gbc_descuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_descuento.gridx = 3;
		gbc_descuento.gridy = 5;
		getContentPane().add(descuento, gbc_descuento);
		descuento.setColumns(10);

		JLabel LinkFechaNacimiento = new JLabel("Fecha de alta");
		GridBagConstraints gbc_LinkFechaNacimiento = new GridBagConstraints();
		gbc_LinkFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_LinkFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_LinkFechaNacimiento.gridx = 2;
		gbc_LinkFechaNacimiento.gridy = 6;
		getContentPane().add(LinkFechaNacimiento, gbc_LinkFechaNacimiento);

		boxDia = new JComboBox<>(obtenerDias());
		GridBagConstraints gbc_BoxDia = new GridBagConstraints();
		gbc_BoxDia.insets = new Insets(0, 0, 5, 5);
		gbc_BoxDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_BoxDia.gridx = 3;
		gbc_BoxDia.gridy = 6;
		getContentPane().add(boxDia, gbc_BoxDia);

		boxMes = new JComboBox<>(obtenerMeses());
		GridBagConstraints gbc_BoxMes = new GridBagConstraints();
		gbc_BoxMes.insets = new Insets(0, 0, 5, 5);
		gbc_BoxMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_BoxMes.gridx = 4;
		gbc_BoxMes.gridy = 6;
		getContentPane().add(boxMes, gbc_BoxMes);

		boxAnio = new JComboBox<>(obtenerAnios());
		GridBagConstraints gbc_BoxAnio = new GridBagConstraints();
		gbc_BoxAnio.insets = new Insets(0, 0, 5, 0);
		gbc_BoxAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_BoxAnio.gridx = 5;
		gbc_BoxAnio.gridy = 6;
		getContentPane().add(boxAnio, gbc_BoxAnio);

		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 8;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 8;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

	private boolean esNumerico(String str) {
	    return str.matches("\\d+");  // Verifica si la cadena contiene solo dígitos
	}

	
	private void darAlta() throws TipoOfertaExiste {
	    String nom = nombre.getText();
	    String desc = descripcion.getText();
	    String descue = descuento.getText();
	    int anio = (int) boxAnio.getSelectedItem();
	    int mes = boxMes.getSelectedIndex() + 1;
	    int dia = (int) boxDia.getSelectedItem();

	    if (!esNumerico(descuento.getText())) {
	        JOptionPane.showMessageDialog(this, "El costo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (!esNumerico(periodo.getText())) {
	        JOptionPane.showMessageDialog(this, "El periodo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (!esNumerico(orden.getText())) {
	        JOptionPane.showMessageDialog(this, "El orden debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (nom.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (desc.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, ingrese una descripción.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (periodo.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, ingrese un periodo de validez.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (descue.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, ingrese un descuento.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    LocalDate fechaAlta;
	    fechaAlta = LocalDate.of(anio, mes, dia);
	    
	    float descuentof = Float.parseFloat(descue);
	    int per = Integer.parseInt(periodo.getText());
	    int ord = Integer.parseInt(orden.getText());

	    try {
	        conTipo.altaTipoDeOfertaLaboral(nom, desc, ord, per, descuentof, fechaAlta);
	        JOptionPane.showMessageDialog(this, "Registro exitoso!", "", JOptionPane.INFORMATION_MESSAGE);
	        dispose();
	    } catch (TipoOfertaExiste e) {
	        JOptionPane.showMessageDialog(this, "Ya existe un Tipo de Oferta con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


}
