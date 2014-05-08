import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;


public class Deck {

	public Card[] cards;
	private final int DECK_SIZE = 52;

	/*
	 * Initialises the instance of deck. Fills the instance variable (array)
	 * cards by calling the constructor on each slot, initialising a new card.
	 */
	public Deck() {
		cards = new Card[DECK_SIZE];
		String[] suits = { "spades", "diamonds", "hearts", "clubs" };
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14 };

		int cardIndex = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				cards[cardIndex] = new Card(suits[i], values[j]);
				cardIndex+=1;

			}

		}
		shuffle();

	}

	public void shuffle() {
		int[] order = preShuffle(cards.length);
		Card[] replace = new Card[cards.length]; //create replacement array for shuffled deck.
		for(int i = 0; i<cards.length; i++){
			replace[i] = this.cards[order[i]];
		}
		this.cards = replace; //overwrite
	}

	
	public void removeCard(Integer[] indexesToRemove)
	{
		Card[] replacement = new Card[cards.length - indexesToRemove.length]; // Create new Card array of new length.
		int replacementIndex = 0; // set starting index on the new array.
		for(Integer i =0; i<cards.length; i++){ //Iterate through each index of the old hand. 
			if(Arrays.asList(indexesToRemove).contains(i)== false){ // if the current index is not included in the list of indexes to remove:
				replacement[replacementIndex]=cards[i]; //copy this index of the current hand to the current index of the replacement hand.
				replacementIndex+=1;// shift to the next index of the replacement hand. 
			}
			
			
		}
		cards = replacement; //overwrite
	}
	
		
	public Card[] deal(int howMany) {
		// Initialise return array
		Card[] handFull = new Card[howMany];
		
		//populate return array with the top indeces of the instance variable cards. 
		Integer[] removeList = new Integer[howMany];
		for (int i = 0; i < howMany; i++) {
			handFull[i] = cards[i];
			removeList[i] = i;
		}
		//Remove the selected cards from the deck. 
		removeCard(removeList);
		
			return handFull;
	}
	
	public int[] preShuffle(int size)
	{
		
		Random rn = new Random();
		ArrayList<Integer> possibleIndexes = new ArrayList<Integer>(); //initialise an array list (mutable array length)
		
		for(int i = 0; i<size; i++){
			possibleIndexes.add(i); //appends an i to the array. generates an array of all the numbers from 0 to maxRandomNumber -1
		}
		int[] result = new int[size]; //initialise return int[]
		for (int i = 0; i<size; i++){ //iterate through return array.
			int random = rn.nextInt(possibleIndexes.size());// picks a random index of the arrayList. As the list shrinks the indeces available shrink. 
			result[i] = possibleIndexes.remove(random); // remove number at this index. Sets this iteration of the return array = to this value. 
		}
		return result;
			
	}
	

	public boolean contains(int[] vector, int num){
		boolean result = false;
		for (int number : vector){
			if(number == num){
				result = true;
			}
		}
		return result;
	}	
}	
