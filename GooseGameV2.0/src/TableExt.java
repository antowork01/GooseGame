

import javax.swing.JOptionPane;

import commons.Constants;
import commons.TypeOfSpace;

public class TableExt extends Table {

	public TableExt() {
		super();
	}
	
	public boolean addPlayer(Player name) {
		players.add(name);
		return true;
		
	}
	/**
	 * metodo che verifica se la stringa passata come parametro corrisponde al nome di un giocatore presente
	 * @param name
	 * @return true se già è presente, falso altrimenti
	 */
public boolean verifyExistingPlayer(String name) {
		
		for(Player player :  players) {
			if(player.getName().equals(name))
				return true;
		}
		return false;
	}

private void updatePosition(Player name) {
	if(GamesTable[name.newPosition].present==null || name.newPosition==Constants.START || GamesTable[name.newPosition].present == name ){
		GamesTable[name.newPosition].present= name;
		if(name.oldPosition!=Constants.START){
			GamesTable[name.oldPosition].present= null;
		}
		
		
	}else {//scambia le posizioni
		GamesTable[name.newPosition].present.newPosition=name.oldPosition;
		GamesTable[name.oldPosition].present= GamesTable[name.newPosition].present;
		GamesTable[name.newPosition].present= name;
		GamesTable[name.oldPosition].present.oldPosition=GamesTable[name.oldPosition].present.newPosition;
		UtilitiesForGUI.printMoveBack(name.oldPosition, name.newPosition, GamesTable[name.oldPosition].present.getName());
		
		
	}
	verifyPosition(GamesTable[name.newPosition].label,name);
}

private void verifyPosition(TypeOfSpace label, Player name) {
	switch (label) {
	case GOOSE:
		System.out.println("GOOSE!");
		name.oldPosition=name.newPosition;
		name.newPosition+=name.dices[2];
		JOptionPane.showMessageDialog(null, "sei finito sulla casella dell'oca avanzerai automaticamente", "GOOSE!", JOptionPane.NO_OPTION);
		Utilities.printMove(name, name.dices);
		updatePosition(name);
		
		break;
	case BRIDGE:
		JOptionPane.showMessageDialog(null, "salterai fino alla casella 12", "BRIDGE!", JOptionPane.NO_OPTION);
		System.out.println("BRIDGE!");
		System.out.println(name.getName() +" JUMPS TO 12");
		name.newPosition=12;
		updatePosition(name);
		break;
	case FINISH:
		win=true;
		JOptionPane.showMessageDialog(null, name.getName() + "HAI VINTO! COMPLIMENTI", "WIN!", JOptionPane.NO_OPTION);
		PlayerGUI.main(null);
		break;
	case START:
		break;
	default:
		break;
	}
}

public void move(Player name) {
	name.newPosition = (byte) (name.oldPosition + name.dices[2]);

	if(name.newPosition>Constants.FINISH) {		
		name.newPosition = (byte)Utilities.calculate(name.oldPosition, name.dices[2]);
		updatePosition(name);		
	}
			
	else {		
		Utilities.printMove(name,name.dices);
		updatePosition(name);
		}
	name.oldPosition = name.newPosition;
	
	if (name.dices[0]==name.dices[1]&&!win){
		JOptionPane.showMessageDialog(null, "hai fatto doppio! muovi ancora", "Bene!", JOptionPane.NO_OPTION);		
	}
	else
		GUITable.indexPlayers++;
	if(GUITable.indexPlayers==players.size())
		GUITable.indexPlayers=0;
	
}
}
