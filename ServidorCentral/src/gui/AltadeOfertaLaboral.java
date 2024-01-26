package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

import java.time.LocalDate;

import excepciones.EmpresaNoExiste;
import excepciones.OfertaYaExiste;
import excepciones.TipoOfertaNoExiste;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConUsuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AltadeOfertaLaboral extends JInternalFrame {
	private JTextField nombre;
	private JTextField descripcion;
	private JTextField horario;
	private JTextField remuneracion;
	private JTextField ciudad;
	private JTextField departamento;
	private JList<String> listaTipo;

	private String selectedEmpresa;
	private List<String> selectedKeywords = new ArrayList<>();
	
	private boolean esNumerico(String str) {
	    return str.matches("\\d+");  // Verifica si la cadena contiene solo dígitos
	}

	private boolean camposCompletos() {
	    if (nombre.getText().trim().isEmpty() || descripcion.getText().trim().isEmpty() || 
	        horario.getText().trim().isEmpty() || remuneracion.getText().trim().isEmpty() || 
	        ciudad.getText().trim().isEmpty() || departamento.getText().trim().isEmpty()) {
	        return false;
	    }

	    if (selectedEmpresa == null || selectedKeywords.isEmpty() || listaTipo.getSelectedValue() == null) {
	        return false;
	    }

	    return true;
	}

	private void actualizarEstadoBotonAceptar(JButton btnAceptar) {
		btnAceptar.setEnabled(camposCompletos());
	}

	public AltadeOfertaLaboral() {
		setResizable(true);
		setClosable(true);
		setTitle("Alta de Oferta Laboral");
		setSize(700, 650);

		JButton btnAceptar = new JButton("Aceptar");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, -10, 124, 138, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 119, 52, 0, 0, 0, 0, 0, 0, 37, 41, 0, 0, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel_3 = new JLabel("Seleccionar Empresa");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		GridBagConstraints gbc_ListaEmpresas = new GridBagConstraints();
		gbc_ListaEmpresas.gridwidth = 1;
		gbc_ListaEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_ListaEmpresas.fill = GridBagConstraints.BOTH;
		gbc_ListaEmpresas.gridx = 4;
		gbc_ListaEmpresas.gridy = 0;

		ControllerFactory fac = ControllerFactory.getInstance();
		IConUsuario icu = fac.getIControladorUsuario();

		Set<String> empresas = icu.listarEmpresas();

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String empresa : empresas) {
			listModel.addElement(empresa);
		}

		JList<String> listaEmpresas = new JList<>(listModel);
		listaEmpresas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listaEmpresas = new GridBagConstraints();
		gbc_listaEmpresas.gridwidth = 2;
		gbc_listaEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_listaEmpresas.fill = GridBagConstraints.BOTH;
		gbc_listaEmpresas.gridx = 3;
		gbc_listaEmpresas.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(listaEmpresas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, gbc_listaEmpresas);

		listaEmpresas.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				selectedEmpresa = listaEmpresas.getSelectedValue();
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Seleccionar Tipo");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		IConOferta ico = fac.getIControladorOferta();
		Set<String> tipos = ico.listarTipoOferta();

		DefaultListModel<String> listModel2 = new DefaultListModel<>();
		for (String tipo : tipos) {
		    listModel2.addElement(tipo);
		}

		listaTipo = new JList<>(listModel2);
		listaTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneTipo = new JScrollPane(listaTipo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		GridBagConstraints gbc_ListaTipo = new GridBagConstraints();
		gbc_ListaTipo.gridheight = 2;
		gbc_ListaTipo.gridwidth = 2;
		gbc_ListaTipo.insets = new Insets(0, 0, 5, 5);
		gbc_ListaTipo.fill = GridBagConstraints.BOTH;
		gbc_ListaTipo.gridx = 3;
		gbc_ListaTipo.gridy = 1;

		getContentPane().add(scrollPaneTipo, gbc_ListaTipo);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);

		nombre = new JTextField();
		GridBagConstraints gbc_Nombre = new GridBagConstraints();
		gbc_Nombre.gridwidth = 2;
		gbc_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_Nombre.gridx = 3;
		gbc_Nombre.gridy = 3;
		getContentPane().add(nombre, gbc_Nombre);
		nombre.setColumns(10);
		

		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 4;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		descripcion = new JTextField();
		GridBagConstraints gbc_Descripcion = new GridBagConstraints();
		gbc_Descripcion.gridwidth = 2;
		gbc_Descripcion.insets = new Insets(0, 0, 5, 5);
		gbc_Descripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_Descripcion.gridx = 3;
		gbc_Descripcion.gridy = 4;
		getContentPane().add(descripcion, gbc_Descripcion);
		descripcion.setColumns(10);
		

		JLabel lblNewLabel_1 = new JLabel("Horario");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 5;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);


		horario = new JTextField();
		GridBagConstraints gbc_Horario = new GridBagConstraints();
		gbc_Horario.gridwidth = 2;
		gbc_Horario.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_Horario.insets = new Insets(0, 0, 5, 5);
		gbc_Horario.fill = GridBagConstraints.HORIZONTAL;
		gbc_Horario.gridx = 3;
		gbc_Horario.gridy = 5;
		getContentPane().add(horario, gbc_Horario);
		horario.setColumns(10);
		

		JLabel lblHorario = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.EAST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 2;
		gbc_lblHorario.gridy = 6;
		getContentPane().add(lblHorario, gbc_lblHorario);

		remuneracion = new JTextField();
		GridBagConstraints gbc_Remuneracion = new GridBagConstraints();
		gbc_Remuneracion.gridwidth = 2;
		gbc_Remuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_Remuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_Remuneracion.gridx = 3;
		gbc_Remuneracion.gridy = 6;
		getContentPane().add(remuneracion, gbc_Remuneracion);
		remuneracion.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 2;
		gbc_lblCiudad.gridy = 7;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		ciudad = new JTextField();
		GridBagConstraints gbc_Ciudad = new GridBagConstraints();
		gbc_Ciudad.gridwidth = 2;
		gbc_Ciudad.insets = new Insets(0, 0, 5, 5);
		gbc_Ciudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_Ciudad.gridx = 3;
		gbc_Ciudad.gridy = 7;
		getContentPane().add(ciudad, gbc_Ciudad);
		ciudad.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 2;
		gbc_lblDepartamento.gridy = 8;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		departamento = new JTextField();
		GridBagConstraints gbc_Departamento = new GridBagConstraints();
		gbc_Departamento.gridwidth = 2;
		gbc_Departamento.insets = new Insets(0, 0, 5, 5);
		gbc_Departamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_Departamento.gridx = 3;
		gbc_Departamento.gridy = 8;
		getContentPane().add(departamento, gbc_Departamento);
		departamento.setColumns(10);
		


		btnAceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        if (camposCompletos()) {
		            String nom = nombre.getText();
		            String desc = descripcion.getText();
		            String hora = horario.getText();
		            String remunera = remuneracion.getText();
		            String ciu = ciudad.getText();
		            String depar = departamento.getText();
		            String selectedTipo = listaTipo.getSelectedValue();
		            
		            if (!camposCompletos()) {
		                JOptionPane.showMessageDialog(null, "Por favor, no deje campos vacíos.", "E", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            float remuneration;
		            if (esNumerico(remunera)){
		                remuneration = Float.parseFloat(remunera);
		            } else {
		                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la remuneración.", "", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            
		            LocalDate fechaAlta = LocalDate.now();
		            Set<String> keywords = new HashSet<>(selectedKeywords);
		            
		            try {
		                ico.altaOfertaLaboral(selectedEmpresa, selectedTipo, nom, desc, hora, remuneration, ciu, depar, fechaAlta, keywords);
		                JOptionPane.showMessageDialog(null, "Oferta registrada con éxito!");
		                dispose();
		            } catch (TipoOfertaNoExiste | OfertaYaExiste | EmpresaNoExiste ex) {
		                JOptionPane.showMessageDialog(null, "Error al registrar la oferta: " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		            }

		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos y seleccione al menos un elemento de cada lista.", "", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});




		JLabel lblKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.anchor = GridBagConstraints.EAST;
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.gridx = 2;
		gbc_lblKeywords.gridy = 9;
		getContentPane().add(lblKeywords, gbc_lblKeywords);

		Set<String> keywords = ico.listarKeyWord();

		DefaultListModel<String> listKeywords = new DefaultListModel<>();
		for (String key : keywords) {
			listKeywords.addElement(key);
		}

		JList<String> keywordsList = new JList<>(listKeywords);
		keywordsList.setValueIsAdjusting(true);

		JScrollPane scrollPane4 = new JScrollPane(keywordsList);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 9;

		getContentPane().add(scrollPane4, gbc_scrollPane);

		keywordsList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				selectedKeywords = keywordsList.getSelectedValuesList();
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 12;
		getContentPane().add(btnAceptar, gbc_btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 12;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}
}