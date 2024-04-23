package game;

import timekeeper.TimeKeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Bag bag;
    private final List<Player> players;
    private final List<Thread> threads;
    private final int MAX_PLAYERS;
    private final Map<Player, Integer> playerScores;
    TimeKeeper timeKeeper;
    List<Token> highestSequence;
    private int turn;

    public Game(int maxPlayers, String[] names, int timeLimit) {
        timeKeeper = new TimeKeeper(this, timeLimit); //timeLimit in seconds
        timeKeeper.setDaemon(true);
        timeKeeper.start();
        bag = new Bag();
        players = new ArrayList<>();
        threads = new ArrayList<>();
        playerScores = new HashMap<>();
        highestSequence = new ArrayList<>();
        turn = 0;

        MAX_PLAYERS = maxPlayers;

        for (int i = 0; i < MAX_PLAYERS; i++) {
            players.add(new Player(names[i], this));
            playerScores.put(players.get(i), 0);
        }
    }

    public static void main(String[] args) {
        String[] players = new String[]{"Mario", "Bolfa", "Sorin"};
        Game game = new Game(players.length, players, 1);
        game.play();
    }

    public synchronized void stopGame() {
        makeSequences();
        for (Player player : players) {
            System.out.println(player + " managed to get the tokens: " + player.getTokens());
            System.out.println(player + " has formed a sequence of length: " + getPlayerScores().get(player));
            new Thread(new StopPlayer(player)).start();
        }
        checkWinner();
    }

    public Map<Player, Integer> getPlayerScores() {
        return playerScores;
    }

    public synchronized void play() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
            System.out.println(player + " has started playing!");
        }

        while (!bag.getTokens().isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }

        checkWinner();

        joinThreads();
    }

    private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }

    public synchronized void extractToken(Player player) {
        Token token = bag.extractToken();
        if (token != null) {
            player.getTokens().add(token);
            updatePlayersTurn();
            System.out.println(player + " picked the token: " + token);
            System.out.println();
        }
        notifyAll();
    }

    void checkWinner() {
        makeSequences();
        for (Player player : players) {
            if (player.getTokens().isEmpty()) {
                System.out.println(player + " has no tokens!");
                continue;
            }
            System.out.println(player + " has the following tokens: " + player.getTokens());
            System.out.println(player + " has formed a sequence of length: " + getPlayerScores().get(player));
        }

        int maxScore = 0;
        boolean draw = (playerScores.values().stream().distinct().count() == 1);

        if (draw) {
            System.out.println("It's a draw!");
            System.out.println("Game Over!");
            System.exit(0);
        } else {
            for (Player player : players) {
                maxScore = Math.max(maxScore, playerScores.get(player));
            }
        }

        for (Player player : players) {
            if (playerScores.get(player) == maxScore) {
                System.out.println();
                System.out.println("Game Over!");
                System.out.println("Max sequence length achieved: " + maxScore);
                System.out.println("The highest sequence is: " + highestSequence);
                System.out.println("Winner is: " + player);
                System.exit(0);
            }
        }
    }

    public Player getPlayersTurn() {
        return players.get(turn);
    }

    public synchronized void updatePlayersTurn() {
        turn = (turn + 1) % MAX_PLAYERS;
        notifyAll();
    }

    public Bag getBag() {
        return this.bag;
    }

    void makeSequences() {
        for (Player player : players) {
            List<Token> tokens = player.getTokens();
            List<Token> currentSequence = new ArrayList<>();
            boolean[] visited = new boolean[tokens.size()];

            for (int i = 0; i < tokens.size(); i++) {
                backtrack(tokens, i, currentSequence, visited, player);
            }
        }
    }

    private static boolean isClosedSequence(List<Token> currentSequence) {
        return currentSequence.get(0).getFirst() == currentSequence.get(currentSequence.size() - 1).getSecond();
    }

    private void backtrack(List<Token> tokens, int currentIndex, List<Token> currentSequence, boolean[] visited, Player player) {
        currentSequence.add(tokens.get(currentIndex));
        visited[currentIndex] = true;

        if (currentSequence.size() > 1 && isClosedSequence(currentSequence)) {
            playerScores.put(player, Math.max(playerScores.get(player), currentSequence.size() - 1));
            if (currentSequence.size() - 1 > highestSequence.size()) {
                highestSequence = new ArrayList<>(currentSequence);
            }
        }

        for (int i = 0; i < tokens.size(); i++) {
            if (!visited[i] && tokens.get(currentIndex).getSecond() == tokens.get(i).getFirst()) {
                backtrack(tokens, i, currentSequence, visited, player);
            }
        }

        currentSequence.remove(currentSequence.size() - 1);
        visited[currentIndex] = false;
    }
}
