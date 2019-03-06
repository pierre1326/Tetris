package Game;

public class Util {

  public static float lerp(float xI, float xF, float dX) {
    return xI + ((xF - xI) * dX);
  }

  public static int[][] rotateMatrix(int[][] matrix, int rows, int columns) {
    int[][] newMatrix = new int[columns][rows];
    for(int i = 0; i < columns; i++) {
      for(int j = 0; j < rows; j++) {
        newMatrix[i][j] = matrix[j][i];
      }
    }
    return newMatrix;
  }
}
