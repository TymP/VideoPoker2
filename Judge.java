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
	
	/*
	 * Evaluates a Card[] input, checking the score of the hand in descending order.
	 */
	public int score(Card[] hand){
		if(hasStraightFlush(hand)==true && contains(stripValues(hand), 12) ==true){
			return 9;
			//Royal Flush!
		}
		else if(hasStraightFlush(hand)){
			return 8;
			//Straight Flush.
		}
		else if(hasGroup(4,hand)){
			return 7;
			//Four of a Kind
		}
		else if(hasFullHouse(hand)){
			return 6;
			//Full House.
		}
		else if(hasFlush(hand)){
			return 5;
		}
		else if(hasStraight(hand)){
			return 4;
			//Straight
		}
		else if(hasThree(hand)){
			return 3;
			//Three
		}
		else if(hasTwoPair(hand)){
			return 2;
			//two pair.
		}
		else if(hasPair(hand)){
			return 1;
			//pair
		}
		else{
			return 0;
			//nothing.
		}
	}
		
	/*
	 * Takes an integer of the size of the group to look for, and a Card[] array. Returns true if this size of a group of cards with the same value exists.
	 */
	public boolean hasGroup(int howMany, Card[] hand) {
		int[] values = stripValues(hand); //Creates array of the cards' values.
		boolean result = false;
		for (int i = 0; i < values.length; i++) {
			int count = 0;
			for (int j = 0; j < values.length; j++) {
				if (values[j] == values[i]) {
					count += 1; //Count same cards.
				}
			}
			if (count > howMany - 1) {
				result = true;
				break;
			}
		}
		return result;
	}
	/*
	 * Takes in an array of indexes to remove from the instance variable hand( type Card[]) and removes them.
	 */
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
		//Check if the hand has three of a kind.
		if (hasThree(hand) == false){
			result = false;
		}
		else { //If it does, remove the three of a kind and check for a pair in the remaining two cards.
			Integer[] indexesToRemove = findGroupIndexes(3,hand);
			hand = removeCard(indexesToRemove,hand);
			result = hasGroup(2,hand);
		}
		
		return result;
	}
	
	public boolean hasTwoPair(Card[] hand){
		boolean result = true;
		//Check if the hand has a pair.
		if (hasPair(hand) == false){
			result = false;
		}
		else { //If it does, remove the pair.
			Integer[] indexesToRemove = findGroupIndexes(2,hand);
			hand = removeCard(indexesToRemove,hand);
			//check for another pair.
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
	
	/*
	 * Returns the index of the minimum value in an int[] array.
	 */
	public int findMinIndex(int[] list) {
		int minNum = Integer.MAX_VALUE;
		for (int num : list) {
			minNum = Math.min(num, minNum);
		}
		return findIndex(list, minNum);
	}
	
	/*
	 * Returns the index of the integer given as input in the int[] array given as input.
	 */
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
	
	/*
	 * Returns true if the input int array contains the input int.
	 */
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
		int[] mano = stripValues(hand); //Create int array of card values.
		int start = findMinIndex(mano); //Find the index of the minimum value of the array, start there.
		boolean result = false;
		int i = 0;
		while (true) {
			i += 1;
			if (contains(mano, mano[start] + 1) == true) { //if the next value up is in the hand
				start = findIndex(mano, mano[start]+1); //
			} 
			else { //if the hand does not contain the next value from the current one, break the loop.
				break;
			}

		}
		if (i > hand.length-1) { //if we incremented through all the indexes of the hand, we have a straight.
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
	/*
	 * Takes in an array of cards, returns an int array of their values.
	 */
	public int[] stripValues(Card[] hand) {
		int[] values = new int[hand.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = hand[i].value;
		}
		return values;
	}

}
