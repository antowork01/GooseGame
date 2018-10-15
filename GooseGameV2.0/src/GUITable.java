import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GUITable extends JFrame {

	//private static final long serialVersionUID = 1L;
	static int indexPlayers=0;
	static boolean daditirati = false;
	static TableExt table = new TableExt();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		GUITable frame=new GUITable();
		

		
	}

	/**
	 * Create the application.
	 */
	public GUITable() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel pannello;
		
		String path="empty.jpeg";
		
		pannello=new JPanel(new GridLayout(8,8));
		pannello.setBackground(Color.CYAN);
		
		Player player =  table.players.get(indexPlayers);
			
				for(int i=1; i<=64; i++) {
		
					    if(i!=64) {
					    	if(PlayerGUI.table.GamesTable[i].getLabelString().equals("BRIDGE"))
					    		path = "bridge.jpeg";
					    	else if(PlayerGUI.table.GamesTable[i].getLabelString().equals("GOOSE"))
					    		path = "goose.jpeg";
					    	else if(i==1)
				    		path = "start.jpeg";
					    	else if(PlayerGUI.table.GamesTable[i].getLabelString().equals("FINISH"))
					    		path = "finish.jpeg";
					    else
					    	path = "empty.jpeg";
					    }
					    else
					    	path= "nothing.jpg";
					
					JImgPanel p = new JImgPanel(path);
		            if(i!=64) {
		            	
		
		            	p.setVisible(true);
		            	p.paintComponents(getGraphics());
		            	
		            	JLabel jlab = new JLabel("                                   ");
		            	p.add(jlab);
		            	JLabel jlabel=new JLabel();
		            	jlabel.setVerticalTextPosition(JLabel.CENTER); 
		            	jlabel.setHorizontalTextPosition(JLabel.CENTER);
		            	jlabel.setForeground(new Color(63,72,204));
		            	jlabel.setText(i + "   " + PlayerGUI.table.GamesTable[i].getLabelString());
		            	
		            	p.add(jlabel);
		            	
		            	JLabel jlabell=new JLabel();
		            	jlabell.setForeground(new Color(255,201,14));
		            	for(Player plx: TableExt.players) {
		            		if(plx.newPosition==i)
		            			jlabell.setText(plx.getName());
		            		p.add(jlabell);
						}
       	
		            }
		            else {
		        		JLabel jlabelPlayer = new JLabel();
		        		jlabelPlayer.setForeground(new Color(255,201,14));
		        		jlabelPlayer.setText("Player: " + player.getName());
		        		p.add(jlabelPlayer);
		        		
		        		
		        		JTextArea jDice;
		        		jDice =new JTextArea();
		        		jDice.append("Dadi: ");
		        		p.add(jDice);
		        		
		        		
		            	JButton btnDices = new JButton("Tira i dadi");
		            	btnDices.addActionListener(new ActionListener() {
		
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								player.dices = Dice.rollDices();
								jDice.setText("DADI: "+ 
										player.dices[0]+","+player.dices[1]);
								daditirati=true;
							}
		            	});
		        		p.add(btnDices);

		        		JButton btnMove = new JButton("Muovi");
		        		btnMove.addActionListener(new ActionListener() {
		
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								if (daditirati) {
									table.move(player);
									daditirati=false;
									initialize();
								}
								else
									JOptionPane.showMessageDialog(null, "Devi prima tirare i dadi!", "ehi!", JOptionPane.ERROR_MESSAGE);
								

							}
		            	});
		        		p.add(btnMove);
		            }
		            pannello.add(p);
				}
				setContentPane(pannello);
				setTitle("GooseGame");
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        pack();
		        setSize(800,800);
		        setVisible(true);

			}

}

