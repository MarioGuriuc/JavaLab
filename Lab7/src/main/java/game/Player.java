package game;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable
{
    private final String name;
    private final Game game;
    private final List<Token> tokens;
    private boolean running;

    public Player(String name, Game game)
    {
        this.name = name;
        this.game = game;
        tokens = new ArrayList<>();
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
                stopPlayer();
            }

            while (game.getPlayersTurn() != this)
            {
                try
                {
                    game.wait();
                } catch (InterruptedException e)
                {
                    System.exit(1);
                }
            }
            game.extractToken(this);
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                System.exit(1);
            }
        }
    }

    public List<Token> getTokens()
    {
        return tokens;
    }

    public void stopPlayer()
    {
        synchronized (game)
        {
            running = false;
            game.notifyAll();
        }
    }

    @Override
    public String toString()
    {
        return name;
    }
}
