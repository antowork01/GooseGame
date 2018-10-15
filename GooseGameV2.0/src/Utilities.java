
import java.util.ArrayList;
import java.util.Scanner;
import commons.Constants;

public class Utilities {

	/**
	 * metodo che stampa "Players: nome1, nome2, ecc"
	 * @param players un ArrayList contenente tutti i giocatori connessi
	 */
	public static void printPlayers(ArrayList<Player> players) {
		System.out.print("Players: ");
		for(int i=0;i<players.size();i++) {
			System.out.print(players.get(i).getName());
			if(players.size()!=1 && i!=players.size()-1) 
				System.out.print(", ");
		}
		System.out.println("");
	}

	
	/**
	 * metodo che stampa "nomegiocatore: already existing player"
	 * @param name
	 */
	public static void printPlayerExist(Player name) {
		System.out.println(name.getName() + ": already existing player");
	}

	/**
	 * metodo che stampa i risultati del lancio dei dadi
	 * @param dices
	 */
	public static void printDices(byte[] dices) {
		System.out.print(dices[0] + ", " + dices[1]);
		
	}
	/**
	 * metodo che stampa a video "nomeGiocatore move: " 
	 * offre la possibilità di inserire i valori dei dadi che se saranno adeguati verranno utilizzati 
	 * altrimenti il computer li genererà automaticamente
	 * ci si aspetta due valori interi compresi tra 1 e 6 separati da una virgola.
	 * @param name
	 * @return true se i valori sono inseriti correttamente
	 */
	public static boolean printRolls(Player name, Scanner in) {
		
		boolean rep=false;
				
		System.out.print(" move " + name.getName() + ": " );
		String s=in.nextLine();
		if(s.contains(",")){
			String[] dicesString = s.split(",");
			if(dicesString.length==2){
				byte[] dices = new byte[3];
				dices[0]=(byte)Integer.parseInt(dicesString[0]);
				dices[1]=(byte)Integer.parseInt(dicesString[1]);
				if(dices[0]<7&&dices[0]>0&&dices[1]<7&&dices[1]>0){
					dices[2]=(byte) (dices[0] + dices[1]);
					name.dices=dices;
					return rep=true;
				}
			}
		}
		System.out.println("i valori inseriti non sono accettabili come risultato di dadi, il computer tirerà automaticamente");;
		return rep;
	}
	
	/**
	 * metodo che stampa il movimento 
	 */
	public static void printMove(Player name,byte[] dices) {
		printDices(dices);
		System.out.print(". " + name.getName() + " move from " + name.oldPosition + " to ");
		if( name.newPosition > Constants.FINISH )
			System.out.print(Constants.FINISH + ". " + name.getName() + " Bounces! " + name.getName() + " retuns to "
					+ Integer.toString(calculate(name.oldPosition,dices[2]))); 
		else	System.out.print(name.newPosition);
		System.out.println();
	}
	/**
	 * metodo per il calcolo della differenza alla fine della tavola
	 */
	public static int calculate(byte position, byte dices) {
		int value1,value2;
		value1=Constants.FINISH-position;
		value2=dices-value1;
		value1=Constants.FINISH-value2;
		
		return value1;
	}
	
	/**
	 * metodo che stampa il movimento di un giocatore quando un altro gli ruba la casella
	 */
	public static void printMoveBack(byte position, byte newPosition, String name) {
		System.out.println("On "+ newPosition + " there is " + name + " , who returns to " + position);				
	}

	
	/**
	 * metodo che consente di inserire giocatori da riga di comando
	 */
	public static void insertPlayers(Table table, Scanner in) {
		String name;
		boolean exit = false;
		
		do {
			name = in.nextLine();
			if (!name.startsWith(Constants.ADD_PLAYER))
			{
				System.out.printf("il comando inserito non è accettabile  digita \"%s\" seguito dal tuo nome (per esempio: add player pippo) \n", Constants.ADD_PLAYER);
				continue;
			}
						
			name = name.replaceFirst(Constants.ADD_PLAYER, "");
			table.addPlayer(new Player(name));

			
		    System.out.printf("ci sono altri giocatori? digita \"%s\" per inserire altri giocatori \n", Constants.YES_RESPONSE);
			
			String response = in.nextLine().toLowerCase();
			if(!response.equals(Constants.YES_RESPONSE)) {
				exit = true;
			}
			else 
			{
				System.out.println("inserisci il nome di un altro giocatore preceduto da "+ Constants.ADD_PLAYER);//System.out.println();
			}		
		}while (!exit);
	}
}
