package Game;

import java.util.TimerTask;

public class Task extends TimerTask {

  private Game game;

  public Task(Game game) {
    this.game = game;
  }

  @Override
  public void run() {
    System.out.println("Me estoy ejecutando prro");
  }

}
