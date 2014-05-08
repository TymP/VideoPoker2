import java.util.Arrays;

public class Player {

	/**
	 * @param args
	 */
	public Card[] hand;
		/* Player is initialised with an array of cards, forming the instance variable hand.
	 * 
	 */
	public Player(Card[] startHand)
	{
		this.hand = startHand;
	}
	
	public Card[] getHand(){
		return hand;
	}
	/*
	 * Takes in an array of indexes to remove from the instance variable hand( type Card[]) and removes them.
	 */
	public void removeCard(Integer[] indexesToRemove)
	{
		Card[] replacementHand = new Card[hand.length - indexesToRemove.length]; // Create new Card array of new length.
		int newArrayIndex = 0; // set starting index on the new array.
		for(Integer i =0; i<hand.length; i++){ //Iterate through each index of the old hand. 
			if(Arrays.asList(indexesToRemove).contains(i)== false){ // if the current index is not included in the list of indexes to remove:
				replacementHand[newArrayIndex]=hand[i]; //copy this index of the current hand to the current index of the replacement hand.
				newArrayIndex+=1;// shift to the next index of the replacement hand. 
			}
		}
		hand = replacementHand; //overwrite
	}
	
	/*
	 * Takes in an array of cards, adds them to the instance variable hand. 
	 */
	public void addCard(Card[] cardsToAdd){
		Card[] replacementHand = new Card[hand.length + cardsToAdd.length]; // create a replacement hand of increased length.
		int replacementHandIndex = 0; //initialise the first index of the replacement hand. 
		for (Card card : hand){ //iterate through the current hand, copying each card to the new hand, and shifting the new hand index.
			replacementHand[replacementHandIndex] = card;
			replacementHandIndex+=1;
		}
		for( Card card : cardsToAdd){
			replacementHand[replacementHandIndex] = card;
			replacementHandIndex+=1;
		}
		
		this.hand = replacementHand; // Overwrite
			
	}
	
	
	
	
	
}

