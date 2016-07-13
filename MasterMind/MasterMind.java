import java.io.IOException;
import java.util.Scanner;
/*
	esboço compartilhado pelo slack
	data: 16/06/2016
	autor: Prof. Vinny
*/
class MasterMind {
	
	static String 	code 		= "VAXR";
	static int 		limit 		= 10;
	static int 		tries 		= 1;
	static boolean 	codeBroken 	= false;
	static String 	playerTry;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // clear console
		
		boolean hasTries 		= true;
		
		while ( hasTries && !codeBroken ) {
			codeBroken = evaluateTry(codeBroken, code);
			tries++;
			hasTries = tries <= limit;
		}
		showResult();
	}
	
	static void showResult(){
		if (codeBroken) show("You win."); else show ("You lost");
	}
	static boolean evaluateTry(boolean codeBroken, String code) {
		show("Your try: ");
		playerTry = readTry();
		String feedback = getFeedback(playerTry);
		show("Feedback: "+feedback);
		// if (playerTry.equals(code)) codeBroken = true;
		if (playerTry.equals(code)) return true;
		
		return false;
	}
	
	static void show(String text) {
		System.out.print(text);
	}
	
	static String readTry(){
		return new java.util.Scanner(System.in).nextLine().toUpperCase(); //.toString().toUpperCase();
	}
	
	static String getFeedback( String plrTry ){
		int rightPositions 	= 0;
		int rightColors 	= 0;
		//TODO identify right tries
		
		for ( int i = 0 ; i < 4 ; i++) {
			for ( int j = 0 ; j < plrTry.length() ; j++) {
				if ( code.charAt(i) == plrTry.charAt( j ) ) {
					rightPositions++;
				}
			}
		}
		
		for ( int i = 0 ; i < plrTry.length() ; i++) {
			if ( code.indexOf(plrTry.charAt(i)) > -1 ) {
				rightColors++;
			}
		}
		
		return String.format("%s right positions, %s right colors\n", rightPositions, rightColors);
	}
}