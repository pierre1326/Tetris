package Game;

public class Util {

  public static float lerp(float xI, float xF, float dX) {
    return xI + ((xF - xI) * dX);
  }

  public static Object[][] rotateMatrixRight(Object[][] matrix, int rows, int columns) {
    Object[][] newMatrix = new Object[columns][rows];
    for(int j = 0; j < columns; j++) {
      for(int i = 0; i < rows; i++) {
        newMatrix[j][rows - i] = matrix[i][j];
      }
    }
    return newMatrix;
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
}
