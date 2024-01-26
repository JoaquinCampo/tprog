package gui;


import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.awt.event.ActionEvent;

import datatypes.DTEmpresa;
import datatypes.DTPostulante;
import datatypes.DTUsuario;

import javax.swing.JScrollPane;


import logica.ControllerFactory;
import logica.IConUsuario;


import javax.swing.JList;

public class ModificarDatosdeUsuario extends JInternalFrame {
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtLink;
	private JTextArea txtDescripcion;
	private int tipo = 0;//1 empresa, 0 postulante 
	
	//private String txt;
	private DTUsuario dtu = null;
	
	private Integer[] obtenerDias() {
		Integer[] dias = new Integer[31];
		for (int i = 0; i < 31; i++) {
			dias[i] = i + 1;
		}
		return dias;
	}

	private Integer[] obtenerMeses() {
		Integer[] dias = new Integer[31];
		for (int i = 0; i < 12; i++) {
			dias[i] = i + 1;
		}
		return dias;
	}


	private Integer[] obtenerAnios() {
		Integer[] anios = new Integer[124];
		int anioActual = LocalDate.now().getYear();
		for (int i = 0; i < 124; i++) {
			anios[i] = anioActual - i;
		}
		return anios;
	}

	public ModificarDatosdeUsuario() {
		setClosable(true);
		setResizable(true);

		ControllerFactory fac = ControllerFactory.getInstance();
		IConUsuario con = fac.getIControladorUsuario();

		setTitle("Modificar Datos de Usuario");
		setSize(600, 500);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, -10, 124, 138, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 119, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel_3 = new JLabel("Seleccionar Usuario");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel LinkFechaNacimiento = new JLabel("Link");
		GridBagConstraints gbc_LinkFechaNacimiento = new GridBagConstraints();
		gbc_LinkFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_LinkFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_LinkFechaNacimiento.gridx = 2;
		gbc_LinkFechaNacimiento.gridy = 5;
		getContentPane().add(LinkFechaNacimiento, gbc_LinkFechaNacimiento);

		JLabel DescripcionNacionalidad = new JLabel("Descricpion");
		GridBagConstraints gbc_DescripcionNacionalidad = new GridBagConstraints();
		gbc_DescripcionNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_DescripcionNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_DescripcionNacionalidad.gridx = 2;
		gbc_DescripcionNacionalidad.gridy = 7;
		getContentPane().add(DescripcionNacionalidad, gbc_DescripcionNacionalidad);
		
		JComboBox diacomboBox = new JComboBox<>(obtenerDias());
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		diacomboBox.setVisible(false);
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 9;
		getContentPane().add(diacomboBox, gbc_comboBox_2);
		
		JComboBox mescomboBox = new JComboBox<>(obtenerMeses());
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		mescomboBox.setVisible(false);
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 9;
		getContentPane().add(mescomboBox, gbc_comboBox_1);
		
		JComboBox aniocomboBox = new JComboBox<>(obtenerAnios());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		aniocomboBox.setVisible(false);
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 9;
		getContentPane().add(aniocomboBox, gbc_comboBox);
		

		Set<String> usuarios = con.listarUsuarios();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String actual : usuarios) {
			listModel.addElement(actual);
		}
		JList<String> list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollpane = new JScrollPane(list);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && list.getSelectedIndex() != 1) {
				limpiarFormulario();
				String selectedUser = list.getSelectedValue();
				dtu = con.mostrarDatos(selectedUser);
				txtNickname.setText(dtu.getNickname());
				txtNombre.setText(dtu.getNombre());
				txtApellido.setText(dtu.getApellido());
				txtEmail.setText(dtu.getCorreo());
				if (dtu instanceof DTEmpresa) {
					DTEmpresa dte = (DTEmpresa) dtu;
					tipo = 1;
					diacomboBox.setVisible(false);
					mescomboBox.setVisible(false);
					aniocomboBox.setVisible(false);
					txtLink.setText(dte.getWeb());
					txtDescripcion.setText(dte.getDescripcion());
					LinkFechaNacimiento.setText("Link");
					DescripcionNacionalidad.setText("Descripcion");
					txtDescripcion.setVisible(true);
					DescripcionNacionalidad.setVisible(true);
				}
				if (dtu instanceof DTPostulante) {
					DTPostulante dtp = (DTPostulante) dtu;
					tipo = 0;
					DescripcionNacionalidad.setVisible(false);
					txtDescripcion.setVisible(false);
					txtLink.setText(dtp.getNacionalidad());
					LocalDate fecha = dtp.getFechaNacimiento();
					diacomboBox.setSelectedItem(fecha.getDayOfMonth());
					mescomboBox.setSelectedItem(fecha.getMonthValue());
					aniocomboBox.setSelectedItem(fecha.getYear());
					diacomboBox.setVisible(true);
					mescomboBox.setVisible(true);
					aniocomboBox.setVisible(true);
					LinkFechaNacimiento.setText("Nacionalidad");
				}
			}
		});
		

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 3;
		gbc_list.gridy = 0;
		getContentPane().add(scrollpane, gbc_list);

		JLabel lblNewLabel = new JLabel("Nickname");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.gridwidth = 2;
		gbc_txtNickname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNickname.gridx = 3;
		gbc_txtNickname.gridy = 1;
		getContentPane().add(txtNickname, gbc_txtNickname);
		txtNickname.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 2;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 2;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_txtApellido.gridwidth = 2;
		gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.gridx = 3;
		gbc_txtApellido.gridy = 3;
		getContentPane().add(txtApellido, gbc_txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Correo Electronico");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 2;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 4;
		getContentPane().add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		txtLink = new JTextField();
		GridBagConstraints gbc_txtLink = new GridBagConstraints();
		gbc_txtLink.gridwidth = 2;
		gbc_txtLink.insets = new Insets(0, 0, 5, 5);
		gbc_txtLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLink.gridx = 3;
		gbc_txtLink.gridy = 5;
		getContentPane().add(txtLink, gbc_txtLink);
		txtLink.setColumns(10);

		txtDescripcion = new JTextArea();
		txtDescripcion.setRows(4);
		txtDescripcion.setColumns(2);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
		JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);
		GridBagConstraints gbc_Descripcion = new GridBagConstraints();
		gbc_Descripcion.gridheight = 3;
		gbc_Descripcion.gridwidth = 2;
		gbc_Descripcion.insets = new Insets(0, 0, 5, 5);
		gbc_Descripcion.fill = GridBagConstraints.BOTH;
		gbc_Descripcion.gridx = 3;
		gbc_Descripcion.gridy = 6;
		getContentPane().add(descripcionScroll, gbc_Descripcion);
		



		JButton btnCancelar = new JButton("Aceptar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!(txtNickname.getText() == "" | txtNombre.getText() == "" | txtApellido.getText() == "" | txtLink.getText() == "")) {
					if (tipo == 1) {
						con.modificarEmpresa(txtNickname.getText(), txtNombre.getText(), txtApellido.getText(), txtDescripcion.getText(), txtLink.getText());
					}
					if (tipo == 0) {
						int año = (int) aniocomboBox.getSelectedItem();
						int mes = (int) mescomboBox.getSelectedItem();
						int día = (int) diacomboBox.getSelectedItem();
						con.modificarPostulante(txtNickname.getText(), txtNombre.getText(), txtApellido.getText(), LocalDate.of(año, mes, día), txtLink.getText());
					}
				}
				limpiarFormulario();
				setVisible(false);
				dispose();
			}
		});
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbc_btnCancelar);


	}

	private void limpiarFormulario() {
		txtNickname.setText("");
		txtApellido.setText("");
		txtNombre.setText("");
		txtEmail.setText("");
		txtDescripcion.setText("");
		txtLink.setText("");
	}
}
