import Jama.Matrix;

public class MatrixSolver {
	// String input = "WARIO ONE TRUE GOD";
	public static void main(String[] args) {
		// Define values within encoding matrix
		// double[][] encodingMatrixVals = {{-1, 6, 15}, {-4, -7, -12}, {11, 6, 4}};
		double[][] encodingMatrixVals = new double[][]{{-1, 1, 0}, {1, 3, 2}, {-3, -2, -3}};

		String[] inputArray = takeEncodedMatrixInput();

		// Make encoding matrix from array of values
		Matrix encodingMatrix = new Matrix(encodingMatrixVals);

		Matrix encodedMatrix = makeInputMatrixFromArray(inputArray);

		// Decode values by multiplying by inverse of encodeing matrix
		Matrix decodedMatrix = encodedMatrix.times(encodingMatrix.inverse());

		String decodedMessage = decodeMessageFromMatrix(decodedMatrix);

		System.out.println(decodedMessage);
	}

	private static String getCharForNumber(int i) {
		if (i == 0) {
			return " ";
		} else {
			return i > 0 && i < 27 ? String.valueOf((char) (i + 'A' - 1)) : null;
		}

	}

	private static Matrix makeInputMatrixFromArray(String[] inputArray) {
		// Make 2D array to hold values from user input
		double[][] encodedMatrixVals = new double[inputArray.length / 3][3];

		// Loop over all user values and add them to 2D array
		int i = 0;
		for (int row = 0; row < encodedMatrixVals.length; row++) {
			for (int col = 0; col < encodedMatrixVals[row].length; col++) {
				encodedMatrixVals[row][col] = Double.parseDouble(inputArray[i]);
				i++;
			}
		}

		// Make matrix from user values
		Matrix inputMatrix = new Matrix(encodedMatrixVals);

		return inputMatrix;
	}

	private static String decodeMessageFromMatrix(Matrix decodedMatrix) {
		// Get the array from the decoded values matrix
		double[][] decodedMatrixVals = decodedMatrix.getArray();

		// Define empty string for decoded message
		String decodedMessage = "";

		// Loop over all values in decoded array and translate them to letters
		for (double[] d : decodedMatrixVals) {
			for (double o : d) {
				decodedMessage += getCharForNumber((int) Math.round(o));
			}
		}

		return decodedMessage;
	}

	private static String[] takeEncodedMatrixInput() {
		// String input = "146,171,279,-21,-50,-92,-42,97,270,-17,39,114,204,144,151,94,135,233,-19,114,285";
		String input = "8,52,30,-64,-32,-58,1,3,2,-32,36,-6,-35,25,-12,-27,46,3";

		// Take actual user input here

		String[] inputArray = input.split("[,][ ]*");
		return inputArray;
	}
}
