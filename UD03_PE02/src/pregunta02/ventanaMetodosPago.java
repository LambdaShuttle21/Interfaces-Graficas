package pregunta02;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ventanaMetodosPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ventanaMetodosPago() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// cambiar el texto de las opciones de pago y bolsa
		String[] arrTxtOpcionesPago = { "Efectivo", "Tarjeta", "Transferencia", "Bizum" };
		String[] arrTxtOpcionesBolsa = { "Si", "No" };

		ButtonGroup grupoDeOpciones = new ButtonGroup();
		ButtonGroup grupoBolsa = new ButtonGroup();

		JLabel lblMetodo = new JLabel("Seleccione Metodo de Pago");
		lblMetodo.setBounds(25, 6, 191, 16);
		contentPane.add(lblMetodo);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 28, 171, 140);
		contentPane.add(panel);
		panel.setLayout(null);

		JRadioButton rdbtnOpcion1 = new JRadioButton("Efectivo");
		rdbtnOpcion1.setBounds(6, 6, 141, 23);
		panel.add(rdbtnOpcion1);

		JRadioButton rdbtnOpcion2 = new JRadioButton("Tarjeta");
		rdbtnOpcion2.setBounds(6, 41, 141, 23);
		panel.add(rdbtnOpcion2);

		JRadioButton rdbtnOpcion3 = new JRadioButton("Transferencia");
		rdbtnOpcion3.setBounds(6, 76, 141, 23);
		panel.add(rdbtnOpcion3);

		JRadioButton rdbtnOpcion4 = new JRadioButton("Bizum");
		rdbtnOpcion4.setBounds(6, 111, 141, 23);
		panel.add(rdbtnOpcion4);

		JLabel lblMetodoSelected = new JLabel("Metodo Seleccionado:");
		lblMetodoSelected.setBounds(25, 194, 300, 16);
		contentPane.add(lblMetodoSelected);

		JButton btnAplicarMetodo = new JButton("Realizar Pago");
		btnAplicarMetodo.setBounds(230, 143, 117, 29);
		contentPane.add(btnAplicarMetodo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(236, 28, 111, 78);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Si");
		rdbtnNewRadioButton_4.setBounds(6, 6, 54, 23);
		panel_1.add(rdbtnNewRadioButton_4);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("No");
		rdbtnNewRadioButton_5.setBounds(6, 41, 68, 23);
		panel_1.add(rdbtnNewRadioButton_5);

		// Agrupar los botones de radio para que solo se pueda seleccionar uno a la vez
		grupoDeOpciones.add(rdbtnOpcion1);
		grupoDeOpciones.add(rdbtnOpcion2);
		grupoDeOpciones.add(rdbtnOpcion3);
		grupoDeOpciones.add(rdbtnOpcion4);

		grupoBolsa.add(rdbtnNewRadioButton_4);
		grupoBolsa.add(rdbtnNewRadioButton_5);

		JLabel lblBolsaText = new JLabel("Bolsas");
		lblBolsaText.setBounds(239, 6, 108, 16);
		contentPane.add(lblBolsaText);

		JLabel lblBolsaRequired = new JLabel("Necesita Bolsa:");
		lblBolsaRequired.setBounds(25, 222, 300, 16);
		contentPane.add(lblBolsaRequired);

		// Agregar ActionListener al botón para mostrar el método de pago seleccionado y
		// si se necesita bolsa
		btnAplicarMetodo.addActionListener(e -> {
			String metodoPago = "";
			if (rdbtnOpcion1.isSelected()) {
				metodoPago = rdbtnOpcion1.getText();
			} else if (rdbtnOpcion2.isSelected()) {
				metodoPago = rdbtnOpcion2.getText();
			} else if (rdbtnOpcion3.isSelected()) {
				metodoPago = rdbtnOpcion3.getText();
			} else if (rdbtnOpcion4.isSelected()) {
				metodoPago = rdbtnOpcion4.getText();
			}

			lblMetodoSelected.setText("Metodo Seleccionado: " + metodoPago);

			String bolsaSeleccionada = "";
			if (rdbtnNewRadioButton_4.isSelected()) {
				bolsaSeleccionada = arrTxtOpcionesBolsa[0];
			} else if (rdbtnNewRadioButton_5.isSelected()) {
				bolsaSeleccionada = arrTxtOpcionesBolsa[1];
			}

			lblBolsaRequired.setText("Necesita Bolsa: " + bolsaSeleccionada);
		});
	}
}
