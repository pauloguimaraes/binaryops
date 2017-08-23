package usp.si.ocd.ep1.operations;

/**
 * Classe que representa uma subtração com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class SubtracaoDePontosFlutuantes {

	/**
	 * Efetua a soma com o negativo do segundo
	 * 
	 * @param x
	 *            Double 1
	 * @param y
	 *            Double 2
	 * @return Resultado reconvertido para double
	 */
	public double faz(double x, double y) {
		return new SomaDePontosFlutuantes().faz(x, -y);
	}

}