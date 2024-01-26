package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ConsultaDePaqueteDeTiposDePublicacionDeOfertasLaborales extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConsultaDePaqueteDeTiposDePublicacionDeOfertasLaborales() {
		setLayout(null);
		
		JLabel lblSeleccionarPaquete = new JLabel("Seleccionar Paquete");
		lblSeleccionarPaquete.setBounds(10, 23, 101, 13);
		add(lblSeleccionarPaquete);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 19, 111, 21);
		add(comboBox);
		
		JPanel panelInfoPaquete = new JPanel();
		panelInfoPaquete.setBounds(10, 46, 251, 129);
		add(panelInfoPaquete);
		panelInfoPaquete.setLayout(null);
		panelInfoPaquete.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(0, 10, 45, 13);
		panelInfoPaquete.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("nombre");
		lblNewLabel_1_2.setBounds(68, 10, 45, 13);
		panelInfoPaquete.add(lblNewLabel_1_2);
		
		JLabel lblDescripcion_1 = new JLabel("Descripcion:");
		lblDescripcion_1.setBounds(0, 27, 64, 13);
		panelInfoPaquete.add(lblDescripcion_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("descripcion");
		lblNewLabel_1_1_2.setBounds(68, 27, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_2);
		
		JLabel lblDescuento_4 = new JLabel("Validez:");
		lblDescuento_4.setBounds(0, 43, 64, 13);
		panelInfoPaquete.add(lblDescuento_4);
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("validez");
		lblNewLabel_1_1_1_5.setBounds(68, 43, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_1_5);
		
		JLabel lblDescuento_1_1 = new JLabel("Descuento:");
		lblDescuento_1_1.setBounds(0, 60, 64, 13);
		panelInfoPaquete.add(lblDescuento_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("descuento");
		lblNewLabel_1_1_1_1_1.setBounds(68, 60, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblDescuento_2_1 = new JLabel("Precio:");
		lblDescuento_2_1.setBounds(0, 77, 64, 13);
		panelInfoPaquete.add(lblDescuento_2_1);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("precio");
		lblNewLabel_1_1_1_2_1.setBounds(68, 77, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_1_2_1);
		
		JLabel lblDescuento_3_1 = new JLabel("Fecha:");
		lblDescuento_3_1.setBounds(0, 95, 64, 13);
		panelInfoPaquete.add(lblDescuento_3_1);
		
		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("fecha");
		lblNewLabel_1_1_1_3_1.setBounds(68, 95, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_1_3_1);
		
		JLabel lblPareja_1 = new JLabel("Pareja:");
		lblPareja_1.setBounds(0, 110, 64, 13);
		panelInfoPaquete.add(lblPareja_1);
		
		JLabel lblNewLabel_1_1_1_4_1 = new JLabel("pareja");
		lblNewLabel_1_1_1_4_1.setBounds(68, 110, 183, 13);
		panelInfoPaquete.add(lblNewLabel_1_1_1_4_1);
		
		JList tipoYCantidadOferta = new JList();
		tipoYCantidadOferta.setBounds(10, 185, 155, 13);
		add(tipoYCantidadOferta);
		
		JPanel panelInfoTipoOferta = new JPanel();
		panelInfoTipoOferta.setBounds(10, 208, 251, 104);
		add(panelInfoTipoOferta);
		panelInfoTipoOferta.setLayout(null);
		panelInfoTipoOferta.setVisible(false);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nombre:");
		lblNewLabel_2_1.setBounds(0, 0, 45, 13);
		panelInfoTipoOferta.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("nombre");
		lblNewLabel_1_2_1.setBounds(68, 0, 45, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_2_1);
		
		JLabel lblDescripcion_1_1 = new JLabel("Descripcion:");
		lblDescripcion_1_1.setBounds(0, 17, 64, 13);
		panelInfoTipoOferta.add(lblDescripcion_1_1);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("descripcion");
		lblNewLabel_1_1_2_1.setBounds(68, 17, 183, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_1_2_1);
		
		JLabel lblDescuento_4_1 = new JLabel("Orden:");
		lblDescuento_4_1.setBounds(0, 33, 64, 13);
		panelInfoTipoOferta.add(lblDescuento_4_1);
		
		JLabel lblNewLabel_1_1_1_5_1 = new JLabel("orden");
		lblNewLabel_1_1_1_5_1.setBounds(68, 33, 183, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_1_1_5_1);
		
		JLabel lblDescuento_1_1_1 = new JLabel("Costo:");
		lblDescuento_1_1_1.setBounds(0, 50, 64, 13);
		panelInfoTipoOferta.add(lblDescuento_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("costo");
		lblNewLabel_1_1_1_1_1_1.setBounds(68, 50, 183, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblDescuento_2_1_1 = new JLabel("Duracion:");
		lblDescuento_2_1_1.setBounds(0, 67, 64, 13);
		panelInfoTipoOferta.add(lblDescuento_2_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("duracion");
		lblNewLabel_1_1_1_2_1_1.setBounds(68, 67, 183, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_1_1_2_1_1);
		
		JLabel lblDescuento_3_1_1 = new JLabel("Fecha alta:");
		lblDescuento_3_1_1.setBounds(0, 85, 64, 13);
		panelInfoTipoOferta.add(lblDescuento_3_1_1);
		
		JLabel lblNewLabel_1_1_1_3_1_1 = new JLabel("fecha");
		lblNewLabel_1_1_1_3_1_1.setBounds(68, 85, 183, 13);
		panelInfoTipoOferta.add(lblNewLabel_1_1_1_3_1_1);

	}
}
