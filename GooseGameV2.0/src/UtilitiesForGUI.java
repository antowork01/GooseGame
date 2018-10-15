import java.util.ArrayList;
import javax.swing.JOptionPane;
public class UtilitiesForGUI {
	
	/**
	 * metodo che stampa "Players: nome1, nome2, ecc"
	 * @param players un ArrayList contenente tutti i giocatori connessi
	 */
	public static String printPlayersForGUI(ArrayList<Player> players) {
		String player = null;
		
		for(int i=0;i<players.size();i++) {
			player = players.get(i).getName();
			if(players.size()!=1 && i!=players.size()-1) 
				player +=(", ");
		}
		return player;
	}

	
	/**
	 * metodo che consente di inserire giocatori
	 */
	public static boolean insertPlayers(TableExt table, String player) {
		String name;
		PlayerGUI.isVerified= !table.verifyExistingPlayer(player);
		if(PlayerGUI.isVerified==true) {
			name = player;
			PlayerGUI.table.addPlayer(new Player(name));
			JOptionPane.showMessageDialog(null, "Utente aggiunto con successo", "Bene!", JOptionPane.NO_OPTION);
			PlayerGUI.isVerified=false;
			return true;
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Il nome dell'utente inserito esiste già", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		

	}
	
	
	/**
	 * metodo che manda un messaggio quando un giocatoreruba la casella a un altro
	 */
	public static void printMoveBack(byte position, byte newPosition, String name) {
		JOptionPane.showMessageDialog(null,"Nella "+ newPosition + " c'è " + name + " , che ritorna nella " + position , "oops!", JOptionPane.NO_OPTION);
		System.out.println();				
	}
}
