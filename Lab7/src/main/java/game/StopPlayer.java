package game;

public class StopPlayer implements Runnable {
    private final Player player;

    public StopPlayer(Player player) {
        this.player = player;
    }

    @Override
    public synchronized void run() {
        player.stopPlayer();
    }
}
