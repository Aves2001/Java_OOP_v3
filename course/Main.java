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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.Panel;
import java.awt.Cursor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static List<Route> r = new ArrayList<>();
	public static List<Stop> stops = new ArrayList<>();
	TransportType transportType;
	JTextPane textPane_2 = new JTextPane();
	JScrollPane scrollPane = new JScrollPane();

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
					System.out.println(e.getMessage() + "\n\n");
					e.printStackTrace();
				}
			}
		});
	}

	private static void Init() throws IOException {
//		stops = new ArrayList<>();
//		stops.add(new Stop(25356, "Держуніверситет"));
//		stops.add(new Stop(113, "вул. Богдана Хмельницького"));
//		stops.add(new Stop(110, "вул. Степана Бандери"));
//		stops.add(new Stop(107, "пл. Соборна"));
//		stops.add(new Stop(104, "Центральний ринок"));
//		stops.add(new Stop(269, "вул. Сторожинецька"));
//		stops.add(new Stop(101, "вул. Олександра Щербанюка"));
//		stops.add(new Stop(98, "Універсам \"Колос\""));
//		stops.add(new Stop(143, "Тролейбусне депо"));
//		stops.add(new Stop(142, "Мікрорайон"));
//		stops.add(new Stop(140, "Формаркет"));
//		stops.add(new Stop(139, "магазин \"Спорттовари\""));
//		stops.add(new Stop(135, "вул. Ясська"));
//		stops.add(new Stop(136, "вул. Ясська"));
//		stops.add(new Stop(137, "пр. Незалежності"));
//		stops.add(new Stop(138, "магазин \"Спорттовари\""));
//		stops.add(new Stop(170, "вул. Небесної сотні"));
//		stops.add(new Stop(141, "Мікрорайон"));
//		stops.add(new Stop(99, "Універсам \"Колос\""));
//		stops.add(new Stop(100, "вул. Олександра Щербанюка"));
//		stops.add(new Stop(103, "Медучилище"));
//		stops.add(new Stop(106, "пл. Соборна"));
//		stops.add(new Stop(109, "Кінотеатр \"Чернівці\""));
//		stops.add(new Stop(25354, "вул. Богдана Хмельницького"));
//		int[] Flights1_1 = {25356, 113, 110, 107, 104, 269, 101, 98, 143, 142, 140, 139, 135};
//		int[] Flights1_2 = {136, 137, 138, 170, 141, 99, 100, 103, 106, 109, 25354, 25356};
//		int[] Flights2_1 = {138,141};

//		r = new ArrayList<Route>();
//		r.add(new Route(1, "1", TransportType.Trolleybus, 5, 10, "06:00 - 23:00", new Flights(Flights1_1, "вул. Садова - вул. Вижницька", Flights1_2, "вул. Вижницька - вул. Садова ")));
//		r.add(new Route(2, "2", TransportType.bus, 7, 10, "06:00 - 23:00", new Flights(Flights2_1, "вул.")));
		stops = Stop.input();
		r = Route.input();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public Main() {
		JComboBox<Object> comboBox = new JComboBox<Object>();
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Flights tmp = null;
				if (comboBox.getSelectedIndex() != -1) {
					tmp = (Flights) ((Route) r.stream()
							.filter(s -> s.getId() == ((Route) comboBox.getSelectedItem()).getId()).toArray()[0])
									.getFlights();
					comboBox_1.removeAllItems();

					ArrayList<String> tmp2 = new ArrayList<>();
					if (tmp != null) {

						if (tmp.getFlightName1() != "") {
							tmp2.add(tmp.getFlightName1());
						}
						if (tmp.getFlightName2() != "") {
							tmp2.add(tmp.getFlightName2());
						}
					}
					comboBox_1.setModel(new DefaultComboBoxModel<>(tmp2.toArray()));
				}
			}
		});

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInfo(comboBox, comboBox_1, textPane_2);
			}
		});

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Тролейбус");
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Автобус");
//////////////////////////////////////////////////////////////////////////////////
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 381);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Файл");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Зберегти");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Route.out(r);
				} catch (IOException e1) {
					// TODO Автоматически созданный блок catch
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

				int ret = fileopen.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					r = Route.input(fileopen.getSelectedFile());
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
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////////////////////////////////
				Create.main();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Видалити поточний");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.remove(comboBox.getSelectedItem());
				table.setVisible(false);
				setSelectedClear(comboBox, comboBox_1, textPane_2);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Редагування зупинок");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStops.main();
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
					}
				}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel_1.add(rdbtnNewRadioButton_1);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(500, 50));
		panel_4.getLayout();
		panel.add(panel_4);

		Panel panel_6 = new Panel();
		panel_4.add(panel_6);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Номер маршруту");
		textPane_3.setEditable(false);
		textPane_3.setBackground(SystemColor.menu);
		panel_6.add(textPane_3);

		///////////////////////////////////// JComboBox<Object> comboBox = new
		///////////////////////////////////// JComboBox<Object>();
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
		ListSelectionModel selModel = table.getSelectionModel();
		selModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Автоматически созданная заглушка метода
				System.out.println("1");
			}
		});
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setPreferredSize(new Dimension(400, 400));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		
		/////////////////////////////////////////////////////////////////////////////////// textPane_2

		textPane_2.setPreferredSize(new Dimension(200, 200));
		panel.add(textPane_2);

		textPane_2.setBackground(SystemColor.control);

		textPane_2.setEditable(false);
	}

	private void Enabled(JComboBox<Object> comboBox, JComboBox<Object> comboBox_1) {
		comboBox.setEnabled(true);
		comboBox_1.setEnabled(true);
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
