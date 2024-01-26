package gui;


import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;


import datatypes.DTPostulacion;
import logica.ControllerFactory;
import datatypes.DTOferta;
import datatypes.DTEmpresa;
import datatypes.DTPostulante;
import datatypes.DTUsuario;


import logica.IConOferta;
import logica.IConUsuario;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class ConsultadeOfertaLaboral extends JInternalFrame {
	private JTextField txtRemuneracion;
	private JTextField txtCiudad;
	private JTextField txtDepartamento;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtHorario;
	//private GridBagConstraints gbc;
	//private JPanel detailsPanel;

	private JList<String> listOfertas;
	private JList<String> listaEmpresas;
	private JList<String> listaPostulante;
	private JTextField textFieldFecha;

	//private String empresaSeleccionada;
	private String ofertaSeleccionada;
	//private String postulanteSeleccionado;



	/**
	 * Create the frame.
	 */
	public ConsultadeOfertaLaboral() {
		//detailsPanel = new JPanel(new GridBagLayout());

		setResizable(true);
		ControllerFactory con = ControllerFactory.getInstance();
		IConOferta conOferta = con.getIControladorOferta();
		IConUsuario conUsuario = con.getIControladorUsuario();

		setClosable(true);
		setTitle("Consulta de Oferta Laboral");
		setSize(759, 650);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, -10, 124, 138, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 119, -23, 0, 56, 0, 0, 0, 0, 0, 0, 0, 0, 33, 77, 0, 0, 0, 0, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel_3 = new JLabel("Selecciona una empresa");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		DTPostulacion dtp = new DTPostulacion();
		Set<String> empresas = conUsuario.listarEmpresas();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String empresa : empresas) {
			listModel.addElement(empresa);
		}

		listaEmpresas = new JList<>(listModel);

		JScrollPane scrollPaneEmpresas = new JScrollPane(listaEmpresas);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 3;
		gbc_list.gridy = 0;
		Set<String> ofertas = new HashSet<>();

		getContentPane().add(scrollPaneEmpresas, gbc_list);

		listaEmpresas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEmpresas.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listaEmpresas.getSelectedIndex() != -1) {
				ControllerFactory fac = ControllerFactory.getInstance();
				IConUsuario icu = fac.getIControladorUsuario();

				DTUsuario detalles = icu.elegirUsuario(listaEmpresas.getSelectedValue());
				if (detalles instanceof DTEmpresa) {
					DTEmpresa empresa = (DTEmpresa) detalles;

					DefaultListModel<String> ofertasModel = new DefaultListModel<>();
					for (DTOferta oferta : empresa.getOfertasLaborales()) {
						ofertasModel.addElement(oferta.getNombre());
					}
					listOfertas.setModel(ofertasModel);
					//empresaSeleccionada = listaEmpresas.getSelectedValue();
				}
			}
		});

		DefaultListModel<String> listModel1 = new DefaultListModel<>();
		for (String oferta : ofertas) {
			listModel1.addElement(oferta);
		}

		Set<DTPostulante> postulantes = conUsuario.listarPostulantesConDatos();
		DefaultListModel<String> listModel2 = new DefaultListModel<>();
		for (DTPostulante aux : postulantes) {
			listModel2.addElement(aux.getNickname());
		}

		JLabel lblNewLabel = new JLabel("Ofertas Laborales disponibles");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		listOfertas = new JList<>(listModel1);
		listOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(listOfertas);

		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.gridheight = 2;
		gbc_list_2.insets = new Insets(0, 0, 5, 5);
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.gridx = 3;
		gbc_list_2.gridy = 3;

		getContentPane().add(scrollPane, gbc_list_2);

		listOfertas.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting() && listOfertas.getSelectedIndex() != -1) {
		        DTOferta selectedOferta = conOferta.seleccionarOferta(listOfertas.getSelectedValue());
		        txtNombre.setText(listOfertas.getSelectedValue());
		        txtDescripcion.setText(selectedOferta.getDescripcion());
		        txtHorario.setText(selectedOferta.getHorario());
		        txtRemuneracion.setText(String.valueOf(selectedOferta.getSueldo()));
		        txtDepartamento.setText(selectedOferta.getDepartamento());
		        txtCiudad.setText(selectedOferta.getCiudad());

		        LocalDate fecha = selectedOferta.getFecha();
		        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String fechaString = fecha.format(sdf);
		        textFieldFecha.setText(fechaString);
		        ofertaSeleccionada = listOfertas.getSelectedValue();

		        // Aquí es donde obtienes los postulantes y actualizas la listaPostulante
		        ControllerFactory fac = ControllerFactory.getInstance();
		        IConOferta ofa = fac.getIControladorOferta();
		        Set<String> Set_postulantes = ofa.getPostulantes(ofertaSeleccionada);
		        
		        // Actualizar el modelo de datos de listaPostulante
		        DefaultListModel<String> postulantesModel = new DefaultListModel<>();
		        for (String postulante : Set_postulantes) {
		            postulantesModel.addElement(postulante);
		        }
		        listaPostulante.setModel(postulantesModel);
		    }
		});


		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 6;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 6;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descripción");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 7;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtDescripcion = new JTextField();
		txtDescripcion.setEditable(false);
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.gridx = 3;
		gbc_txtDescripcion.gridy = 7;
		getContentPane().add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Horario");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 8;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtHorario = new JTextField();
		txtHorario.setEditable(false);
		GridBagConstraints gbc_txtHorario = new GridBagConstraints();
		gbc_txtHorario.insets = new Insets(0, 0, 5, 5);
		gbc_txtHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHorario.gridx = 3;
		gbc_txtHorario.gridy = 8;
		getContentPane().add(txtHorario, gbc_txtHorario);
		txtHorario.setColumns(10);

		JLabel LinkFechaNacimiento = new JLabel("Remuneracion");
		GridBagConstraints gbc_LinkFechaNacimiento = new GridBagConstraints();
		gbc_LinkFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_LinkFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_LinkFechaNacimiento.gridx = 2;
		gbc_LinkFechaNacimiento.gridy = 9;
		getContentPane().add(LinkFechaNacimiento, gbc_LinkFechaNacimiento);

		txtRemuneracion = new JTextField();
		txtRemuneracion.setEditable(false);
		GridBagConstraints gbc_txtRemuneracion = new GridBagConstraints();
		gbc_txtRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_txtRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRemuneracion.gridx = 3;
		gbc_txtRemuneracion.gridy = 9;
		getContentPane().add(txtRemuneracion, gbc_txtRemuneracion);
		txtRemuneracion.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ciudad");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 10;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtCiudad = new JTextField();
		txtCiudad.setEditable(false);
		GridBagConstraints gbc_txtCiudad = new GridBagConstraints();
		gbc_txtCiudad.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_txtCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_txtCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCiudad.gridx = 3;
		gbc_txtCiudad.gridy = 10;
		getContentPane().add(txtCiudad, gbc_txtCiudad);
		txtCiudad.setColumns(10);

		JLabel DescripcionNacionalidad = new JLabel("Departamento");
		GridBagConstraints gbc_DescripcionNacionalidad = new GridBagConstraints();
		gbc_DescripcionNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_DescripcionNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_DescripcionNacionalidad.gridx = 2;
		gbc_DescripcionNacionalidad.gridy = 11;
		getContentPane().add(DescripcionNacionalidad, gbc_DescripcionNacionalidad);

		txtDepartamento = new JTextField();
		txtDepartamento.setEditable(false);
		GridBagConstraints gbc_txtDepartamento = new GridBagConstraints();
		gbc_txtDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDepartamento.gridx = 3;
		gbc_txtDepartamento.gridy = 11;
		getContentPane().add(txtDepartamento, gbc_txtDepartamento);
		txtDepartamento.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Fecha de alta");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 12;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setColumns(10);
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 3;
		gbc_textFieldFecha.gridy = 12;
		getContentPane().add(textFieldFecha, gbc_textFieldFecha);

		JLabel lblNewLabel_7 = new JLabel("Postulantes");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 13;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		listaPostulante = new JList<>(listModel2);

		JScrollPane scrollPanePostulante = new JScrollPane(listaPostulante);

		GridBagConstraints gbc_Postulantes = new GridBagConstraints();
		gbc_Postulantes.insets = new Insets(0, 0, 5, 5);
		gbc_Postulantes.fill = GridBagConstraints.BOTH;
		gbc_Postulantes.gridx = 3;
		gbc_Postulantes.gridy = 13;

		getContentPane().add(scrollPanePostulante, gbc_Postulantes);

		listaPostulante.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listaPostulante.getSelectedIndex() != -1) {
				dtp.setPostulante(listaPostulante.getSelectedValue());
			}
		//	postulanteSeleccionado = listaPostulante.getSelectedValue();
		});

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
		gbc_btnAceptar.gridy = 15;
		getContentPane().add(btnAceptar, gbc_btnAceptar);


	}
}
