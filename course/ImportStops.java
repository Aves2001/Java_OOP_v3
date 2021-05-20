package course;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

public class ImportStops extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3577823498058349113L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(DefaultTableModel model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportStops frame = new ImportStops(model);
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
	public ImportStops(DefaultTableModel model) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Імпортувати");
		btnNewButton.addActionListener(new ActionListener() {
		
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				String[] textAr = Stream.of(textArea.getText().split("\n")).filter(s -> s.length() > 0).toArray(String[]::new);
				
				System.out.println(Arrays.toString(textAr));
				for (int i = 0; i < textAr.length; i++) {
					String[] s = textAr[i].replace("[", "").split("] ");
					try {
						Vector<Comparable> row = new Vector<Comparable>();
						row.add(Integer.parseInt(s[0].trim()));
						row.add(s[1].trim());
						model.addRow(row);
					} catch (Exception e2) {
						e2.printStackTrace();
						System.out.println("ImportStops 70");
					}
				}
				dispose();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setEditable(false);
		textPane.setText("Приклад: [25356] Держуніверситет");
		contentPane.add(textPane, BorderLayout.NORTH);
	}

}
