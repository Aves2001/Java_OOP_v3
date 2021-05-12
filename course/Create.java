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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		comboBoxStops = new JComboBox<String>();
		List<Integer> StopsId = new ArrayList<Integer>();
		for (Stop item : Main.stops) {
			comboBoxStops.addItem(item.toString());
			StopsId.add(item.getId());
		}
		comboBoxStops.setEditable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 311);
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
	

		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = String.valueOf(table.getValueAt(0, 1)) + " - "
						+ table.getValueAt(table.getRowCount() - 1, 1);
				String name2 = String.valueOf(table_1.getValueAt(0, 1)) + " - "
						+ table_1.getValueAt(table_1.getRowCount() - 1, 1);

				Flights flights = new Flights(getIdTable(table), name1, (int[]) getIdTable(table_1), name2);
				System.out.println(flights);

				Main.r.add(new Route(Integer.parseInt(formattedTextField_id.getText()),
						formattedTextField_routeName.getText(), transportType,
						Integer.parseInt(formattedTextField_price.getText()),
						Integer.parseInt(formattedTextField_interval.getText()), formattedTextField_workTime.getText(),
						flights));
			}
		});

		Panel panel_1 = new Panel();
		panel_1.setPreferredSize(new Dimension(300, 200));
		contentPane.add(panel_1, BorderLayout.EAST);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setName("");
		tabbedPane.setPreferredSize(new Dimension(300, 200));
		panel_1.add(tabbedPane);

		Panel panel_2 = new Panel();
		panel_2.setPreferredSize(new Dimension(300, 170));
		panel_2.getLayout();
		tabbedPane.addTab("Прямий", null, panel_2, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 170));
		panel_2.add(scrollPane);

		table = new JTable();
		InitTable(table);
		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});

		scrollPane.setViewportView(table);

		Panel panel_3 = new Panel();
		panel_3.setPreferredSize(new Dimension(300, 170));
		tabbedPane.addTab("Зворотній", null, panel_3, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(300, 170));
		panel_3.add(scrollPane_1);

		table_1 = new JTable();
		InitTable(table_1);
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte index = (byte) tabbedPane.getSelectedIndex();
				System.out.println(index);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { null, null });
			}
		});
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(table.getSelectedRow());
			}
		});
		panel_1.add(btnNewButton_2);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

	private void InitTable(JTable table) {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "ID", "Зупинка" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Object.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		/////////////////////////////////////////////////////////
		table.getColumnModel().getColumn(0).setPreferredWidth(26);
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxStops));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
	}

	private int[] getIdTable(JTable table) {
		int size = table.getRowCount();
		int[] flight = new int[size];
		for (int i = 0; i < size; i++) {
			flight[i] = (int) table.getValueAt(i, 0);
		}
		return flight;
	}
}
