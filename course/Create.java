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
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class Create extends JFrame {
	private static final long serialVersionUID = 1092802948844935117L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private TransportType transportType = null;
	private JComboBox<String> comboBoxStops;
	private JTable table_1 = null;
	private JTable table = null;
	private String old_id;
	private String id = "";
	private String routeName = "";
	private String price = "";
	private String interval = "";
	private String workTime = "";

	private Route route;

	private String[][] str1 = null;
	private String[][] str2 = null;

	public Create() {
		setTitle("Новий маршрут");
		main();
	}

	public Create(Route r) {
		this.route = r;
		setTitle("Редагування маршруту");
		id = String.valueOf(route.getId());
		old_id = id;
		routeName = route.getRouteName();
		transportType = route.getTransportType();
		price = String.valueOf(route.getPrice());
		interval = String.valueOf(route.getInterval());
		workTime = route.getWorkTime();
		Flights flights = route.getFlights();

		int[] tmp1 = flights.getId_flight1();
		int[] tmp2 = flights.getId_flight2();
		str1 = new String[tmp1.length][1];
		str2 = new String[tmp2.length][1];

		Object[] tmp = flights.tmp_add(tmp1, Main.stops);
		for (int i = 0; i < tmp1.length; i++) {
			str1[i][0] = tmp1[i] + ": " + String.valueOf(tmp[i]);
		}
		tmp = flights.tmp_add(tmp2, Main.stops);
		for (int i = 0; i < tmp2.length; i++) {
			str2[i][0] = tmp2[i] + ": " + String.valueOf(tmp[i]);
		}
		main();
	}

	private void main() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 370);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getWidth()) / 2, (screen.height - this.getHeight()) / 2);
		this.setVisible(true);
		comboBoxStops = new JComboBox<String>();
		comboBoxStops.setEditable(false);
		for (Stop item : Main.stops) {
			comboBoxStops.addItem(item.getId() + ": " + item.toString());
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel.getLayout();
		flowLayout_6.setVgap(15);
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel panel_id = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_id.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_id.setBackground(SystemColor.control);
		panel_id.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_id);

		JTextPane txtpn_id = new JTextPane();
		txtpn_id.setEditable(false);
		txtpn_id.setPreferredSize(new Dimension(100, 20));
		txtpn_id.setBackground(SystemColor.control);
		txtpn_id.setText("ID маршруту");
		panel_id.add(txtpn_id);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter format_Int = new NumberFormatter(format);
		format_Int.setValueClass(Integer.class);
		format_Int.setMinimum(0);
		format_Int.setMaximum(Integer.MAX_VALUE);
		format_Int.setAllowsInvalid(false);
		format_Int.setCommitsOnValidEdit(true);

		JFormattedTextField formattedTextField_id = new JFormattedTextField(format_Int);
		formattedTextField_id.setText(id);
		formattedTextField_id.setFocusTraversalPolicyProvider(true);
		formattedTextField_id.setPreferredSize(new Dimension(100, 20));
		panel_id.add(formattedTextField_id);

		JPanel panel_routeName = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_routeName.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_routeName.setBackground(SystemColor.control);
		panel_routeName.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_routeName);

		JTextPane txtpn_routeName = new JTextPane();
		txtpn_routeName.setEditable(false);
		txtpn_routeName.setPreferredSize(new Dimension(100, 20));
		txtpn_routeName.setBackground(SystemColor.control);
		txtpn_routeName.setText("Номер маршруту");
		panel_routeName.add(txtpn_routeName);

		JFormattedTextField formattedTextField_routeName = new JFormattedTextField();
		formattedTextField_routeName.setText(routeName);
		formattedTextField_routeName.setPreferredSize(new Dimension(100, 20));
		panel_routeName.add(formattedTextField_routeName);

		JPanel panel_transportType = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_transportType.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_transportType.setBackground(SystemColor.control);
		panel_transportType.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_transportType);

		JTextPane txtpn_transportType = new JTextPane();
		txtpn_transportType.setEditable(false);
		txtpn_transportType.setPreferredSize(new Dimension(95, 20));
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

		if (transportType != null) {
			if (transportType == TransportType.Trolleybus) {
				buttonGroup.setSelected(rdbtnNewRadioButton.getModel(), true);
			} else {
				buttonGroup.setSelected(rdbtnNewRadioButton_1.getModel(), true);
			}
		}

		JPanel panel_price = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_price.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_price.setBackground(SystemColor.control);
		panel_price.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_price);

		JTextPane txtpn_price = new JTextPane();
		txtpn_price.setEditable(false);
		txtpn_price.setPreferredSize(new Dimension(100, 20));
		txtpn_price.setBackground(SystemColor.control);
		txtpn_price.setText("Вартість проїзду");
		panel_price.add(txtpn_price);

		JFormattedTextField formattedTextField_price = new JFormattedTextField();
		formattedTextField_price.setText(price);
		formattedTextField_price.setPreferredSize(new Dimension(100, 20));
		panel_price.add(formattedTextField_price);

		JPanel panel_interval = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_interval.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_interval.setBackground(SystemColor.control);
		panel_interval.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_interval);

		JTextPane txtpn_interval = new JTextPane();
		txtpn_interval.setEditable(false);
		txtpn_interval.setPreferredSize(new Dimension(100, 20));
		txtpn_interval.setBackground(SystemColor.control);
		txtpn_interval.setText("Інтервал руху");
		panel_interval.add(txtpn_interval);

		JFormattedTextField formattedTextField_interval = new JFormattedTextField();
		formattedTextField_interval.setText(interval);
		formattedTextField_interval.setPreferredSize(new Dimension(100, 20));
		panel_interval.add(formattedTextField_interval);

		JPanel panel_workTime = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_workTime.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_workTime.setBackground(SystemColor.control);
		panel_workTime.setPreferredSize(new Dimension(280, 30));
		panel.add(panel_workTime);

		JTextPane txtpn_workTime = new JTextPane();
		txtpn_workTime.setEditable(false);
		txtpn_workTime.setPreferredSize(new Dimension(100, 20));
		txtpn_workTime.setBackground(SystemColor.control);
		txtpn_workTime.setText("Час роботи");
		panel_workTime.add(txtpn_workTime);

		JFormattedTextField formattedTextField_workTime = new JFormattedTextField();
		formattedTextField_workTime.setText(workTime);
		formattedTextField_workTime.setPreferredSize(new Dimension(100, 20));
		panel_workTime.add(formattedTextField_workTime);

		JButton btnNewButton = new JButton("Зберегти");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (formattedTextField_id.getText().equals("")) {
						new Dialog("Потрібно вказати ID");
						return;
					}
					if (route == null) {
					for (Route r : Main.r) {
						if (String.valueOf(r.getId()).equals(formattedTextField_id.getText())) {
							new Dialog("Маршрут з такм ID уже є:\n" + r.getRouteName() + " " + r.getTransportType());
							return;
						}
					}
					}
					if (formattedTextField_routeName.getText().equals("")) {
						new Dialog("Потрібно вказати номер маршруту");
						return;
					}
					if (transportType == null) {
						new Dialog("Потрібно вказати тип транспорту");
						return;
					}
					if (formattedTextField_price.getText().equals("")) {
						new Dialog("Потрібно вказати вартість маршруту");
						return;
					}
					if (formattedTextField_interval.getText().equals("")) {
						new Dialog("Потрібно вказати інтервал руху");
						return;
					}
					if (formattedTextField_workTime.getText().equals("")) {
						new Dialog("Потрібно вказати час роботи");
						return;
					}

					Flights flights = new Flights(getIdTable(table), getIdTable(table_1));
					if (route == null) {
						add_route(formattedTextField_id, formattedTextField_routeName, formattedTextField_price,
								formattedTextField_interval, formattedTextField_workTime, flights);
					} else {
						update_route(old_id, formattedTextField_routeName, formattedTextField_price,
								formattedTextField_interval, formattedTextField_workTime, flights);
					}
					dispose();
				} catch (Exception e1) {
					new Dialog(e1.getLocalizedMessage());
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
		table.setModel(new DefaultTableModel(str1, new String[] { "Зупинка" }));
		scrollPane.setViewportView(table);

		Panel panel_3 = new Panel();
		panel_3.setPreferredSize(new Dimension(350, 220));
		tabbedPane.addTab("Зворотній", null, panel_3, null);
		panel_3.setBackground(SystemColor.control);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(350, 220));
		panel_3.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(str2, new String[] { "Зупинка" }));

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

		JButton btnNewButton_3 = new JButton("Імпорт ID");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Import(getSelectedModel(tabbedPane));
			}
		});
		panel_1.add(btnNewButton_3);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = tabbedPane.getSelectedIndex();
					DefaultTableModel model = null;
					if (index == 0 && table.getRowCount() > 0) {
						model = (DefaultTableModel) table.getModel();
						if (table.getSelectedRow() == -1) {
							model.removeRow(table.getRowCount() - 1);
							return;
						}
						model.removeRow(table.getSelectedRow());
					} else if (index == 1 && table_1.getRowCount() > 0) {
						model = (DefaultTableModel) table_1.getModel();
						if (table_1.getSelectedRow() == -1) {
							model.removeRow(table_1.getRowCount() - 1);
							return;
						}
						model.removeRow(table_1.getSelectedRow());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_4 = new JButton("Очистити");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (getSelectedModel(tabbedPane).getRowCount() > 0) {
					getSelectedModel(tabbedPane).removeRow(0);
				}
			}
		});
		panel_1.add(btnNewButton_4);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

	private DefaultTableModel getSelectedModel(JTabbedPane tabbedPane) {
		byte index = (byte) tabbedPane.getSelectedIndex();
		if (index == 0) {
			return (DefaultTableModel) table.getModel();
		} else if (index == 1) {
			return (DefaultTableModel) table_1.getModel();
		} else {
			return null;
		}
	}

	private void update_route(String old_id2, JFormattedTextField formattedTextField_routeName,
			JFormattedTextField formattedTextField_price, JFormattedTextField formattedTextField_interval,
			JFormattedTextField formattedTextField_workTime, Flights flights) {

		Main.r.set(Main.r.indexOf(route),
				new Route(Integer.parseInt(old_id2), formattedTextField_routeName.getText(), transportType,
						Integer.parseInt(formattedTextField_price.getText()),
						Integer.parseInt(formattedTextField_interval.getText()), formattedTextField_workTime.getText(),
						flights));
	}

	private void add_route(JFormattedTextField formattedTextField_id, JFormattedTextField formattedTextField_routeName,
			JFormattedTextField formattedTextField_price, JFormattedTextField formattedTextField_interval,
			JFormattedTextField formattedTextField_workTime, Flights flights) {

		Main.r.add(new Route(Integer.parseInt(formattedTextField_id.getText()), formattedTextField_routeName.getText(),
				transportType, Integer.parseInt(formattedTextField_price.getText()),
				Integer.parseInt(formattedTextField_interval.getText()), formattedTextField_workTime.getText(),
				flights));
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
