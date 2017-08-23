package usp.si.ocd.ep1.operations;

/**
 * Método que representa a divisão com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class DivisaoDePontosFlutuantes {

	/**
	 * Método que efetua a divisão de fato
	 * 
	 * @param a
	 *            Double 1
	 * @param b
	 *            Double 2
	 * @return Resultado da divisão já convertido para a representação decimal
	 */
	public double faz(double a, double b) {
		if (a == 0 || b == 0)
			return 0;
		if (b == 1)
			return a;

		double q = 0, p = 1;

		if (a < 0) {
			a = a * (-1);
			p = p * (-1);
		}
		if (b < 0) {
			b = b * (-1);
			p = p * (-1);
		}

		while (a < b) {
			a = a * 10;
			p = p * 10;
		}

		while (a >= b) {
			a = new SomaDePontosFlutuantes().faz(a, b * (-1));
			q++;
		}

		int e = 5;
		while (a != 0 && e > 0) {
			a = a * 10;
			p = p * 10;
			q = q * 10;

			while (a >= b) {
				a = new SomaDePontosFlutuantes().faz(a, b * (-1));
				q++;
			}
			e--;
		}

		return q / p;
	}

}