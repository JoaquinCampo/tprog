package gui;

import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConPaquete;
import logica.IConUsuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashSet;
import java.util.Set;
import javax.swing.SwingConstants;

import datatypes.DTPaquete;
import datatypes.DTPostulacion;
import datatypes.DTPostulante;

public class ConsultarPaquete extends JInternalFrame {

	private JTextField txtdescuento;
	private JTextField txtprecio;
	private JTextField txtfecha;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtValides;
	
	//private GridBagConstraints gbc;
	//private JPanel detailsPanel;

	private JList<String> listPaquetes;
	private JList<String> listTiposPublicacion;

	//private String empresaSeleccionada;
//	private String ofertaSeleccionada;
	//private String postulanteSeleccionado;



	/**
	 * Create the frame.
	 */

	private DTPaquete buscarPaquetePorNombre(Set<DTPaquete> paquetes, String nombrePaquete) {
		for (DTPaquete paquete : paquetes) {
			if (paquete.getNombre().equals(nombrePaquete)) {
				return paquete;
			}
		}
		return null;
	}

	private void mostrarDetallesPaquete(String nombrePaquete) {
		ControllerFactory fac = ControllerFactory.getInstance();
		IConPaquete icp = fac.getIControladorPaquete();
		Set<DTPaquete> paquetes = icp.listarPaquete();
		DTPaquete paquete = buscarPaquetePorNombre(paquetes, nombrePaquete);

		if (paquete != null) {
			txtNombre.setText(paquete.getNombre());
			txtDescripcion.setText(paquete.getDescripcion());

			txtValides.setText(String.valueOf(paquete.getValides()));
			txtdescuento.setText(String.valueOf(paquete.getDescuento()));

			txtprecio.setText(String.valueOf(paquete.getPrecio()));
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			txtfecha.setText(paquete.getFecha().format(sdf));

			if (icp.getNombresTipoOferta(paquete.getNombre()) != null
					&& !icp.getNombresTipoOferta(paquete.getNombre()).isEmpty()) {
				DefaultListModel<String> listModel = new DefaultListModel<>();
				Set<String> tipos = icp.getNombresTipoOferta(paquete.getNombre());
				for (String tipo : tipos) {
					listModel.addElement(tipo);
				}
				listTiposPublicacion.setModel(listModel);
			}
		}
	}

	private void llenarListaPaquetes() {
	    ControllerFactory fac = ControllerFactory.getInstance();
	    IConPaquete icp = fac.getIControladorPaquete();
	    Set<DTPaquete> paquetes = icp.listarPaquete();

	    if (paquetes != null && !paquetes.isEmpty()) {
	        DefaultListModel<String> listModel = new DefaultListModel<>();
	        for (DTPaquete paquete : paquetes) {
	            listModel.addElement(paquete.getNombre());
	        }
	        listPaquetes.setModel(listModel);
	    }
	}


	public ConsultarPaquete() {
	//	detailsPanel = new JPanel(new GridBagLayout());

		setResizable(true);
		ControllerFactory con = ControllerFactory.getInstance();
		IConOferta conOferta = con.getIControladorOferta();
		IConUsuario conUsuario = con.getIControladorUsuario();

		setClosable(true);
		setTitle("Consulta de paquete");
		setSize(759, 650);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, -10, 124, 138, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 119, -23, 0, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 77, 0, 0, 0, 0, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblTiposPublicacion = new JLabel("Tipos de Publicación:");
		GridBagConstraints gbc_lblTiposPublicacion = new GridBagConstraints();
		gbc_lblTiposPublicacion.anchor = GridBagConstraints.EAST;
		gbc_lblTiposPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTiposPublicacion.gridx = 2;
		gbc_lblTiposPublicacion.gridy = 10; // Ajusta este valor según la posición deseada
		getContentPane().add(lblTiposPublicacion, gbc_lblTiposPublicacion);

		DTPostulacion dtp = new DTPostulacion();
		Set<String> empresas = conUsuario.listarEmpresas();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String empresa : empresas) {
			listModel.addElement(empresa);
		}
		Set<String> ofertas = new HashSet<>();

		DefaultListModel<String> listModel1 = new DefaultListModel<>();
		for (String oferta : ofertas) {
			listModel1.addElement(oferta);
		}

		Set<DTPostulante> postulantes = conUsuario.listarPostulantesConDatos();
		DefaultListModel<String> listModel2 = new DefaultListModel<>();
		for (DTPostulante aux : postulantes) {
			listModel2.addElement(aux.getNickname());
		}

		JLabel lblNewLabel = new JLabel("Paquetes:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		listPaquetes = new JList<>(listModel1);
		listPaquetes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(listPaquetes);

		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.gridheight = 2;
		gbc_list_2.insets = new Insets(0, 0, 5, 5);
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.gridx = 3;
		gbc_list_2.gridy = 0;

		getContentPane().add(scrollPane, gbc_list_2);

		listPaquetes.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listPaquetes.getSelectedIndex() != -1) {
				mostrarDetallesPaquete(listPaquetes.getSelectedValue());
			}
		});
		llenarListaPaquetes();

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 3;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descripción");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtDescripcion = new JTextField();
		txtDescripcion.setEditable(false);
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.gridx = 3;
		gbc_txtDescripcion.gridy = 4;
		getContentPane().add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel validestxt = new JLabel("valides");
		validestxt.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_validestxt = new GridBagConstraints();
		gbc_validestxt.anchor = GridBagConstraints.EAST;
		gbc_validestxt.insets = new Insets(0, 0, 5, 5);
		gbc_validestxt.gridx = 2;
		gbc_validestxt.gridy = 5;
		getContentPane().add(validestxt, gbc_validestxt);

		txtValides = new JTextField();
		txtValides.setEditable(false);
		GridBagConstraints gbc_txtValides = new GridBagConstraints();
		gbc_txtValides.insets = new Insets(0, 0, 5, 5);
		gbc_txtValides.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValides.gridx = 3;
		gbc_txtValides.gridy = 5;
		getContentPane().add(txtValides, gbc_txtValides);
		txtValides.setColumns(10);

		JLabel descuentotxt = new JLabel("descuento");
		descuentotxt.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_descuentotxt = new GridBagConstraints();
		gbc_descuentotxt.anchor = GridBagConstraints.EAST;
		gbc_descuentotxt.insets = new Insets(0, 0, 5, 5);
		gbc_descuentotxt.gridx = 2;
		gbc_descuentotxt.gridy = 6;
		getContentPane().add(descuentotxt, gbc_descuentotxt);

		txtdescuento = new JTextField();
		txtdescuento.setEditable(false);
		GridBagConstraints gbc_txtdescuento = new GridBagConstraints();
		gbc_txtdescuento.insets = new Insets(0, 0, 5, 5);
		gbc_txtdescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtdescuento.gridx = 3;
		gbc_txtdescuento.gridy = 6;
		getContentPane().add(txtdescuento, gbc_txtdescuento);
		txtdescuento.setColumns(10);

		JLabel preciotxt = new JLabel("precio");
		GridBagConstraints gbc_preciotxt = new GridBagConstraints();
		gbc_preciotxt.insets = new Insets(0, 0, 5, 5);
		gbc_preciotxt.anchor = GridBagConstraints.EAST;
		gbc_preciotxt.gridx = 2;
		gbc_preciotxt.gridy = 7;
		getContentPane().add(preciotxt, gbc_preciotxt);

		txtprecio = new JTextField();
		txtprecio.setEditable(false);
		GridBagConstraints gbc_txtprecio = new GridBagConstraints();
		gbc_txtprecio.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_txtprecio.insets = new Insets(0, 0, 5, 5);
		gbc_txtprecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtprecio.gridx = 3;
		gbc_txtprecio.gridy = 7;
		getContentPane().add(txtprecio, gbc_txtprecio);
		txtprecio.setColumns(10);

		JLabel fechatxt = new JLabel("Fecha ");
		GridBagConstraints gbc_fechatxt = new GridBagConstraints();
		gbc_fechatxt.anchor = GridBagConstraints.EAST;
		gbc_fechatxt.insets = new Insets(0, 0, 5, 5);
		gbc_fechatxt.gridx = 2;
		gbc_fechatxt.gridy = 8;
		getContentPane().add(fechatxt, gbc_fechatxt);

		txtfecha = new JTextField();
		txtfecha.setEditable(false);
		GridBagConstraints gbc_txtfecha = new GridBagConstraints();
		gbc_txtfecha.insets = new Insets(0, 0, 5, 5);
		gbc_txtfecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtfecha.gridx = 3;
		gbc_txtfecha.gridy = 8;
		getContentPane().add(txtfecha, gbc_txtfecha);
		txtfecha.setColumns(10);

		listTiposPublicacion = new JList<>();
		JScrollPane scrollPaneTiposPublicacion = new JScrollPane(listTiposPublicacion);
		GridBagConstraints gbc_listTiposPublicacion = new GridBagConstraints();
		gbc_listTiposPublicacion.gridheight = 2;
		gbc_listTiposPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_listTiposPublicacion.fill = GridBagConstraints.BOTH;
		gbc_listTiposPublicacion.gridx = 3;
		gbc_listTiposPublicacion.gridy = 10;
		getContentPane().add(scrollPaneTiposPublicacion, gbc_listTiposPublicacion);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.EAST;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 12;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

	}

}
