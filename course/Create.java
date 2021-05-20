package course;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Create extends JFrame {

	private static final long serialVersionUID = 1092802948844935117L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	TransportType transportType = null;
	JComboBox<String> comboBoxStops;
	private JTable table_1 = null;
	private JTable table = null;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create frame = new Create();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Create() {
		setTitle("Новий маршрут");
		comboBoxStops = new JComboBox<String>();
		comboBoxStops.setEditable(false);
		List<Integer> StopsId = new ArrayList<Integer>();
		for (Stop item : Main.stops) {
			comboBoxStops.addItem(item.getId() + ": " + item.toString());
			StopsId.add(item.getId());
		}
		comboBoxStops.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		        validate();
			}
			@Override
			public void focusGained(FocusEvent e) {
			        validate();
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel panel_id = new JPanel();
		panel_id.setBackground(SystemColor.control);
		panel_id.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_id);

		JTextPane txtpn_id = new JTextPane();
		txtpn_id.setEditable(false);
		txtpn_id.setPreferredSize(new Dimension(100, 20));
		txtpn_id.setBackground(SystemColor.control);
		txtpn_id.setText("id");
		panel_id.add(txtpn_id);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter format_Int = new NumberFormatter(format);
		format_Int.setValueClass(Integer.class);
		format_Int.setMinimum(0);
		format_Int.setMaximum(Integer.MAX_VALUE);
		format_Int.setAllowsInvalid(false);
		format_Int.setCommitsOnValidEdit(true);

		JFormattedTextField formattedTextField_id = new JFormattedTextField(format_Int);
		formattedTextField_id.setFocusTraversalPolicyProvider(true);
		formattedTextField_id.setPreferredSize(new Dimension(100, 20));

		panel_id.add(formattedTextField_id);

		JPanel panel_routeName = new JPanel();
		panel_routeName.setBackground(SystemColor.control);
		panel_routeName.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_routeName);

		JTextPane txtpn_routeName = new JTextPane();
		txtpn_routeName.setEditable(false);
		txtpn_routeName.setPreferredSize(new Dimension(100, 20));
		txtpn_routeName.setBackground(SystemColor.control);
		txtpn_routeName.setText("Номер маршруту");
		panel_routeName.add(txtpn_routeName);

		JFormattedTextField formattedTextField_routeName = new JFormattedTextField();
		formattedTextField_routeName.setPreferredSize(new Dimension(100, 20));
		panel_routeName.add(formattedTextField_routeName);

		JPanel panel_transportType = new JPanel();
		panel_transportType.setBackground(SystemColor.control);
		panel_transportType.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_transportType);

		JTextPane txtpn_transportType = new JTextPane();
		txtpn_transportType.setEditable(false);
		txtpn_transportType.setPreferredSize(new Dimension(100, 20));
		txtpn_transportType.setBackground(SystemColor.control);
		txtpn_transportType.setText("Тип транспорту");
		panel_transportType.add(txtpn_transportType);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Тролейбус");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transportType = TransportType.Trolleybus;
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		panel_transportType.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Автобус");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transportType = TransportType.bus;
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel_transportType.add(rdbtnNewRadioButton_1);

		JPanel panel_price = new JPanel();
		panel_price.setBackground(SystemColor.control);
		panel_price.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_price);

		JTextPane txtpn_price = new JTextPane();
		txtpn_price.setEditable(false);
		txtpn_price.setPreferredSize(new Dimension(100, 20));
		txtpn_price.setBackground(SystemColor.control);
		txtpn_price.setText("Ціна");
		panel_price.add(txtpn_price);

		JFormattedTextField formattedTextField_price = new JFormattedTextField();
		formattedTextField_price.setPreferredSize(new Dimension(100, 20));
		panel_price.add(formattedTextField_price);

		JPanel panel_interval = new JPanel();
		panel_interval.setBackground(SystemColor.control);
		panel_interval.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_interval);

		JTextPane txtpn_interval = new JTextPane();
		txtpn_interval.setEditable(false);
		txtpn_interval.setPreferredSize(new Dimension(100, 20));
		txtpn_interval.setBackground(SystemColor.control);
		txtpn_interval.setText("Інтервал руху");
		panel_interval.add(txtpn_interval);

		JFormattedTextField formattedTextField_interval = new JFormattedTextField();
		formattedTextField_interval.setPreferredSize(new Dimension(100, 20));
		panel_interval.add(formattedTextField_interval);

		JPanel panel_workTime = new JPanel();
		panel_workTime.setBackground(SystemColor.control);
		panel_workTime.setPreferredSize(new Dimension(300, 30));
		panel.add(panel_workTime);

		JTextPane txtpn_workTime = new JTextPane();
		txtpn_workTime.setEditable(false);
		txtpn_workTime.setPreferredSize(new Dimension(100, 20));
		txtpn_workTime.setBackground(SystemColor.control);
		txtpn_workTime.setText("Час роботи");
		panel_workTime.add(txtpn_workTime);

		JFormattedTextField formattedTextField_workTime = new JFormattedTextField();
		formattedTextField_workTime.setPreferredSize(new Dimension(100, 20));
		panel_workTime.add(formattedTextField_workTime);

		JButton btnNewButton = new JButton("Зберегти");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("202 перевірка");
			}
		});

		btnNewButton.setEnabled(true); ////////////////////////
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

//					int id_1 = Integer.parseInt(table.getValueAt(0, 0).toString().split(":")[0]);
//					int id_2 = Integer.parseInt(table.getValueAt(table.getRowCount() - 1, 0).toString().split(":")[0]);

//					String name1 = Main.stops.stream().filter(s -> s.getId() == id_1).map(s -> s.getTitle()).toArray()[0] + " - " + Main.stops.stream().filter(s -> s.getId() == id_2).map(s -> s.getTitle()).toArray()[0];

//					int id_3 = Integer.parseInt(table_1.getValueAt(0, 0).toString().split(":")[0]);
//					int id_4 = Integer.parseInt(table_1.getValueAt(table_1.getRowCount() - 1, 0).toString().split(":")[0]);

//					String name2 = Main.stops.stream().filter(s -> s.getId() == id_3).map(s -> s.getTitle()).toArray()[0] + " - " + Main.stops.stream().filter(s -> s.getId() == id_4).map(s -> s.getTitle()).toArray()[0];

					Flights flights = new Flights(getIdTable(table), getIdTable(table_1));

					Main.r.add(new Route(Integer.parseInt(formattedTextField_id.getText()),
							formattedTextField_routeName.getText(), transportType,
							Integer.parseInt(formattedTextField_price.getText()),
							Integer.parseInt(formattedTextField_interval.getText()),
							formattedTextField_workTime.getText(), flights));
					dispose();
				} catch (NullPointerException e1) {
					Dialog.main("Потрібно вказати зупинки");
				}
			}
		});

		Panel panel_1 = new Panel();
		panel_1.setPreferredSize(new Dimension(350, 160));
		contentPane.add(panel_1, BorderLayout.EAST);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFocusCycleRoot(true);
		tabbedPane.setFocusTraversalPolicyProvider(true);
		tabbedPane.setToolTipText("");
		tabbedPane.setName("");
		panel_1.add(tabbedPane);

		Panel panel_2 = new Panel();
		panel_2.setPreferredSize(new Dimension(350, 220));
		panel_2.setBackground(SystemColor.control);
		tabbedPane.addTab("Прямий", null, panel_2, null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(350, 220));
		panel_2.add(scrollPane);

		table = new JTable();
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("asdasd");
				
			}
		});
		InitTable(table);
		scrollPane.setViewportView(table);

		Panel panel_3 = new Panel();
		panel_3.setPreferredSize(new Dimension(350, 220));
		tabbedPane.addTab("Зворотній", null, panel_3, null);
		panel_3.setBackground(SystemColor.control);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(350, 220));
		panel_3.add(scrollPane_1);

		table_1 = new JTable();
		InitTable(table_1);
		scrollPane_1.setViewportView(table_1);

		DefaultCellEditor editor = new DefaultCellEditor(comboBoxStops);
		table.getColumnModel().getColumn(0).setCellEditor(editor);
		table_1.getColumnModel().getColumn(0).setCellEditor(editor);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte index = (byte) tabbedPane.getSelectedIndex();
				DefaultTableModel model;
				if (index == 0) {
					model = (DefaultTableModel) table.getModel();
				} else {
					model = (DefaultTableModel) table_1.getModel();
				}
				model.addRow(new Object[] { null });
			}
		});
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					byte index = (byte) tabbedPane.getSelectedIndex();
					DefaultTableModel model = null;
					if (index == 0 && table.getRowCount() > 1) {
						model = (DefaultTableModel) table.getModel();
						model.removeRow(table.getSelectedRow());
					} else if (table_1.getRowCount() > 1) {
						model = (DefaultTableModel) table_1.getModel();
						model.removeRow(table_1.getSelectedRow());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_1.add(btnNewButton_2);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

	private void InitTable(JTable table) {
		table.setModel(new DefaultTableModel(null,  new String[] { "Зупинка" }));
	}

	private int[] getIdTable(JTable table) {
		int size = table.getRowCount();
		int[] flight = new int[size];
		for (int i = 0; i < size; i++) {
			flight[i] = Integer.parseInt(table.getValueAt(i, 0).toString().split(":")[0]);
		}
		return flight;
	}
}
