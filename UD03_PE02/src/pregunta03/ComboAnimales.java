package pregunta03;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ComboAnimales extends JFrame {

	// arrays para los animales disponibles
	// crear dichos animales basados en estos nombres y razas asociadas
	// cada petName lleva asociado su petRaza con el mismo índice en dichos arrays
	// el elemento 0 de cada array es el primer animal, y asi sucesivamente...
	String[] petNames = { "Perro", "Gato", "Conejo", "Pajaro" };
	String[] petRaza = { "Pastor Aleman", "Siames", "Belier", "Canario" };

	private JPanel contentPane;
	private JTextField jtfAnimal;
	private JTextField jtfRaza;

	/**
	 * Create the frame.
	 */
	public ComboAnimales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre Animal");
		lblNewLabel.setBounds(16, 11, 121, 16);
		contentPane.add(lblNewLabel);

		jtfAnimal = new JTextField();
		jtfAnimal.setBounds(124, 6, 159, 26);
		contentPane.add(jtfAnimal);
		jtfAnimal.setColumns(10);

		JButton btnNewAnimal = new JButton("Insertar");
		btnNewAnimal.setBounds(295, 36, 117, 29);
		contentPane.add(btnNewAnimal);

		JButton btnAnimalsToList = new JButton("Animales a Lista");
		btnAnimalsToList.setBounds(259, 126, 153, 29);
		contentPane.add(btnAnimalsToList);

		JComboBox<Animal> comboBox = new JComboBox<>();
		comboBox.setBounds(16, 90, 235, 27);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Animales Disponibles");
		lblNewLabel_1.setBounds(16, 70, 137, 16);
		contentPane.add(lblNewLabel_1);

		JList<String> listAnimales = new JList<>();

		JScrollPane scrollPane = new JScrollPane(listAnimales);
		scrollPane.setBounds(16, 158, 396, 108);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("Lista de Animales");
		lblNewLabel_2.setBounds(16, 131, 147, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(16, 41, 96, 16);
		contentPane.add(lblRaza);

		jtfRaza = new JTextField();
		jtfRaza.setColumns(10);
		jtfRaza.setBounds(124, 36, 159, 26);
		contentPane.add(jtfRaza);

		JLabel lblSelectedAnimal = new JLabel("Seleccionado:");
		lblSelectedAnimal.setBounds(259, 94, 185, 16);
		contentPane.add(lblSelectedAnimal);

		for (int i = 0; i < petNames.length; i++) {
			Animal animal = new Animal(petNames[i], petRaza[i]);
			comboBox.addItem(animal);
		}

		btnNewAnimal.addActionListener(e -> {
			String nombre = jtfAnimal.getText();
			String raza = jtfRaza.getText();

			Animal animal = new Animal(nombre, raza);
			comboBox.addItem(animal);

			jtfAnimal.setText("");
			jtfRaza.setText("");
		});

		comboBox.addItemListener(e -> {
			Animal animal = (Animal) comboBox.getSelectedItem();

			if (animal != null) {
				lblSelectedAnimal.setText("Seleccionado: " + animal.getNombre());
			}
		});

		btnAnimalsToList.addActionListener(e -> {
			String[] animales = new String[comboBox.getItemCount()];

			for (int i = 0; i < comboBox.getItemCount(); i++) {
				animales[i] = comboBox.getItemAt(i).toString();
			}

			listAnimales.setListData(animales);
		});

	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String petName = (String) cb.getSelectedItem();
		// updateLabel(petName);
	}
}
