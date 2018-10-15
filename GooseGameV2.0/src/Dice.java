import java.util.Random;

class Dice {
	static Random random = new Random();
	static byte[] value = new byte[3];
	
	
	public static byte[] rollDices() {

		value[0]= (byte)(random.nextInt(6)+1);
		value[1]= (byte)(random.nextInt(6)+1);
		value[2]= (byte) (value[0] + value[1]);
		
		return value;
	}

}
