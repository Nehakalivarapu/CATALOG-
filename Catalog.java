import java.math.BigInteger;

public class Catalog {
    public static void main(String[] args) {
        // Example points (x, y) where x = 1, 2, 3, ... and y are the decimal values of the corresponding bases
        BigInteger[][] points = {
            {BigInteger.valueOf(1), new BigInteger("420020006424065463", 7)},  // Base 7 to decimal
            {BigInteger.valueOf(2), new BigInteger("10511630252064643035", 7)}, // Base 7 to decimal
            {BigInteger.valueOf(3), new BigInteger("101010101001100101011100000001000111010010111101100100010", 2)}, // Base 2 to decimal
            {BigInteger.valueOf(4), new BigInteger("31261003022226126015", 8)}, // Base 8 to decimal
            {BigInteger.valueOf(5), new BigInteger("2564201006101516132035", 7)}, // Base 7 to decimal
            {BigInteger.valueOf(6), new BigInteger("a3c97ed550c69484", 15)}, // Base 15 to decimal
            {BigInteger.valueOf(7), new BigInteger("134b08c8739552a734", 13)} // Base 13 to decimal
        };
        int[][] points1 = {{1, 4}, {2, 7}, {3, 12}};

        // Perform Lagrange interpolation at x = 0 to find the constant term
        BigInteger constantTerm = lagrangeInterpolation(points, BigInteger.ZERO);
        System.out.println("The constant term is: " + constantTerm);
        double constantTerm1 = lagrangeInterpolation(points1, 0);
        System.out.println("The constant term is: " + constantTerm1);
    }

    public static BigInteger lagrangeInterpolation(BigInteger[][] points, BigInteger x) {
        BigInteger result = BigInteger.ZERO;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            BigInteger term = points[i][1]; // y_i
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term = term.multiply(x.subtract(points[j][0])).divide(points[i][0].subtract(points[j][0]));
                }
            }
            result = result.add(term);
        }

        return result;
    }
    public static double lagrangeInterpolation(int[][] points, int x) {
        double result = 0;

        for (int i = 0; i < points.length; i++) {
            double term = points[i][1]; // y_i
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    term = term * (x - points[j][0]) / (points[i][0] - points[j][0]);
                }
            }
            result += term;
        }

        return result;
    }
}
