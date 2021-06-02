package lab5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.MenuEvent;
import javax.swing.ListSelectionModel;

public class Main extends JFrame {
	private static final long serialVersionUID = 7428068460143904609L;
	private JPanel contentPane;
	private JTextField textField_nameToy;
	private JTextField textField_parameter;
	private JSpinner spinner_AgeLimitsTo;

	private JMenu menu_file = new JMenu("Файл");
	private JMenuItem menuItem_open = new JMenuItem("Відкрити");
	private JMenuItem menuItem_save = new JMenuItem("Зберегти");
	private JMenu menu_edit = new JMenu("Правка");
	private JMenuItem menuItem_update = new JMenuItem("Обновити");
	private JCheckBoxMenuItem checkItem_edit = new JCheckBoxMenuItem("Редагувати");
	private JCheckBoxMenuItem checkItem_add = new JCheckBoxMenuItem("Додати");
	private JCheckBoxMenuItem checkItem_sort = new JCheckBoxMenuItem("Сортувати");
	private Label label_type = new Label("Тип");
	private Label label_name = new Label("Назва");
	private Label label_price = new Label("Вартість");
	private Label label_ageLimits = new Label("Вікові межі");
	private Label label_ageLimits_from = new Label("Від");
	private Label label_ageLimits_to = new Label("До");
	private Label label_parameter = new Label("Параметр");
	private JButton button_add = new JButton("Додати");
	private JButton button_update = new JButton("Обновити");
	private JButton btnNewButton = new JButton("Очистити");
	private JPanel panel_tableAddUpdate = new JPanel();
	private JPanel panel_tableEdit = new JPanel();
	private JButton btn_plus = new JButton("+");
	private JButton btn_minus = new JButton("-");
	private JButton btn_clear = new JButton("Очистити");

	private List<Toy> toys = new ArrayList<Toy>();
	private ToysTableModel model;
	private ListSelectionModel selModel;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public Main() throws Exception {
		toys = Toy.input();
		if (toys.size() > 0) {
			checkItem_add.setSelected(false);
			panel_tableAddUpdate.setVisible(false);
		} else {
			panel_tableAddUpdate.setVisible(true);
			checkItem_add.setSelected(true);
		}
		model = new ToysTableModel(toys);
		table = new JTable(model);
		selModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.setVisible(true);
		setMinimumSize(new Dimension(720, 400));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setTitle("Лабораторна робота №5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 400);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);
		menu_file.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				if (checkItem_add.isSelected()) {
					label_type.setVisible(true);
				}
			}

			public void menuSelected(MenuEvent e) {
				if (checkItem_add.isSelected()) {
					label_type.setVisible(false);
				}
			}
		});

		menuBar.add(menu_file);

		menuItem_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser(new File(".").getAbsolutePath());
				fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileopen.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));

				int ret = fileopen.showDialog(null, "Відкрити файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					try {
						if (Toy.input(fileopen.getSelectedFile()).size() != 0) {
							toys = Toy.input(fileopen.getSelectedFile());
							model.update(toys);
							new Dialog("Дані завантажені");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		menuItem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuItem_open.setSelectedIcon(null);
		menu_file.add(menuItem_open);

		menuItem_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Toy.out(toys);
				} catch (Exception e1) {
					new Dialog("Не збережено\n" + e1.getMessage());
					e1.printStackTrace();
					return;
				}
				new Dialog("Збережено");
			}
		});
		menuItem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_save.setSelectedIcon(null);
		menu_file.add(menuItem_save);
		menu_edit.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				if (checkItem_add.isSelected()) {
					label_type.setVisible(true);
				}
			}

			public void menuSelected(MenuEvent e) {
				if (checkItem_add.isSelected()) {
					label_type.setVisible(false);
				}
			}
		});
		menuBar.add(menu_edit);
		checkItem_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkItem_add.isSelected()) {
					panel_tableAddUpdate.setVisible(true);
				} else {
					panel_tableAddUpdate.setVisible(false);
				}
			}
		});
		menuItem_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Toy.input().size() != 0) {
						toys = Toy.input();
						model.update(toys);
						new Dialog("Дані завантажені");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					new Dialog(e1.getMessage());
				}
			}
		});
		menuItem_update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

		menu_edit.add(menuItem_update);
		checkItem_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkItem_edit.isSelected()) {
					panel_tableEdit.setVisible(true);
					button_update.setVisible(true);
				} else {
					panel_tableEdit.setVisible(false);
					button_update.setVisible(false);
				}
			}
		});
		checkItem_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

		menu_edit.add(checkItem_edit);

		checkItem_add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menu_edit.add(checkItem_add);
		checkItem_sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkItem_sort.isSelected() && toys.size() > 0) {
					int max = toys.stream().mapToInt(s -> s.getPrice()).max().getAsInt() - 1000;
					table.setModel(new ToysTableModel(toys.stream().filter(s -> s.getPrice() > max)
							.sorted((s2, s1) -> s1.getPrice() - s2.getPrice()).collect(Collectors.toList())));
				} else {
					table.setModel(new ToysTableModel(toys));
				}
			}
		});

		checkItem_sort.setVisible(true);
		checkItem_sort.setFocusable(true);
		checkItem_sort.setFocusPainted(true);

		checkItem_sort.setToolTipText(
				"Перелік найбільш коштовних іграшок (ціна яких відрізняється від ціни найкоштовнішої іграшки не більш ніж на 10 грн.) у порядку спадання ціни.");
		checkItem_sort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		menu_edit.add(checkItem_sort);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_tableAddUpdate.setPreferredSize(new Dimension(10, 102));
		contentPane.add(panel_tableAddUpdate, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(120, 60));
		panel_tableAddUpdate.add(panel_1);

		label_type.setPreferredSize(new Dimension(40, 20));
		label_type.setAlignment(Label.CENTER);
		label_type.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_1.add(label_type);

		JComboBox<TypeToys> comboBox_typeToy = new JComboBox<TypeToys>();
		comboBox_typeToy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp = "";
				try {
					tmp = ((TypeToys) comboBox_typeToy.getSelectedItem()).getParametr();
					textField_parameter.setToolTipText(tmp);
					label_parameter.setText(tmp.split(" ")[0]);
				} catch (Exception e2) {
					label_parameter.setText(tmp);
				}
			}
		});

		comboBox_typeToy.setPreferredSize(new Dimension(115, 22));
		comboBox_typeToy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_typeToy.setModel(new DefaultComboBoxModel<TypeToys>(TypeToys.values()));
		panel_1.add(comboBox_typeToy);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(120, 60));
		panel_tableAddUpdate.add(panel_2);

		label_name.setPreferredSize(new Dimension(50, 20));
		label_name.setAlignment(Label.CENTER);
		label_name.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_2.add(label_name);

		textField_nameToy = new JTextField();
		textField_nameToy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_2.add(textField_nameToy);
		textField_nameToy.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(100, 60));
		panel_tableAddUpdate.add(panel_3);

		label_price.setPreferredSize(new Dimension(95, 20));
		label_price.setAlignment(Label.CENTER);
		label_price.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_3.add(label_price);

		JSpinner spinner_price = new JSpinner();
		spinner_price.setToolTipText("Вартість у копійках");
		spinner_price.setPreferredSize(new Dimension(70, 20));
		spinner_price.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner_price.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(spinner_price);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(150, 60));
		panel_tableAddUpdate.add(panel_4);

		label_ageLimits.setPreferredSize(new Dimension(130, 20));
		label_ageLimits.setAlignment(Label.CENTER);
		label_ageLimits.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_4.add(label_ageLimits);

		label_ageLimits_from.setPreferredSize(new Dimension(20, 20));
		panel_4.add(label_ageLimits_from);

		JSpinner spinner_AgeLimitsFrom = new JSpinner();
		spinner_AgeLimitsFrom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int tmp = Integer.parseInt(spinner_AgeLimitsFrom.getValue().toString());
				spinner_AgeLimitsTo.setModel(new SpinnerNumberModel(tmp, tmp, null, 1));
			}
		});

		spinner_AgeLimitsFrom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinner_AgeLimitsFrom.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner_AgeLimitsFrom.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinner_AgeLimitsFrom);

		label_ageLimits_to.setPreferredSize(new Dimension(20, 20));
		panel_4.add(label_ageLimits_to);

		spinner_AgeLimitsTo = new JSpinner();
		spinner_AgeLimitsTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinner_AgeLimitsTo.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner_AgeLimitsTo.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinner_AgeLimitsTo);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(120, 60));
		panel_tableAddUpdate.add(panel_5);

		label_parameter.setPreferredSize(new Dimension(95, 20));
		label_parameter.setAlignment(Label.CENTER);
		label_parameter.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_5.add(label_parameter);

		textField_parameter = new JTextField();
		textField_parameter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_5.add(textField_parameter);
		textField_parameter.setColumns(10);

		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgeLimits al = new AgeLimits(spinner_AgeLimitsFrom.getValue(), spinner_AgeLimitsTo.getValue());
				model.add(new Toy((TypeToys) comboBox_typeToy.getSelectedItem(), textField_nameToy.getText(),
						Integer.valueOf(spinner_price.getValue().toString()), al, textField_parameter.getText()));
			}
		});
		panel_tableAddUpdate.add(button_add);
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgeLimits al = new AgeLimits(spinner_AgeLimitsFrom.getValue(), spinner_AgeLimitsTo.getValue());
				model.editRow(selModel.getAnchorSelectionIndex(),
						new Toy((TypeToys) comboBox_typeToy.getSelectedItem(), textField_nameToy.getText(),
								Integer.valueOf(spinner_price.getValue().toString()), al,
								textField_parameter.getText()));
			}
		});
		button_update.setVisible(false);

		panel_tableAddUpdate.add(button_update);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_typeToy.setSelectedItem(TypeToys.NULL);
				textField_nameToy.setText("");
				spinner_price.setValue(0);
				spinner_AgeLimitsFrom.setValue(0);
				spinner_AgeLimitsTo.setValue(0);
				textField_parameter.setText("");
			}
		});

		panel_tableAddUpdate.add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(700, 10));
		panel_tableAddUpdate.add(separator);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		panel_tableEdit.setVisible(false);
		contentPane.add(panel_tableEdit, BorderLayout.SOUTH);
		btn_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.add(new Toy());
			}
		});
		panel_tableEdit.add(btn_plus);
		btn_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(table.getSelectedRow());
			}
		});
		panel_tableEdit.add(btn_minus);
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
			}
		});
		panel_tableEdit.add(btn_clear);

		selModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					if (checkItem_edit.isSelected() && model.getRowCount() > 0) {

						String tmp = model.getValueAt(selModel.getAnchorSelectionIndex(), 0).toString();
						for (TypeToys a : TypeToys.values()) {
							if (a.toString().equals(tmp)) {
								comboBox_typeToy.setSelectedItem(a);
								break;
							}
							comboBox_typeToy.setSelectedItem(TypeToys.NULL);
						}
						textField_nameToy.setText(model.getValueAt(selModel.getAnchorSelectionIndex(), 1).toString());
						spinner_price.setValue(
								Integer.parseInt(model.getValueAt(selModel.getAnchorSelectionIndex(), 2).toString()));
						spinner_AgeLimitsFrom.setValue(
								Integer.parseInt(model.getValueAt(selModel.getAnchorSelectionIndex(), 3).toString()));
						spinner_AgeLimitsTo.setValue(
								Integer.parseInt(model.getValueAt(selModel.getAnchorSelectionIndex(), 4).toString()));
						textField_parameter.setText(model.getValueAt(selModel.getAnchorSelectionIndex(), 5).toString());
					}
				} catch (Exception e2) {
				}
			}
		});
	}

	public void clearTable() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
