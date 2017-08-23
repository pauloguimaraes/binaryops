package usp.si.ocd.ep1.operations;

/**
 * Classe que representa a multiplicação com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class MultiplicacaoDePontosFlutuantes {

	/**
	 * Método que faz de fato a mulitiplicação
	 * 
	 * @param a
	 *            Double 1
	 * @param b
	 *            Double 2
	 * @return Resultado da operação já convertido para representação decimal
	 */
	public double faz(double a, double b) {
		if (a == 0)
			return 0;
		if (b == 0)
			return 0;

		int bi = (int) b, m = 1;
		double mult = a;
		while (b - bi != 0) {
			b = b * 10;
			bi = (int) b;
			m = m * 10;
		}

		while (bi > 1) {
			a = new SomaDePontosFlutuantes().faz(a, mult);
			bi--;
		}

		return a / m;
	}

}