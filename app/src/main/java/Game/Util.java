package Game;

import java.util.ArrayList;

public class Util {

  public static float lerp(float xI, float xF, float dX) {
    return xI + ((xF - xI) * dX);
  }

  public static Object[][] rotateMatrixLeft(Object[][] matrix, int rows, int columns) {
    Object[][] newMatrix = new Object[columns][rows];
    for(int j = 0; j < columns; j++) {
      for(int i = 0; i < rows; i++) {
        newMatrix[columns - j][i] = matrix[i][j];
      }
    }
    return newMatrix;
  }

  public static int numberRandom(int n) {
    int number = (int) (Math.random() * n) + 1;
    return number;
  }

  public static int checkIndex(int[] index, ArrayList<int[]> indexSquares) {
    for(int i = 0; i < indexSquares.size(); i++) {
      int[] indexSquare = indexSquares.get(i);
      if(index[0] == indexSquare[0] && index[1] == indexSquare[1]) {
        return i;
      }
    }
    return -1;
  }

}
