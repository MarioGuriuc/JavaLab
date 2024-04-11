import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable
{
	private final String name;
	private final Game game;
	private final List<Token> sequences;
	private boolean running;

	public Player(String name, Game game)
	{
		this.name = name;
		this.game = game;
		sequences = new ArrayList<>();
		running = true;
	}

	@Override
	public synchronized void run()
	{
		while (running)
		{
			this.pickToken();
		}
	}

	public void pickToken()
	{
		synchronized (game)
		{
			if (game.getBag().getTokens().isEmpty())
			{
				running = false;
				game.notifyAll();
				return;
			}

			Bag bag = game.getBag();

			while (game.getPlayersTurn() != this)
			{
				try
				{
					//System.out.println(this.name + " is waiting for their turn.");
					game.wait();
				}
				catch (InterruptedException e)
				{
					System.exit(1);
				}
			}
			//System.out.println(name + " is picking a token.");
			Token token = bag.extractToken();
			if (token != null)
			{
				sequences.add(token);
				game.updatePlayersTurn();
			}
			//System.out.println(name + " has picked token: " + token);
		}
	}

	public List<Token> getSequences()
	{
		return sequences;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
