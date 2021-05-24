package course;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.Panel;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Main extends JFrame {
	public static List<Route> r = new ArrayList<>();
	public static List<Stop> stops = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TransportType transportType;
	private JTextPane textPane_2 = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane();

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Init();
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void Init() throws IOException, ClassNotFoundException {
		stops = Stop.input();
		r = Route.input();
		if (r == null || r.size() == 0) {
			new Dialog("Маршрути не знайдені. Для роботи з маршрутами спочатку дадайте їх.");
		}
		if (stops == null || stops.size() == 0) {
			new Dialog("Зупинки не знайдені.");
		}
	}

	@SuppressWarnings("deprecation")
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 381);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getWidth()) / 2, (screen.height - this.getHeight()) / 2);

		setTitle("Відомості про маршрути громадського транспорту");
		JComboBox<Object> comboBox = new JComboBox<Object>();
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInfo(comboBox, comboBox_1, textPane_2);
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				setSelectedClear(comboBox, comboBox_1, textPane_2);
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1) {
					Flights tmp = null;
					tmp = ((Route) r.stream()
							.filter(s -> s.getTransportType() == transportType
									&& s.getId() == ((Route) comboBox.getSelectedItem()).getId())
							.toArray()[0]).getFlights();
					comboBox_1.removeAllItems();
					String[] tmp2 = new String[2];
					if (tmp != null) {
						if (tmp.getFlightName1() != "") {
							tmp2[0] = tmp.getFlightName1();
						}
						if (tmp.getFlightName2() != "") {
							tmp2[1] = tmp.getFlightName2();
						}
					}
					comboBox_1.setModel(new DefaultComboBoxModel<>(tmp2));
					showInfo(comboBox, comboBox_1, textPane_2);
				}
			}
		});
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Тролейбус");
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Автобус");
//////////////////////////////////////////////////////////////////////////////////
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Файл");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Зберегти");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Route.out(r);
					Stop.out(stops);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Відкрити");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////////////////////////////////////////////////////////////////////////////////
				JFileChooser fileopen = new JFileChooser(new File(".").getAbsolutePath());
				fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int ret = fileopen.showDialog(null, "Відкрити файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					setSelectedClear(comboBox, comboBox_1, textPane_2);
					if (Stop.input(fileopen.getSelectedFile()) != null) {
						stops = Stop.input(fileopen.getSelectedFile());
						new Dialog("Зупинки завантажені");
						return;
					}
					if (Route.input(fileopen.getSelectedFile()) == null) {
						new Dialog("Такий файл не підтримується");
					} else {
						r = Route.input(fileopen.getSelectedFile());
						new Dialog("Маршрути завантажені");
					}
				}
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Маршрут");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Створити новий");
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////////////////////////////////
				new Create();
				setSelectedClear(comboBox, comboBox_1, textPane_2);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Видалити поточний");
		mntmNewMenuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1) {
					r.remove(comboBox.getSelectedItem());
					table.setVisible(false);
					setSelectedClear(comboBox, comboBox_1, textPane_2);
				} else {
					new Dialog("Для видалення маршруту, спочатку виберіть його.");
				}
			}
		});

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Редагувати маршрут");
		mntmNewMenuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Create((Route) comboBox.getSelectedItem());
					setSelectedClear(comboBox, comboBox_1, textPane_2);
				} catch (Exception e2) {
					new Create();
					setSelectedClear(comboBox, comboBox_1, textPane_2);
					new Dialog("Ви не вибрали маршрут який потрібно відредагувати");
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Редагування зупинок");
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditStops();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.getLayout();
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setEditable(false);
		textPane.setText("Виберіть тип транспорту");
		panel_1.add(textPane);

		rdbtnNewRadioButton.setSelected(false);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					transportType = TransportType.Trolleybus;
					comboBox.setModel(new DefaultComboBoxModel<>(
							r.stream().filter(s -> s.getTransportType() == transportType).toArray()));
					if (comboBox.getItemCount() > 0) {
						comboBox.setSelectedIndex(0);
						Enabled(comboBox, comboBox_1);
						showInfo(comboBox, comboBox_1, textPane_2);
					} else {
						setSelectedClear(comboBox, comboBox_1, textPane_2);
						new Dialog("Немає маршрутів тролейбусів");
					}
				}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		panel_1.add(rdbtnNewRadioButton);
		/////////////////////////////////////////////////////////////////////
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton_1.isSelected()) {
					transportType = TransportType.bus;
					comboBox.setModel(new DefaultComboBoxModel<>(
							r.stream().filter(s -> s.getTransportType() == transportType).toArray()));
					if (comboBox.getItemCount() > 0) {
						comboBox.setSelectedIndex(0);
						Enabled(comboBox, comboBox_1);
						showInfo(comboBox, comboBox_1, textPane_2);
					} else {
						setSelectedClear(comboBox, comboBox_1, textPane_2);
						new Dialog("Немає маршрутів автобусів");
					}
				}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel_1.add(rdbtnNewRadioButton_1);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(780, 50));
		panel_4.getLayout();
		panel.add(panel_4);

		Panel panel_6 = new Panel();
		panel_4.add(panel_6);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Номер маршруту");
		textPane_3.setEditable(false);
		textPane_3.setBackground(SystemColor.menu);
		panel_6.add(textPane_3);

		///////////// JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setEnabled(false);
		panel_6.add(comboBox);
		comboBox_1.setEnabled(false);

		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(SystemColor.control);
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane_1.setEditable(false);
		textPane_1.setText("Рейси");
		panel_2.add(textPane_1);
		panel_2.add(comboBox_1);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(300, 200));
		panel_5.getLayout();
		panel.add(panel_5);
		////////////////////////////////////////////////////////////////////
		scrollPane.setPreferredSize(new Dimension(300, 200));
		panel_5.add(scrollPane);
		scrollPane.setVisible(false);
		scrollPane.setEnabled(false);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setPreferredSize(new Dimension(400, 195));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		/////////////////////////////////////////////////////////////////////////////////// textPane_2
		textPane_2.setPreferredSize(new Dimension(400, 200));
		panel.add(textPane_2);
		textPane_2.setBackground(SystemColor.control);
		textPane_2.setEditable(false);
	}

	private void Enabled(JComboBox<Object> comboBox, JComboBox<Object> comboBox_1) {
		comboBox.setEnabled(true);
		comboBox_1.setEnabled(true);
		comboBox_1.setVisible(true);
		textPane_2.setVisible(true);
		table.setEnabled(true);
		table.setVisible(true);
		scrollPane.setVisible(true);
	}

	private void Disable(JComboBox<Object> comboBox, JComboBox<Object> comboBox_1) {
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
		textPane_2.setVisible(false);
		table.setEnabled(false);
		table.setVisible(false);
		table.setModel(new DefaultTableModel(null, new String[] { "Дані відсутні" }));
		scrollPane.setVisible(false);
	}

	private void setSelectedClear(JComboBox<Object> comboBox, JComboBox<Object> comboBox_1, JTextPane textPane_2) {
		comboBox.setSelectedIndex(-1);
		comboBox_1.setSelectedIndex(-1);
		Disable(comboBox, comboBox_1);
		buttonGroup.clearSelection();
	}

	private void showInfo(JComboBox<Object> comboBox, JComboBox<Object> comboBox_1, JTextPane textPane_2) {
		if (comboBox_1.getSelectedIndex() != -1) {
			Route tmp = (Route) r.stream().filter(s -> s.getId() == ((Route) comboBox.getSelectedItem()).getId())
					.toArray()[0];

			Object[] tmp2 = tmp.getFlights().getFlight(comboBox_1.getSelectedIndex() + 1, stops);

			Object[][] tabl = null;
			if (tmp2 != null) {

				tabl = new Object[tmp2.length][1];
				for (int i = 0; i < tmp2.length; i++) {
					tabl[i][0] = tmp2[i];
				}
			}
			table.setModel(new DefaultTableModel(tabl, new String[] { "Зупинки" }));
			textPane_2
					.setText((String) r.stream().filter(s -> s.getId() == ((Route) comboBox.getSelectedItem()).getId())
							.map(s -> s.getInfo()).toArray()[0]);
		}
	}
}
