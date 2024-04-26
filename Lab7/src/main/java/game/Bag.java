package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag
{
    private final List<Token> tokens;
    private final int TOKENS = 7;
    private final Random random;

    public Bag()
    {
        random = new Random();
        tokens = new ArrayList<>();
        for (int i = 1; i <= TOKENS; i++)
        {
            for (int j = 1; j <= TOKENS; j++)
            {
                if (i != j)
                {
                    tokens.add(new Token(i, j));
                }
            }
        }

        Collections.shuffle(tokens);
    }

    public synchronized Token extractToken()
    {
        if (tokens.isEmpty())
        {
            return null;
        }

        int index = random.nextInt(tokens.size());

        return tokens.remove(index);
    }

    public List<Token> getTokens()
    {
        return tokens;
    }

    public int getMaxTokens()
    {
        return TOKENS;
    }
}
