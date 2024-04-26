package timekeeper;

import game.Game;

public class TimeKeeper extends Thread
{
    private final Game game;
    private final int timeLimit;

    public TimeKeeper(Game game, int timeLimit)
    {
        this.game = game;
        this.timeLimit = timeLimit;
    }

    @Override
    public synchronized void run()
    {
        int counter = 0;
        while (counter < timeLimit)
        {
            try
            {
                System.out.println("Time left: " + (timeLimit - counter++) + " seconds");
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                System.exit(1);
            }
        }
        System.out.println("Time's up!");
        game.stopGame();
        System.exit(0);
    }
}
