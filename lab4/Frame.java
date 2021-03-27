package lab4;
import lab2.Variant3;

import static lab2.Variant3.maxIndex;
import static lab2.Variant3.minIndex;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.TextField;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField distance_res;
	private JTextField numberOfNegative_res;
	private JTextField summ_res;
	private JTextField average_res;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		///////////////////////////////////////////////////////////////////////////
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JTextPane txtpnDx = new JTextPane();
		txtpnDx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnDx.setEditable(false);
		txtpnDx.setText("dx = ");
		topPanel.add(txtpnDx);
		
		TextField textField_dx = new TextField();
		textField_dx.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_dx.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Test(textField_dx, "dx");
			}
		});
		textField_dx.setColumns(4);
//		textField_dx.setSize(WIDTH, HEIGHT);
		topPanel.add(textField_dx);
		
		JTextPane txtpnA = new JTextPane();
		txtpnA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnA.setEditable(false);
		txtpnA.setText("a = ");
		topPanel.add(txtpnA);
		
		TextField textField_a = new TextField();
		textField_a.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_a.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Test(textField_a, "a");
			}
		});
		textField_a.setColumns(4);
		topPanel.add(textField_a);
		
		JTextPane txtpnB = new JTextPane();
		txtpnB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnB.setEditable(false);
		txtpnB.setText("b = ");
		topPanel.add(txtpnB);
		
		TextField textField_b = new TextField();
		textField_b.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_b.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Test(textField_b, "b");
			}
		});
		textField_b.setColumns(4);
		topPanel.add(textField_b);
		///////////////////////////////////////////////////////////////////
		JPanel centralPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) centralPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		contentPane.add(centralPanel, BorderLayout.CENTER);
		
		JPanel task_1 = new JPanel();
		FlowLayout fl_task_1 = (FlowLayout) task_1.getLayout();
		fl_task_1.setAlignment(FlowLayout.LEFT);
		fl_task_1.setHgap(40);
		centralPanel.add(task_1);
		
		JTextField distance = new JTextField();
		distance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		distance.setBackground(Color.WHITE);
		distance.setColumns(20);
		task_1.add(distance);
		distance.setText("Відстань: ");
		distance.setEditable(false);
		
		distance_res = new JTextField();
		distance_res.setFont(new Font("Tahoma", Font.PLAIN, 14));
		distance_res.setEnabled(false);
		task_1.add(distance_res);
		distance_res.setEditable(false);
		distance_res.setColumns(10);
		
		JPanel task_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) task_2.getLayout();
		flowLayout.setHgap(40);
		centralPanel.add(task_2);
		
		JTextField numberOfNegative = new JTextField();
		numberOfNegative.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberOfNegative.setColumns(20);
		numberOfNegative.setBackground(Color.WHITE);
		task_2.add(numberOfNegative);
		numberOfNegative.setText("Кількість відємних елементів: ");
		numberOfNegative.setEditable(false);
		
		numberOfNegative_res = new JTextField();
		numberOfNegative_res.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberOfNegative_res.setEnabled(false);
		numberOfNegative_res.setEditable(false);
		task_2.add(numberOfNegative_res);
		numberOfNegative_res.setColumns(10);
		
		JPanel panel_summ = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_summ.getLayout();
		flowLayout_1.setHgap(40);
		centralPanel.add(panel_summ);
		
		JTextField summ = new JTextField();
		summ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		summ.setBackground(Color.WHITE);
		summ.setColumns(20);
		summ.setText("Сума додатніх елементів:       ");
		summ.setEditable(false);
		panel_summ.add(summ);
		
		summ_res = new JTextField();
		summ_res.setFont(new Font("Tahoma", Font.PLAIN, 14));
		summ_res.setEnabled(false);
		summ_res.setEditable(false);
		summ_res.setColumns(10);
		panel_summ.add(summ_res);
		
		JPanel panel_average = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_average.getLayout();
		flowLayout_2.setHgap(40);
		centralPanel.add(panel_average);
		
		JTextField average = new JTextField();
		average.setFont(new Font("Tahoma", Font.PLAIN, 14));
		average.setBackground(Color.WHITE);
		average.setColumns(20);
		average.setText("Середнє арифметичне:");
		average.setEditable(false);
		panel_average.add(average);
		
		average_res = new JTextField();
		average_res.setFont(new Font("Tahoma", Font.PLAIN, 14));
		average_res.setEnabled(false);
		average_res.setEditable(false);
		average_res.setColumns(10);
		panel_average.add(average_res);
		/////////////////////////////////////////////////////////////////////
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Gen");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setEnabled(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (textField_dx.getBackground() == Color.RED || 
					textField_a.getBackground() == Color.RED ||
					textField_b.getBackground()== Color.RED) {
					btnNewButton.setEnabled(false);
				}
				else {
					btnNewButton.setEnabled(true);
				}
				if (textField_dx.getText().equals("") || 
						textField_a.getText().equals("") ||
						textField_b.getText().equals("")) {
						btnNewButton.setEnabled(false);
					}
					else {
						btnNewButton.setEnabled(true);
					}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				double x1 = 0;
				double x2 = 0;
				double x3 = 0;
					try {
						x1 = Test(textField_dx, "dx");
						x2 = Test(textField_a, "a");
						x3 = Test(textField_b, "b");
						if (x3 - x2 < 0) {
							throw new Exception("\"а\" має бути менше або дорівнювати \"b\"");
						}
					}
					catch (Exception s) {
						String str = s.getMessage();
						Error.main(str);
						return;
					}

				lab2.Variant3 lab2 = new Variant3();
				lab2.genArray(x1, x2, x3);

				int size;
				if (minIndex >= maxIndex) {
					size = lab2.calcSizeArrayDX(1, maxIndex, minIndex);
				}
				else {
					size = lab2.calcSizeArrayDX(1, minIndex, maxIndex);
				}
				System.out.println("\n\n\n");
				for (int i = 0; i < size; i++) {
					System.out.printf("y[%d] = %.3f\n", i, lab2.showArrayIndexY(i));
				}
				
				if (minIndex == maxIndex) {
					size = 0;
				}
				else if (size != 0) {
					size -= 2;
				}
				System.out.println("minIndex = " + minIndex);
				System.out.println("maxIndex = " + maxIndex);
				System.out.println("size = " + size);
				
				distance_res.setText(String.valueOf(size));
				distance_res.setEnabled(true);
				
				size = lab2.calcSizeArrayDX(x1, x2, x3);
				int num = 0;
				double summ = 0;
				for (int i = 0; i < size; i++) {
					if (lab2.showArrayIndexY(i) < 0) {
						num++;
					}
					else {
						summ = Variant3.round(summ += lab2.showArrayIndexY(i), 3);
					}
				}
				numberOfNegative_res.setText(String.valueOf(num));
				summ_res.setText(String.valueOf(summ));
				average_res.setText(String.valueOf(Variant3.round(summ / size, 3)));
				
				distance_res.setEnabled(true);
				numberOfNegative_res.setEnabled(true);
				summ_res.setEnabled(true);
				average_res.setEnabled(true);
			}
		});
		lowerPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("?");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Foto.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lowerPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Завдання з 2-ї лабораторної");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FotoLab2.main(null);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lowerPanel.add(btnNewButton_2);
	}
	@SuppressWarnings("finally")
	private double Test(TextField textField, String lvl) {		
		boolean ok = true;
		double x1 = 1;
		
		textField.setText(textField.getText().replace(',', '.'));
		try {
			if (textField.getText().length() < 1) {
				throw new Exception("Введіть " + lvl);
			}
			else {
				x1 = Double.parseDouble(textField.getText());
			}
			
			if (lvl.equals("dx") && (x1 <= 0 || textField.getText().equals(""))) {
				throw new Exception("Крок не може бути менше або дорівнювати 0");
			}
		}
		catch(NumberFormatException e1) {
				ok = false;
				String str = e1.getMessage();
				str = str.substring(18);
				Error.main("Помилка: замість " + str + " потрібно ввести число");
		}
		catch(Exception e1) {
			ok = false;
			String str = e1.getMessage();
			Error.main(str);
		}
		finally {
			if (!ok) {
				textField.setBackground(Color.RED);
			}
			else {
				textField.setBackground(Color.WHITE);
			}
			return x1;
		}
	}
}
