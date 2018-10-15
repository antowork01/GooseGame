import commons.Constants;

public class Player {

	private String name;
	
	byte oldPosition;
	byte newPosition;
	protected byte[] dices = new byte[3];
	public Player(String name) {
		this.name=name;
		oldPosition = Constants.START;		
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}

}
