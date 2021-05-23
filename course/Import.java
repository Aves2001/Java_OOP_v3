package course;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

public class Import extends JFrame {
	private static final long serialVersionUID = 3577823498058349113L;
	private JPanel contentPane;

	private String getModelClass(DefaultTableModel model) {
		return model.getClass().getNestHost().getSimpleName();
	}

	private void SetText(JTextComponent textPane, DefaultTableModel model) {
		if (getModelClass(model).equals("EditStops")) {
			setTitle("Імпорт зупинок");
			textPane.setText("Приклад: [25356] Держуніверситет");
		} else {
			setTitle("Імпорт ID зупинок");
			textPane.setText("Приклад: [25356] Держуніверситет, або [25356]");
		}
	}

	public Import(DefaultTableModel model) {
		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setEditable(false);
		SetText(textPane, model);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				List<String> errorString = new ArrayList<>();
				String[] s = new String[2];
				for (int i = 0; i < textAr.length; i++) {
					try {
						s = textAr[i].replace("[", "").split("]");
						Vector<Comparable> row = new Vector<Comparable>();
						row.add(Integer.parseInt(s[0].trim()));
						if (s.length > 1) {							
							row.add(s[1].trim());
						}
						model.addRow(row);
					} catch (Exception e2) {
						e2.printStackTrace();
						errorString.add("\n" + Arrays.toString(s));
					}
				}
				if (errorString.size() > 0) {
					new Dialog("Увага наступні дані не вдалось імпортувати:" + Arrays.toString(errorString.toArray()).replace("[", "").replace("]", ""));
				}
				dispose();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		contentPane.add(textPane, BorderLayout.NORTH);
	}
}
