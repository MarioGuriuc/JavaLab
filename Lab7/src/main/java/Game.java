import java.util.*;

public class Game
{
	private Bag bag;
	private List<Player> players;
	private List<Thread> threads;
	private int MAX_PLAYERS;
	private int maxSequenceLength;
	private Map<Player, Integer> playerScores;
	private int turn;

	public Game(int maxPlayers, String[] names)
	{
		bag = new Bag();
		players = new ArrayList<>();
		threads = new ArrayList<>();
		playerScores = new HashMap<>();
		turn = 0;
		maxSequenceLength = 0;

		MAX_PLAYERS = maxPlayers;

		for (int i = 0; i < MAX_PLAYERS; i++)
		{
			players.add(new Player(names[i], this));
			playerScores.put(players.get(i), 0);
		}
	}

	public static void main(String[] args)
	{
		String[] players = new String[]{ "Mario", "Bolfa", "Sorin" };
		Game game = new Game(players.length, players);
		game.play();
	}

	public Map<Player, Integer> getPlayerScores()
	{
		return playerScores;
	}

	public synchronized void play()
	{
		for (Player player : players)
		{
			Thread thread = new Thread(player);
			threads.add(thread);
			thread.start();
			System.out.println(player + " has started playing!");
		}

		while (!bag.getTokens().isEmpty())
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				System.exit(1);
			}
		}

		for (Player player : players)
		{
			if (player.getSequences().isEmpty())
			{
				System.out.println(player + " has no tokens!");
				continue;
			}
			player.getSequences().sort(Comparator.comparingInt(token -> (token.getFirst() + token.getSecond())));
			System.out.println(player + " has the following tokens: " + player.getSequences());
			makeSequences(player, player.getSequences());
			System.out.println(player + " has formed a sequence of length: " + getPlayerScores().get(player));
			checkAndUpdateMaxSequence(getPlayerScores().get(player));
		}

		checkWinner();

		for (Thread thread : threads)
		{
			try
			{
				thread.join();
			}
			catch (InterruptedException e)
			{
				System.exit(1);
			}
		}
	}

	public synchronized void checkAndUpdateMaxSequence(int sequenceLength)
	{
		maxSequenceLength = Math.max(maxSequenceLength, sequenceLength);
	}

	void checkWinner()
	{
		int maxScore = 0;
		for (Player player : players)
		{
			maxScore = Math.max(maxScore, playerScores.get(player));
		}
		for (Player player : players)
		{
			if (playerScores.get(player) == maxScore)
			{
				System.out.println("Winner is: " + player);
				System.out.println("Game Over!");
				System.out.println("Max sequence length achieved: " + maxSequenceLength);
				System.exit(0);
			}
		}
	}

	public Player getPlayersTurn()
	{
		return players.get(turn);
	}

	public synchronized void updatePlayersTurn()
	{
		turn = (turn + 1) % MAX_PLAYERS;
		notifyAll();
	}

	public Bag getBag()
	{
		return this.bag;
	}

	private void makeSequences(Player player, List<Token> sequences)
	{
		int sequenceLength = 1;
		for (int i = 0; i < sequences.size() - 1; i++)
		{
			if (sequences.get(i).getSecond() == sequences.get(i + 1).getFirst())
			{
				sequenceLength++;
			}
			else
			{
				playerScores.put(player, Math.max(playerScores.get(player), sequenceLength));
				sequenceLength = 1;
			}
		}
		playerScores.put(player, Math.max(playerScores.get(player), sequenceLength));
	}
}
