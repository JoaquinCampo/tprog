package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Insets;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.ControllerFactory;
import logica.IConUsuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaUsuario extends JInternalFrame {
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtDescripcion;
	private JTextField txtLink;
	private JTextField txtContrasena;
	private JTextField txtConcon;
	private int tipo = 0; //1 empresa, 0 postulante 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario frame = new AltaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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

	public AltaUsuario() {
		setTitle("Alta Empresa");
		setBounds(100, 100, 663, 467);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 242, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nickname");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNickname = new JTextField();
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNickname.gridx = 4;
		gbc_txtNickname.gridy = 1;
		getContentPane().add(txtNickname, gbc_txtNickname);
		txtNickname.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 4;
		gbc_txtNombre.gridy = 2;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Apellido");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.gridx = 4;
		gbc_txtApellido.gridy = 3;
		getContentPane().add(txtApellido, gbc_txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Contrasena");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtContrasena = new JTextField();
		GridBagConstraints gbc_txtContrasena = new GridBagConstraints();
		gbc_txtContrasena.insets = new Insets(0, 0, 5, 5);
		gbc_txtContrasena.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContrasena.gridx = 4;
		gbc_txtContrasena.gridy = 4;
		getContentPane().add(txtContrasena, gbc_txtContrasena);
		txtContrasena.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Confirmacion Contrasena");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		txtConcon = new JTextField();
		GridBagConstraints gbc_txtConcon = new GridBagConstraints();
		gbc_txtConcon.insets = new Insets(0, 0, 5, 5);
		gbc_txtConcon.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtConcon.gridx = 4;
		gbc_txtConcon.gridy = 5;
		getContentPane().add(txtConcon, gbc_txtConcon);
		txtConcon.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 6;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 4;
		gbc_txtEmail.gridy = 6;
		getContentPane().add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		txtDescripcion = new JTextField();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.gridheight = 3;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
		gbc_txtDescripcion.gridx = 4;
		gbc_txtDescripcion.gridy = 7;
		getContentPane().add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblDesc = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 3;
		gbc_lblDesc.gridy = 8;
		getContentPane().add(lblDesc, gbc_lblDesc);
		
				JComboBox diacomboBox = new JComboBox<>(obtenerDias());
				diacomboBox.setVisible(false);
				GridBagConstraints gbc_diacomboBox = new GridBagConstraints();
				gbc_diacomboBox.anchor = GridBagConstraints.EAST;
				gbc_diacomboBox.insets = new Insets(0, 0, 5, 5);
				gbc_diacomboBox.gridx = 3;
				gbc_diacomboBox.gridy = 10;
				getContentPane().add(diacomboBox, gbc_diacomboBox);
		
				JComboBox mescomboBox = new JComboBox<>(obtenerMeses());
				mescomboBox.setVisible(false);
				GridBagConstraints gbc_mescomboBox = new GridBagConstraints();
				gbc_mescomboBox.insets = new Insets(0, 0, 5, 5);
				gbc_mescomboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_mescomboBox.gridx = 4;
				gbc_mescomboBox.gridy = 10;
				getContentPane().add(mescomboBox, gbc_mescomboBox);
		
				JComboBox aniocomboBox = new JComboBox<>(obtenerAnios());
				aniocomboBox.setVisible(false);
				GridBagConstraints gbc_anioComboBox = new GridBagConstraints();
				gbc_anioComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_anioComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_anioComboBox.gridx = 5;
				gbc_anioComboBox.gridy = 10;
				getContentPane().add(aniocomboBox, gbc_anioComboBox);

		JLabel lblLink = new JLabel("Link");
		GridBagConstraints gbc_lblLink = new GridBagConstraints();
		gbc_lblLink.anchor = GridBagConstraints.EAST;
		gbc_lblLink.insets = new Insets(0, 0, 5, 5);
		gbc_lblLink.gridx = 3;
		gbc_lblLink.gridy = 11;
		getContentPane().add(lblLink, gbc_lblLink);

		txtLink = new JTextField();
		GridBagConstraints gbc_txtLink = new GridBagConstraints();
		gbc_txtLink.insets = new Insets(0, 0, 5, 5);
		gbc_txtLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLink.gridx = 4;
		gbc_txtLink.gridy = 11;
		getContentPane().add(txtLink, gbc_txtLink);
		txtLink.setColumns(10);
		
		JRadioButton btnSwitch = new JRadioButton("Cambiar Tipo");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipo == 0) {
					setTitle("Alta Postulante");
					tipo = 1;
					diacomboBox.setVisible(true);
					mescomboBox.setVisible(true);
					aniocomboBox.setVisible(true);
					lblLink.setText("Nacionalidad");
					lblDesc.setText("Fecha de Nacimiento");
					txtDescripcion.setVisible(false);
				}
				else {
					setTitle("Alta Empresa");
					tipo = 0;
					diacomboBox.setVisible(false);
					mescomboBox.setVisible(false);
					aniocomboBox.setVisible(false);
					lblLink.setText("Link");
					lblDesc.setText("Descripcion");
					txtDescripcion.setVisible(true);

				}
					
			}
			
		});
		
		GridBagConstraints gbc_btnSwitch = new GridBagConstraints();
		gbc_btnSwitch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSwitch.gridwidth = 2;
		gbc_btnSwitch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwitch.gridx = 1;
		gbc_btnSwitch.gridy = 1;
		getContentPane().add(btnSwitch, gbc_btnSwitch);


		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = getTitle();
				if (validarCamposComunes()) {
					if (tipo == "Alta Empresa") {
						String nick = txtNickname.getText();
						String name = txtNombre.getText();
						String ape = txtApellido.getText();
						String mail = txtEmail.getText();
						String lin = txtLink.getText();
						String contra = txtContrasena.getText();
						String desc = txtDescripcion.getText();

						ControllerFactory fac = ControllerFactory.getInstance();
						IConUsuario icu = fac.getIControladorUsuario();

						try {
							icu.altaEmpresa(nick, name, ape, contra, mail, desc, lin);
							JOptionPane.showMessageDialog(null, "Empresa registrada exitosamente.", "",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (UsuarioNickExiste usere) {
							JOptionPane.showMessageDialog(null, usere.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						} catch (UsuarioCorreoExiste usere2) {
							JOptionPane.showMessageDialog(null, usere2.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						}

					} else {
						String nick = txtNickname.getText();
						String name = txtNombre.getText();
						String ape = txtApellido.getText();
						String mail = txtEmail.getText();
						String lin = txtLink.getText();
						String contra = txtContrasena.getText();
						int dia = (int) diacomboBox.getSelectedItem();
						int mes = mescomboBox.getSelectedIndex() + 1;
						int anio = (int) aniocomboBox.getSelectedItem();

						LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);

						ControllerFactory fac = ControllerFactory.getInstance();
						IConUsuario icu = fac.getIControladorUsuario();

						try {
							icu.altaPostulante(nick, name, ape, contra, mail, fechaNacimiento, lin);
							JOptionPane.showMessageDialog(null, "Postulante registrado exitosamente.", "",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (UsuarioNickExiste usere) {
							JOptionPane.showMessageDialog(null, usere.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						} catch (UsuarioCorreoExiste usere2) {
							JOptionPane.showMessageDialog(null, usere2.getMessage(), "", JOptionPane.ERROR_MESSAGE);

						}

					}
				}

			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 4;
		gbc_btnAceptar.gridy = 12;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 12;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private boolean validarCamposComunes() {
		String nick = txtNickname.getText();
		String name = txtNombre.getText();
		String ape = txtApellido.getText();
		String mail = txtEmail.getText();
		String lin = txtLink.getText();
		String contra = txtContrasena.getText();
		String concon = txtConcon.getText();
		if (nick.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo 'Nickname' no puede estar vacío.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacío.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (ape.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo 'Apellido' no puede estar vacío.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (mail.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo 'Correo' no puede estar vacío.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (!esFormatoCorreoValido(mail)) {
			JOptionPane.showMessageDialog(this, "Ingresa un correo electrónico válido.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (lin.isEmpty() && tipo == 1) {
			JOptionPane.showMessageDialog(this, "El ultimo no puede estar vacío. Ingrese la informacion solicitada",
					"Campo inválido", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (contra.isEmpty()) {
			JOptionPane.showMessageDialog(this, "La contrasena no puede ser vacia.", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (concon.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Ingrese la confirmacion de la contraseña. Ingrese la informacion solicitada", "Campo inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!contra.equals(concon)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean esFormatoCorreoValido(String correo) {
		String formatoCorreo = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(formatoCorreo);
		Matcher matcher = pattern.matcher(correo);
		return matcher.matches();

	}
}

