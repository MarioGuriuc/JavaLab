package timekeeper;

import game.Game;

public class TimeKeeper extends Thread
{
	private final Game game;
	private final int timeLimit;

	public TimeKeeper(Game game, int timeLimit)
	{
		this.game = game;
		this.timeLimit = timeLimit * 1000;
	}

	@Override
	public synchronized void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(timeLimit);
			}
			catch (InterruptedException e)
			{
				System.exit(1);
			}
			System.out.println("Time's up!");
			game.stopGame();
			System.exit(0);
		}
	}
}
