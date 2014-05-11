public class Card {

	public String suit;
	public int value;
	
	/*Card initialises with a suit and a value.
	 * 
	 */
	public Card(String suit, int value){
		this.suit = suit;
		this.value = value;
	}

	public String getSuit() 
	{
		return suit;

	}

	public int getValue()
	{
		return value; 
	}
	
	public void setSuit(String suitChoice)
	{
		this.suit = suitChoice;
	}
	
	public void setValue(int valueChoice)
	{
		this.value=valueChoice;
	}
}
