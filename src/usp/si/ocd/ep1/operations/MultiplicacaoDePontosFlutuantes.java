package usp.si.ocd.ep1.operations;

/**
 * Classe que representa a multiplica��o com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class MultiplicacaoDePontosFlutuantes {

	/**
	 * M�todo que faz de fato a mulitiplica��o
	 * 
	 * @param a
	 *            Double 1
	 * @param b
	 *            Double 2
	 * @return Resultado da opera��o j� convertido para representa��o decimal
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