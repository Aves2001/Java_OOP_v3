package course;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditStops extends JFrame {
	private static final long serialVersionUID = -5580386848933709904L;
	private JPanel contentPane;
	public static JTable table;

	public EditStops() {
		this.setVisible(true);
		setTitle("Редагування зупинок");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		Object[][] tmp = Main.stops.stream().map(s -> s.toArray()).toArray(Object[][]::new);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(tmp, new String[] { "ID", "Назва зупинки" }) {

			private static final long serialVersionUID = -3578241024259492519L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Object.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(0).setMinWidth(45);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Зберегти");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.stops.clear();
				Set<Integer> setStop = new HashSet<>();
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0) != null && table.getValueAt(i, 1) != null && setStop.add((int) table.getValueAt(i, 0))) {
						Main.stops.add(new Stop((int) table.getValueAt(i, 0), table.getValueAt(i, 1).toString()));
					}
				}
				setStop = null;
				try {
					Stop.out(Main.stops);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		JButton btnNewButton_3 = new JButton("Імпорт");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Import(model);
			}
		});
		panel.add(btnNewButton_3);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[] { null, null });
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					return;
				}
				int numRows = table.getSelectedRows().length;
				for (int i = 0; i < numRows; i++) {
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		panel.add(btnNewButton_2);
	}
}
