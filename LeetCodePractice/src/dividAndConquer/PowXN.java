package dividAndConquer;

public class PowXN {
	public double pow(double x, int n) {
		if (x == 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}

		double half = pow(x, n / 2);
		 // n is even , then x^n=x^n/2*x^n/2
		if (n % 2 == 0) {
			return half * half;
		} else if (n > 0) {
		//  n>0 and n is odd, x^n=x^n/2 * x^n/2 *x
			return half * half * x;
		} else {
	    // n<0 and n is odd, x^n=x^n/2 *x^n/2 *x^-1
			return half * half / x;
		}
	}
}
