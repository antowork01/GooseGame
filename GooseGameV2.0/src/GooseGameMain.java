import java.util.Scanner;

import commons.Constants;



public class GooseGameMain {
	static Scanner in =new Scanner (System.in);
	public static void main(String[] args) {
		
		Table table = new Table();
			
		printStartGame();
		Utilities.insertPlayers(table,in);		
		printHowMovePlayer();
		
		while(!Table.win) {
			for(Player player :  table.players) {
				table.move(player);
				if(Table.win)break;
			}
		}
		in.close();
	}
	
	private static void printStartGame()
	{
		System.out.println("Benvenuto in GooseGame");
		System.out.println("le regole sono molto semplici: \n tirando i dadi si avanza, \n quando si finisce in una casella GOOSE avanzerai ancora automaticamente, \n nella casella BRIDGE verrai catapultato in avanti, \n se fai doppio puoi tirare ancora, \n per vincere bisogna arrivare precisamente nella casella finale altrimenti verrai rimbalzato indietro. \n ");
		System.out.println("per cominciare a giocare devi aggiungere un giocatore:");
		System.out.println("digita "+ Constants.ADD_PLAYER +" seguito dal tuo nome (per esempio: add player pippo)");
	}
	
	private static void printHowMovePlayer()
	{
		System.out.println("Bene! che il gioco abbia inizio:");
		System.out.println("-Digita "+Constants.MOVE_PLAYER+" 'namePlayer' 'numberOneDice', 'numberTwoDice'  (es. "+Constants.MOVE_PLAYER+" Pippo 1,2) per muovere un determinato giocatore");
		System.out.println("oppure");
		System.out.println("-Digita "+ Constants.MOVE_PLAYER +" 'namePlayer'  (es. "+ Constants.MOVE_PLAYER +" Pippo) e il sistema girerà i dadi per voi!");	
	}
}