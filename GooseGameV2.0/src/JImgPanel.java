import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class JImgPanel extends JPanel {
    Image    sfondo;
    
    public void paintComponent(Graphics g){
    	
        g.drawImage(sfondo, 0, 0, this.getWidth(), this.getHeight(),this);
    }
    
    public JImgPanel(String file) {
        super();
        try {
            this.sfondo= getToolkit().createImage(file);
        }
        catch(Exception e) {System.out.println("immagine non trovata");}
    }

    public void setBounds (int x, int y){
        super.setBounds(x, y, sfondo.getWidth(this), sfondo.getHeight(this));

    }
}