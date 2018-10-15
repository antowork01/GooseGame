import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerGUI {

	private JFrame frame;
	private JTextField txtInsertName;

	public static TableExt table = new TableExt();
	public static boolean isVerified= false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerGUI window = new PlayerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(99,164,194));
		frame.setBounds(100, 100, 271, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtInsertName = new JTextField(15);
		txtInsertName.setBounds(10, 109, 136, 20);
		frame.getContentPane().add(txtInsertName);
		txtInsertName.setColumns(10);
		
		
		JTextArea lblGiocatori = new JTextArea();
		lblGiocatori.append("Players: ");
		lblGiocatori.setLineWrap(true);
		lblGiocatori.setForeground(new Color(63,72,204));
		lblGiocatori.setBounds(10, 170, 235, 80);
		frame.getContentPane().add(lblGiocatori);
		
		JButton btnOk = new JButton("add player");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(UtilitiesForGUI.insertPlayers(table, txtInsertName.getText())) {
					lblGiocatori.append(UtilitiesForGUI.printPlayersForGUI(table.players)+ ", ");
				}
			}
		});
		btnOk.setBounds(10, 136, 136, 23);
		frame.getContentPane().add(btnOk);

		JLabel lblBenvenutoInGoose = new JLabel("BENVENUTO IN GOOSE GAME ");
		lblBenvenutoInGoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoInGoose.setBounds(45, 11, 185, 20);
		frame.getContentPane().add(lblBenvenutoInGoose);
		
		JLabel lblPerIniziareA = new JLabel("Per iniziare a giocare digita il tuo nome");
		lblPerIniziareA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerIniziareA.setBounds(10, 42, 235, 20);
		frame.getContentPane().add(lblPerIniziareA);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.players.size()==0)
					JOptionPane.showMessageDialog(null, "Devi prima aggiungere almeno un giocatore", "Attenzione!", JOptionPane.ERROR_MESSAGE);
				else
					GUITable.main(null);
			}
		});
		btnStart.setBounds(84, 73, 89, 23);
		frame.getContentPane().add(btnStart);
		
	}
}
