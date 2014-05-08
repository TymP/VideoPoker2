import java.util.ArrayList;
import java.util.Scanner;


public class UI 
{
	/*
	 * Takes in an integer representing score of player's hand, prints out the corresponding string.
	 */
	public void interpretScore(int score){
		String[] scoreVec = new String[] {"Nothing", "a pair", "Two Pairs", "Three of a kind", "a straight", "a flush", "a full House","Four of a kind", "a traight Flush", "a Royal Flush!"};
		System.out.println("You have  " + scoreVec[score]);
		System.out.println("\r");
	}
	
	/*
	 * takes user input to define an array of indexes to remove from the player's hand.
	 */
	public Integer[] choose()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Type the cards you'd like to exchange (i.e. 1 for the first, 5 for the fifth). Enter 'd' when you're done.");
		ArrayList<Integer> remove = new ArrayList<Integer>();
		while(sc.hasNextInt()){
			remove.add(sc.nextInt()-1);
		}
		System.out.println("OK!");
		return toIntArray(remove);
	}
	/*
	 * Interacts with user to stop or continue game loop. 
	 */
	public boolean stop(){
		boolean result = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Stop? Type stop to stop.");
		System.out.print("\r");
		String desire = sc.next().toLowerCase();
		if(desire.equals("stop")){
			result = true;
		}
		return result;
	}
	
	public Integer[] toIntArray(ArrayList<Integer> toConvert){
		Integer[] result = new Integer[toConvert.size()];
		for(int i = 0 ; i<result.length;i++){
			result[i] = toConvert.get(i).intValue();
		}
		return result;
	}
	
	public void displayHand(Player jugador){
		System.out.println("Here's your hand: "+'\r');
		for(Card card : jugador.getHand()){
			if(card.value>1 && card.value<11){
				System.out.println(card.value + "  of  " +card.suit);
			}
			else{
				System.out.println(getFace(card.value) + "  of  " +card.suit);
			}
			
		}
		System.out.println('\r');
	}
	/*
	 * Converts face cards' card values to faces for display to user.
	 */
	public String getFace(int val){
		String result= null;
		switch (val)
		{
			case 14: result = "Ace";//aces high
					break;
			case 11:result = "Jack";
					break;
			case 12: result = "Queen";
					break;
			case 13: result = "King";
					break;
		}
		return result;
	}
	
}
