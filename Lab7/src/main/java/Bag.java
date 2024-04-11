import java.util.*;

public class Bag
{
	private final List<Token> tokens = new ArrayList<>();
	private final int TOKENS = 5;

	public Bag()
	{
		for (int i = 0; i <= TOKENS; i++)
		{
			for (int j = 0; j <= TOKENS; j++)
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
		return tokens.remove(0);
	}

	public List<Token> getTokens()
	{
		return tokens;
	}
}
