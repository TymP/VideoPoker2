
public class Pile {

	public Card[] cards;
	/**
	 * @param args
	 */
	public Pile(int size){
		this.cards = new Card[size];
	}
	
	public void addCardstoTop(Card[] cardsToAdd){
		int oldLength = cards.length;
		Card[] newCardsArray = new Card[oldLength+cardsToAdd.length];
		
		int newCardsArrayIndex = 0;
		
		//Add the new cards to the new array
		for (Card card : cardsToAdd)
		{
			newCardsArray[newCardsArrayIndex] = card;
			newCardsArrayIndex+=1;
		}
		
		//copy the old cards to the new array.
		
		for(Card card : this.cards)
		{
			newCardsArray[newCardsArrayIndex] = card;
			newCardsArrayIndex +=1;
		}
		
		this.cards = newCardsArray;
	

	}

}
