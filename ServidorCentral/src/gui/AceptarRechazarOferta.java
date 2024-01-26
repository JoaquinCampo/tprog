package gui;


import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;


import datatypes.DTUsuario;
import datatypes.Estados;
import datatypes.DTEmpresa;
import datatypes.DTOferta;

import logica.ControllerFactory;
import logica.IColOferta;
import logica.CollectionFactory;
import logica.IConUsuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AceptarRechazarOferta extends JInternalFrame {

//	private GridBagConstraints gbc;
//	private JPanel detailsPanel;

	/**
	 * 
	 */
	private JList<String> listOfertas;
	private JList<String> listaEmpresas;
//	private String empresaSeleccionada;
	private String ofertaSeleccionada;

	/**
	 * Create the frame.
	 */
	public AceptarRechazarOferta() {
		//detailsPanel = new JPanel(new GridBagLayout());

		setResizable(true);
		ControllerFactory con = ControllerFactory.getInstance();
		//IConOferta conOferta = con.getIControladorOferta();
		IConUsuario conUsuario = con.getIControladorUsuario();

		setClosable(true);
		setTitle("Aceptar/Rechazar oferta");
		setSize(731, 486);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 144, 30 };

		getContentPane().setLayout(gridBagLayout);

		JLabel Empresas = new JLabel("Selecciona una empresa");
		GridBagConstraints gbc_Empresas = new GridBagConstraints();
		gbc_Empresas.insets = new Insets(0, 0, 5, 5);
		gbc_Empresas.gridx = 2;
		gbc_Empresas.gridy = 1;
		getContentPane().add(Empresas, gbc_Empresas);

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
		gbc_list.gridy = 1;
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
						if (oferta.getEstado() == Estados.Ingresada) {
							ofertasModel.addElement(oferta.getNombre());}
					}
					listOfertas.setModel(ofertasModel);
				//	empresaSeleccionada = listaEmpresas.getSelectedValue();
				}
			}
		});

		DefaultListModel<String> listModel1 = new DefaultListModel<>();
		for (String oferta : ofertas) {
			listModel1.addElement(oferta);
		}

		JLabel ingresadas = new JLabel("Ofertas Laborales \"ingresadas\"");
		GridBagConstraints gbc_ingresadas = new GridBagConstraints();
		gbc_ingresadas.anchor = GridBagConstraints.EAST;
		gbc_ingresadas.insets = new Insets(0, 0, 5, 5);
		gbc_ingresadas.gridx = 2;
		gbc_ingresadas.gridy = 3;
		getContentPane().add(ingresadas, gbc_ingresadas);
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
				ofertaSeleccionada = listOfertas.getSelectedValue();
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chequearDatos()) {
					CollectionFactory fcol = CollectionFactory.getInstance();
					IColOferta col = fcol.getIColOferta();
					col.aceptarOferta(ofertaSeleccionada);
					JOptionPane.showMessageDialog(null, "Oferta aceptada correctamente.", "",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.EAST;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 5;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		JButton btnNewButton = new JButton("Rechazar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chequearDatos()) {
					CollectionFactory fcol = CollectionFactory.getInstance();
					IColOferta col = fcol.getIColOferta();
					col.rechazarOferta(ofertaSeleccionada);
					JOptionPane.showMessageDialog(null, "Oferta rechazada correctamente.", "",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();

				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 5;
		getContentPane().add(btnNewButton, gbc_btnNewButton);

	}

	private boolean chequearDatos() {
		return true;
	}
}
