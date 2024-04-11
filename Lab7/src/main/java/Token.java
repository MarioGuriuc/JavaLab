import java.util.Objects;

public class Token
{
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Token token1 = (Token) o;
		return Objects.equals(token, token1.token);
	}

	private final Pair<Integer> token;

	public Token(int first, int second)
	{
		token = new Pair<>(first, second);
	}

	@Override
	public String toString()
	{
		return "[" + token.getFirst() + ", " + token.getSecond() + "]";
	}

	public int getSecond()
	{
		return token.getSecond();
	}

	public int getFirst()
	{
		return token.getFirst();
	}
}
