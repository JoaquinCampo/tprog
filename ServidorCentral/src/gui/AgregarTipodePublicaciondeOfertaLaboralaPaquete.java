package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;


import datatypes.DTPaquete;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConPaquete;

import javax.swing.JList;
import javax.swing.JOptionPane;
import excepciones.TipoOfertaNoExiste;

public final class AgregarTipodePublicaciondeOfertaLaboralaPaquete extends JInternalFrame {
	//private String txt;
	private JTextField textField;
	private String tipo;
	private String paquete;
	//private JList<String> listaPaquetes;
	//private JList<String> listaTipos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarTipodePublicaciondeOfertaLaboralaPaquete frame = new AgregarTipodePublicaciondeOfertaLaboralaPaquete();
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
	public AgregarTipodePublicaciondeOfertaLaboralaPaquete() {
		setTitle("AgregarTipodePublicaciondeOfertaLaboralaPaquete");
		setBounds(100, 100, 686, 368);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Selecciona un paquete");
		lblNewLabel_3.setBounds(68, 37, 103, 13);
		getContentPane().add(lblNewLabel_3);
		
		ControllerFactory fac = ControllerFactory.getInstance();
		IConPaquete icp = fac.getIControladorPaquete();
		IConOferta ico=fac.getIControladorOferta();
		
		Set<DTPaquete> paquetes = icp.listarPaquete();

		if (paquetes != null) {
		    Set<String> Npaquetes = new HashSet<>();
		    
		    for (DTPaquete paq : paquetes) {
		        if (!paq.getComprado())
		        	Npaquetes.add(paq.getNombre());
		    }
		    
		    DefaultListModel<String> lista1 = new DefaultListModel<>();
		    
		    for (String paq : Npaquetes) {
		        lista1.addElement(paq);
		    }
		
		JList<String> listaPaquetes;
		listaPaquetes = new JList<>(lista1);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 3;
		gbc_list.gridy = 0;
		listaPaquetes.setBounds(184, 0, 485, 87);

		listaPaquetes.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting() && listaPaquetes.getSelectedIndex() != -1) {
		        this.paquete = listaPaquetes.getSelectedValue(); 
		    }
		});

		getContentPane().add(listaPaquetes);
		}		
		JLabel lblNewLabel_1 = new JLabel("Selecciona un Tipo");
		lblNewLabel_1.setBounds(76, 136, 86, 13);
		getContentPane().add(lblNewLabel_1);
		
		DefaultListModel<String> lista2 = new DefaultListModel<>();
		Set<String> tipos=ico.listarTipoOferta();
		for (String tpo: tipos) {
			lista2.addElement(tpo);
		}
		JList<String> listaTipos;
		listaTipos = new JList<>(lista2);
		listaTipos.setBounds(184, 92, 485, 102);
		getContentPane().add(listaTipos);
		listaTipos.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting() && listaTipos.getSelectedIndex() != -1) {
		        this.tipo = listaTipos.getSelectedValue(); 
		    }
		});
		
		JLabel lblNombre = new JLabel("Cantidad");
		lblNombre.setBounds(139, 232, 40, 13);
		getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(184, 229, 485, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCancelar_1 = new JButton("Aceptar");
		btnCancelar_1.setBounds(510, 283, 67, 21);
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {      
			        String cant = textField.getText();
			        agregarTipo(paquete, tipo, cant);
			}
		});
		getContentPane().add(btnCancelar_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(596, 283, 73, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                dispose();
			}
		});
		getContentPane().add(btnCancelar);
		

	}
	public void agregarTipo(String pqt, String tpo, String cant) {
		
	    if (paquete == null) {
	        JOptionPane.showMessageDialog(this, "Por favor, seleccione un Paquete.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

		if (tipo == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un tipo de oferta.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
            int cantidad = Integer.parseInt(cant);
            if (cantidad > 0) {
            	ControllerFactory con = ControllerFactory.getInstance();
				IConPaquete icp = con.getIControladorPaquete();
            	try {
					icp.agregarTipoDeOfertaAPaquete(paquete, tipo, cantidad);
					dispose();
				} catch (TipoOfertaNoExiste e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
		
	}