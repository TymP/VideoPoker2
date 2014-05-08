import java.util.ArrayList;
import java.util.Arrays;

public class Judge {

	/**
	 * @param args
	 */
	// public int score(Player jugador) {
	// Card[] hand = jugador.getHand();
	// }

	public boolean hasStraightFlush(Card[] hand){
		if (hasStraight(hand) == true && hasFlush(hand)==true)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasFlush(Card[] hand) {
		String trySuit = hand[0].suit;
		boolean result = true;
		for (int i = 1; i < hand.length; i++) {
			if (hand[i].suit != trySuit) {
				result = false;
			}
		}
		return result;
	}

	public ArrayList<Card> convertToArrayList(Card[] pile) {
		ArrayList<Card> mano = new ArrayList<Card>();
		for (Card card : pile) {
			mano.add(card);
		}
		return mano;
	}

	public int score(Card[] hand){
		int result = -1;// the score
		if(hasStraightFlush(hand)==true && contains(stripValues(hand), 12) ==true){
			return 9;
			//Royal Flush!
		}
		else if(hasStraightFlush(hand)==true){
			return 8;
			//Straight Flush.
		}
		else if(hasGroup(4,hand)==true){
			return 7;
			//Four of a Kind
		}
		else if(hasFullHouse(hand)==true){
			return 6;
			//Full House.
		}
		else if(hasFlush(hand)==true){
			return 5;
		}
		else if(hasStraight(hand)==true){
			return 4;
			//Straight
		}
		else if(hasThree(hand)==true){
			return 3;
			//Three
		}
		else if(hasTwoPair(hand)==true){
			return 2;
			//two pair.
		}
		else if(hasPair(hand) == true){
			return 1;
			//pair
		}
		else{
			return 0;
			//nothing.
		}
	}
		
	
	public boolean hasGroup(int howMany, Card[] hand) {
		int[] values = stripValues(hand);
		boolean result = false;
		for (int i = 0; i < values.length; i++) {
			int count = 0;
			for (int j = 0; j < values.length; j++) {
				if (values[j] == values[i]) {
					count += 1;
				}

			}
			if (count > howMany - 1) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public Card[] removeCard(Integer[] indexesToRemove, Card[] hand)
	{
		Card[] replacementHand = new Card[hand.length - indexesToRemove.length]; // Create new Card array of new length.
		int newArrayIndex = 0; // set starting index on the new array.
		for(Integer i =0; i<hand.length; i++){ //Iterate through each index of the old hand. 
			if(Arrays.asList(indexesToRemove).contains(i)== false){ // if the current index is not included in the list of indexes to remove:
				replacementHand[newArrayIndex]=hand[i]; //copy this index of the current hand to the current index of the replacement hand.
				newArrayIndex+=1;// shift to the next index of the replacement hand. 
			}
			
			
		}
		return replacementHand; //overwrite
	}
	
	
	public boolean hasFullHouse(Card[] hand){
		boolean result = true;
		if (hasThree(hand) == false){
			result = false;
		}
		else {
			Integer[] indexesToRemove = findGroupIndexes(3,hand);
			hand = removeCard(indexesToRemove,hand);
			result = hasGroup(2,hand);
		}
		
		return result;
	}
	
	public boolean hasTwoPair(Card[] hand){
		boolean result = true;
		if (hasPair(hand) == false){
			result = false;
		}
		else {
			Integer[] indexesToRemove = findGroupIndexes(2,hand);
			hand = removeCard(indexesToRemove,hand);
			result = hasGroup(2,hand);
		}
		
		return result;
	}
	
	/*Method returns int array conaining the indeces of a card repeated a certain number of times.
	 * 
	 */
	public Integer[] findGroupIndexes(int howMany, Card[] hand) {
		int[] values = stripValues(hand);
		Integer[] result = new Integer[howMany];
		for (int i = 0; i < values.length; i++) {
			int count = 0;
			int current = 0;
			for (int j = 0; j < values.length; j++) {
				if (values[j] == values[i]) {
					result[current] = j;
					count += 1;
					current+=1;
				}
			

			}
			if (count > howMany - 1) {
				break;
			}
		}
		return result;
	}
	
	
	public int findMinIndex(int[] list) {
		int minNum = Integer.MAX_VALUE;
		for (int num : list) {
			minNum = Math.min(num, minNum);
		}
		return findIndex(list, minNum);
	}
	
	public int findIndex(int[] list, int value){
		int result = -1; 
		for (int i = 0; i < list.length; i++) {
			if (list[i] == value) 
			{
				result = i;
			}
		}
		return result;
			
	}
	
	public boolean contains(int[] array, int key) {
		boolean result = false;
		for (int term : array) {
			if (term == key) {
				result = true;
			}
		}
		return result;
	}
	
	public boolean hasStraight(Card[] hand) {
		int[] mano = stripValues(hand);
		int start = findMinIndex(mano);
		boolean result = false;
		int i = 0;
		while (true) {
			i += 1;
			if (contains(mano, mano[start] + 1) == true) {
				start = findIndex(mano, mano[start]+1);
			} 
			else {
				break;
			}

		}
		if (i > 4) {
			result = true;
		}
	return result;
	}

	public boolean hasFour(Card[] hand) {
		return hasGroup(4, hand);
	}

	public boolean hasPair(Card[] hand) {
		return hasGroup(2, hand);
	}

	public boolean hasThree(Card[] hand) {
		return hasGroup(3, hand);
	}

	public int[] stripValues(Card[] hand) {
		int[] values = new int[hand.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = hand[i].value;
		}
		return values;
	}

}
