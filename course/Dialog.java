package course;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Dialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2160746013654435297L;

	/**
	 * Launch the application.
	 */
	public static void main(String arg) {
		try {
			Dialog dialog = new Dialog(arg);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog(String arg) {
		setFocusTraversalPolicyProvider(true);
		setLocationByPlatform(true);
		setAlwaysOnTop(true);
		setTitle("Увага");
		setBounds(100, 100, 340, 110);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JTextPane txtpnAsd = new JTextPane();
			txtpnAsd.setFocusable(false);
			txtpnAsd.setFocusTraversalKeysEnabled(false);
			txtpnAsd.setFocusCycleRoot(false);
			txtpnAsd.setVerifyInputWhenFocusTarget(false);
			txtpnAsd.setOpaque(false);
			txtpnAsd.setRequestFocusEnabled(false);
			txtpnAsd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			txtpnAsd.setText(arg);
			txtpnAsd.setEditable(false);
			txtpnAsd.setBackground(SystemColor.control);
			StyledDocument doc = txtpnAsd.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
			getContentPane().add(txtpnAsd, BorderLayout.CENTER);
		}
	}

}
