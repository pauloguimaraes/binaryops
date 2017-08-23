package usp.si.ocd.ep1.operations;

import usp.si.ocd.ep1.Binario;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe abstrata que representa uma operação com binário
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 *
 * @param <T>
 *            Tipo de binário
 */
public abstract class OperacaoCom<T extends Binario> {

	// Flags para a soma de bits
	public static final int ZERO_RESTO_ZERO = 0;
	public static final int UM_RESTO_ZERO = 1;
	public static final int ZERO_RESTO_UM = 2;

	/**
	 * Soma dois bits
	 * 
	 * @param b1
	 *            Bit 1
	 * @param b2
	 *            Bit 2
	 * @return Flag de soma de bits
	 */
	public int somaBooleana(boolean b1, boolean b2) {
		if (!b1 && !b2)
			return ZERO_RESTO_ZERO;
		else if (!b1 || !b2)
			return UM_RESTO_ZERO;
		else
			return ZERO_RESTO_UM;
	}

	/**
	 * Faz determinada operação com binário
	 * 
	 * @param binario1
	 *            Binário 1
	 * @param binario2
	 *            Binário 2
	 * @return Resultado da operação com ambos os binários
	 * @throws ArithmeticException
	 *             Exceção de divisão por zero (única observada)
	 * @throws OverflowException
	 *             Overflows na inicialização ou operação com binários
	 */
	public abstract T faz(T binario1, T binario2) throws ArithmeticException, OverflowException;

}