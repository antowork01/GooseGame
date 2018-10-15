import java.util.ArrayList;

import commons.Constants;
import commons.TypeOfSpace;

public class Table implements TableInterface{
	public static  ArrayList <Player> players = new ArrayList<Player>();
	public Space[] GamesTable = new Space[Constants.FINISH+1];
	static boolean win = false;
	
	
	public Space getTable(int i){
		return GamesTable[i];		
	}
/*		
	public void CreateGamesTable(Space[] GamesTable) {
		for (byte i=START;i<=FINISH;i++)
			GamesTable[i]= new Space(i);
	}
	
*/

	public Table() {
		for (byte i=Constants.START; i <= Constants.FINISH; i++)
			GamesTable[i]= new Space(i);
	}


	
	
	/**
	 * metodo che aggiunge nuovi giocatori, 
	 * stampa a video i nomi dei giocatori,
	 * ritorna true se l'aggiunta è andata a buon fine falso altrimenti
	 */
	@Override
	public boolean addPlayer(Player name) {
		if (!verifyExistingPlayer(name)) {
			players.add(name);
			Utilities.printPlayers(players);
			return true;
		}
		else {
			Utilities.printPlayerExist(name);
			return false;			
		}				
	}
		

	/**
	 * metodo che verifica se un utente esiste già,
	 * restituisce true se esiste, falso altrimenti
	 */
	@Override
	public boolean verifyExistingPlayer(Player name) {
		
		for(Player player :  players) {
			if(player.getName().equals(name.getName()))
				return true;
		}
		return false;
	}



	/**
	 * metodo che implementa il movimento di un giocartore
	 * se un giocatore finisce in una casella occupata da un altro giocatore
	 * quest'ultimo tornerà nella casella da dove proveniva il giocatore che ha fatto la mossa;
	 * SE UN GIOCATORE FA DOPPIO TIRA ANCORA
	 */
	@Override
	public void move(Player name) {
		//dices = name.dices; 
		if(!Utilities.printRolls(name,GooseGameMain.in)) {
			name.dices = Dice.rollDices();
			//name.dices= dices;
			}
		
		name.newPosition = (byte) (name.oldPosition + name.dices[2]);

		if(name.newPosition>Constants.FINISH) {
			//
			Utilities.printMove(name,name.dices);
			name.newPosition = (byte)Utilities.calculate(name.oldPosition, name.dices[2]);
			updatePosition(name);
			
		}
				
		else {
			
			Utilities.printMove(name,name.dices);
			updatePosition(name);
			}
		//GamesTable[name.newPosition].present= name; 
		
		name.oldPosition = name.newPosition;
		
		if (name.dices[0]==name.dices[1]) {
			move(name);
			
		}
	
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
			Utilities.printMoveBack(name.oldPosition, name.newPosition, GamesTable[name.oldPosition].present.getName());
			
			
		}
		verifyPosition(GamesTable[name.newPosition].label,name);
		
	}
	
	private void verifyPosition(TypeOfSpace label, Player name) {
		switch (label) {
		case GOOSE:
			System.out.println("GOOSE!");
			name.oldPosition=name.newPosition;
			name.newPosition+=name.dices[2];
			
			Utilities.printMove(name, name.dices);
			updatePosition(name);
			
			break;
		case BRIDGE:
			System.out.println("BRIDGE!");
			System.out.println(name.getName() +" JUMPS TO 12");
			name.newPosition=12;
			updatePosition(name);
			break;
		case FINISH:
			win=true;
			System.out.println(name.getName() +" WIN!!! CONGRATULATIONS");
			break;
		case START:
			break;
		default:
			break;
		}
	}
	

/**
 * classe interna che gestisce le caselle della tavola di gioco 
 *
 */
 class Space{
	 
	TypeOfSpace label=TypeOfSpace.EMPTY;
	Player present = null;
	/**
	 * inizializzando ogni oggetto viene specificato che propietà deve avere 
	 * possono essere START FINISH Bridge o Goose
	 * @param i è il numero della casella
	 */
	Space(byte i){
		/*number = i;
		if(i==6)
			label = TypeOfSpace.BRIDGE;
		else if(i==5||i== 9||i== 14||i== 18||i== 23||i== 27)
			label= TypeOfSpace.GOOSE;
		else if(i==Table.START)
			label =TypeOfSpace.START;
		else if(i==Table.FINISH)
			label = TypeOfSpace.FINISH;
		else
			label = null;
		*/
		switch (i) {
			case 6:
				label = TypeOfSpace.BRIDGE;
				break;
			case 5:
				label= TypeOfSpace.GOOSE;
				break;
			case 9:
				label= TypeOfSpace.GOOSE;
				break;
			case 14:
				label= TypeOfSpace.GOOSE;
				break;
			case 18:
				label= TypeOfSpace.GOOSE;
				break;
			case 23:
				label= TypeOfSpace.GOOSE;
				break;
			case 27:
				label= TypeOfSpace.GOOSE;
				break;
			case Constants.START:
				label = TypeOfSpace.START;
				break;
			case Constants.FINISH:
				label = TypeOfSpace.FINISH;
				break;
			default:
				label = TypeOfSpace.EMPTY;
				break;
			}
		
			
	}
	public Player getPlayer() {
		return present;
	}
	public String getPlayerName() {
		if(present==null)
			return "";
		return present.getName();
	}
	public TypeOfSpace getLabel(){
		return label;
	}
	public String getLabelString() {
		if (label==TypeOfSpace.EMPTY)
			return "";
		return label.toString();
	}
}

}