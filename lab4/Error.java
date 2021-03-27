package lab4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Error extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String error;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					error = args;
					Error frame = new Error();
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
	public Error() {
		setType(Type.UTILITY);
		setBounds(100, 100, 531, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextField textPane = new JTextField();
		textPane.setColumns(30);
		textPane.setText(error);
		textPane.setHorizontalAlignment(SwingConstants.CENTER);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane.setEditable(false);
		contentPane.add(textPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

}
