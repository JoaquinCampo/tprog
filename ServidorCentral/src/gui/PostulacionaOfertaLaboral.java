package gui;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.util.HashSet;



import datatypes.DTEmpresa;
import datatypes.DTPostulacion;
import datatypes.DTOferta;
import datatypes.Estados;

import datatypes.DTPostulante;
import datatypes.DTUsuario;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;


import java.time.LocalDate;

import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConUsuario;
import excepciones.OfertaNoExiste;
import excepciones.OfertaNoValidaParaEsaFecha;
import excepciones.PostulacionYaExiste;


public final class PostulacionaOfertaLaboral extends JInternalFrame {
	private JTextField txtRemuneracion;
	private JTextField txtCiudad;
	private JTextField txtDepartamento;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtHorario;
	private JTextField txtcurriculum;
	private JTextField txtMotivacion;
	//private GridBagConstraints gbc;
	//private JPanel detailsPanel;

	private JList<String> listOfertas;
	private JList<String> listaEmpresas;
	private JList<String> listaPostulante;
	private JTextField textFieldFecha;

	private JComboBox<Integer> diaComboBox;
	private JComboBox<String> mesComboBox;
	private JComboBox<Integer> anioComboBox;
	
	private String empresaSeleccionada;
	private String ofertaSeleccionada;
	private String postulanteSeleccionado;

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



	public void postular(String nombreOferta, String nombrePostulante, String ofertaSelec) {
		String motivacion = txtMotivacion.getText();
		String curriculum = txtcurriculum.getText();

		// Verificaciones de selección en las listas
		if (listaEmpresas.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una empresa.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (listOfertas.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una oferta laboral.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (listaPostulante.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un postulante.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		int dia = (Integer) diaComboBox.getSelectedItem();
		int mes = mesComboBox.getSelectedIndex() + 1;
		int anio = (Integer) anioComboBox.getSelectedItem();
		LocalDate localDate = LocalDate.of(anio, mes, dia);

		if (motivacion.isEmpty() || curriculum.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				ControllerFactory con = ControllerFactory.getInstance();
				IConOferta conOferta = con.getIControladorOferta();
				conOferta.altaPostulacion(ofertaSelec, nombrePostulante, curriculum, motivacion, localDate);
				JOptionPane.showMessageDialog(this, "Postulacion registrada con exito!", "", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (PostulacionYaExiste | OfertaNoExiste e) {
				JOptionPane.showMessageDialog(this, "Ya existe una postulacion de este postulante a esta oferta", "Error", JOptionPane.ERROR_MESSAGE);
			}catch (OfertaNoValidaParaEsaFecha e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public PostulacionaOfertaLaboral() {
	//	detailsPanel = new JPanel(new GridBagLayout());

		setResizable(true);
		ControllerFactory con = ControllerFactory.getInstance();
		IConOferta conOferta = con.getIControladorOferta();
		IConUsuario conUsuario = con.getIControladorUsuario();

		setClosable(true);
		setTitle("Postulacion a Oferta Laboral");
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
					    String estado = oferta.getEstado().name();
					    if (estado.equals("Confirmada")) {
					        ofertasModel.addElement(oferta.getNombre());
					    }
					}
					listOfertas.setModel(ofertasModel);
					empresaSeleccionada = listaEmpresas.getSelectedValue();
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
			postulanteSeleccionado = listaPostulante.getSelectedValue();
		});

		JLabel lblNewLabel_8 = new JLabel("curriculum reducido");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 15;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);

		txtcurriculum = new JTextField();
		GridBagConstraints gbc_curriculum = new GridBagConstraints();
		gbc_curriculum.insets = new Insets(0, 0, 5, 5);
		gbc_curriculum.fill = GridBagConstraints.HORIZONTAL;
		gbc_curriculum.gridx = 3;
		gbc_curriculum.gridy = 15;
		getContentPane().add(txtcurriculum, gbc_curriculum);
		txtcurriculum.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Motivacion");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 16;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);

		txtMotivacion = new JTextField();
		GridBagConstraints gbc_txtMotivacion = new GridBagConstraints();
		gbc_txtMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_txtMotivacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMotivacion.gridx = 3;
		gbc_txtMotivacion.gridy = 16;
		getContentPane().add(txtMotivacion, gbc_txtMotivacion);
		txtMotivacion.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Fecha de Postulacion");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 17;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				postular(empresaSeleccionada, postulanteSeleccionado, ofertaSeleccionada);
			}
		});

		GridBagConstraints gbc_diaComboBox = new GridBagConstraints();
		gbc_diaComboBox.anchor = GridBagConstraints.WEST;
		gbc_diaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_diaComboBox.gridx = 3;
		gbc_diaComboBox.gridy = 17;
		diaComboBox = new JComboBox<>(obtenerDias());
		getContentPane().add(diaComboBox, gbc_diaComboBox);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.EAST;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 18;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 18;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

		GridBagConstraints gbc_mesComboBox = new GridBagConstraints();
		gbc_mesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_mesComboBox.gridx = 3;
		gbc_mesComboBox.gridy = 17;
		mesComboBox = new JComboBox<>(obtenerMeses());
		getContentPane().add(mesComboBox, gbc_mesComboBox);

		GridBagConstraints gbc_anioComboBox = new GridBagConstraints();
		gbc_anioComboBox.anchor = GridBagConstraints.EAST;
		gbc_anioComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_anioComboBox.gridx = 3;
		gbc_anioComboBox.gridy = 17;
		anioComboBox = new JComboBox<>(obtenerAnios());
		getContentPane().add(anioComboBox, gbc_anioComboBox);

	}

}
