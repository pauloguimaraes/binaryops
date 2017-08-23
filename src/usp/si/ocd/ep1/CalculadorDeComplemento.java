package usp.si.ocd.ep1;

import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Interface que garante a implementação do cálculo de complemento
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 * 
 * @param <T>
 *            Um tipo de binario (inteiro ou ponto flutuante)
 */
public interface CalculadorDeComplemento<T extends Binario> {

	/**
	 * @return Complemento de 2 de binário
	 * @throws OverflowException
	 *             Caso não possua bits necessários para a operação
	 */
	public T seuComplementoDe2() throws OverflowException;

}