package Game;

import java.util.ArrayList;
import java.util.TimerTask;

public class Task extends TimerTask {

  private Game game;

  public Task(Game game) {
    this.game = game;
  }

  @Override
  public void run() {
    ArrayList<int[]> indexSquares = game.obtainIndex();
    Square[][] squares = game.obtainMatrix();
    updateFigureActive(indexSquares, squares);
  }

  private Square[][] updateFigureActive(ArrayList<int[]> indexSquares, Square[][] squares) {
    boolean move = true;
    for(int i = 0; i < indexSquares.size(); i++) {
      int[] index = indexSquares.get(i);
      int[] indexCheck = {index[0] + 1, index[1]};
      if(index[0] == squares.length - 1 || squares[index[0] + 1][index[1]] != null && checkIndex(indexCheck, indexSquares) == -1) {
        move = false;
      }
    }
    if(move) {
      if(game.isActualInsert()) {
        game.updateActualInsert();
        game.updateMatrix(squares);
      }
      else {
        for(int i = squares.length - 2; i > -1; i--) {
          for(int j = 0; j < squares[i].length; j++) {
            int[] index = {i, j};
            int actualIndex = checkIndex(index, indexSquares);
            if(actualIndex >= 0 && squares[i + 1][j] == null) {
              squares[i + 1][j] = squares[i][j];
              squares[i][j] = null;
              indexSquares.remove(actualIndex);
              index[0] = index[0] + 1;
              indexSquares.add(index);
            }
          }
        }
        game.updateIndex(indexSquares);
        game.updateMatrix(squares);
      }
    }
    else {
      game.createFigure();
    }
    return squares;
  }

  private int checkIndex(int[] index, ArrayList<int[]> indexSquares) {
    for(int i = 0; i < indexSquares.size(); i++) {
      int[] indexSquare = indexSquares.get(i);
      if(index[0] == indexSquare[0] && index[1] == indexSquare[1]) {
        return i;
      }
    }
    return -1;
  }

}
