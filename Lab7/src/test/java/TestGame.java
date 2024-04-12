import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestGame
{
	public static void main(String[] args)
	{
		int nrOfGames = 100;
		String name = "Mario";

		List<Game> games = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < nrOfGames; i++)
		{
			int maxPlayers = random.nextInt(50);
			String[] names = new String[maxPlayers];
			Arrays.fill(names, name + i);
			games.add(new Game(maxPlayers, names));
			games.get(i).play();
		}
	}
}
