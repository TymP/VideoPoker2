
public class Game {

	/**
	 * @param args
	 */
	public void play(){
		//Initialise user interface and judge.
		UI ui = new UI();
		Judge judge = new Judge();
		System.out.println("Welcome to Video Poker!" + '\r');
		while (true){
			Deck deck = new Deck();
			System.out.println("Shuffling the deck.");
			try{
				Thread.sleep(2000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			Player player = new Player(deck.deal(5));
			ui.displayHand(player);
			Integer[] exchangeArray = ui.choose();
			player.removeCard(exchangeArray);
			player.addCard(deck.deal(exchangeArray.length));
			ui.displayHand(player);
			ui.interpretScore(judge.score(player.getHand()));
			if(ui.stop() ==true){
				System.out.println("Thanks for playing.");
				break;
			}
		}
		
		

	}

}
