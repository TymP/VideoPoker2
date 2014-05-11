
public class Game {

	public void play()
	{
		//Initialise user interface and judge.
		UI ui = new UI();
		Judge judge = new Judge();
		System.out.println("Welcome to Video Poker!" + '\r');
		while (true){ //Game loop.
			Deck deck = new Deck();//Initialise deck, shuffles in constructor.
			System.out.println("Shuffling the deck.");
			try{ // Shuffle time.
				Thread.sleep(1100);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			Player player = new Player(deck.deal(5));//Initialise player witha  new hand dealt from the deck.
			ui.displayHand(player);
			Integer[] exchangeArray = ui.choose();//Define indexes player wishes to exchange.
			player.removeCard(exchangeArray);//remove them. 
			player.addCard(deck.deal(exchangeArray.length));//deal cards to replace removed indexes
			ui.displayHand(player);
			ui.interpretScore(judge.score(player.getHand()));
			if(ui.stop() ==true){ //Stop condition.
				System.out.println("Thanks for playing.");
				break;
			}
		}
	}
}
