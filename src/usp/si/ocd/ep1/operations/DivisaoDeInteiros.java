package usp.si.ocd.ep1.operations;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa uma divis�o com n�meros inteiros
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class DivisaoDeInteiros extends OperacaoCom<Inteiro> {

	private static boolean inverterDividendo;
	private static boolean inverterDivisor;

	/**
	 * M�todo privado que efetua a divis�o de fato
	 * 
	 * @param dividendo
	 *            Dividendo
	 * @param divisor
	 *            Divisor
	 * @param resposta
	 *            Resposta atual
	 * @param qtd
	 *            N�mero de vezes que esse m�todo j� foi chamado
	 * @return Resultado da divis�o
	 * @throws OverflowException
	 *             Caso ocorra overflow na opera��o
	 */
	private Inteiro faz(Inteiro dividendo, Inteiro divisor, Inteiro resposta, int qtd) throws OverflowException {
		if (qtd == 0) {
			if (dividendo.ehNegativo())
				inverterDividendo = true;
			else
				inverterDividendo = false;

			if (divisor.ehNegativo())
				inverterDivisor = true;
			else
				inverterDivisor = false;
		}

		if (inverterDividendo && qtd == 0)
			dividendo.copia(dividendo.seuComplementoDe2());

		if (inverterDivisor && qtd == 0)
			divisor.copia(divisor.seuComplementoDe2());

		if (dividendo.ehMenorQue(divisor))
			if ((inverterDividendo && inverterDivisor) || (!inverterDividendo && !inverterDivisor))
				return resposta;
			else
				return resposta.seuComplementoDe2();

		Inteiro um = new Inteiro(dividendo.getNumeroBits());
		um.valor(1);

		Inteiro novoDividendo = new Inteiro(dividendo.getNumeroBits());
		novoDividendo.copia(new SubtracaoDeInteiros().faz(dividendo, divisor));

		resposta.copia(new SomaDeInteiros().faz(resposta, um));

		return faz(novoDividendo, divisor, resposta, ++qtd);
	}

	@Override
	public Inteiro faz(Inteiro dividendo, Inteiro divisor) throws ArithmeticException, OverflowException {
		Inteiro zero = new Inteiro(divisor.getNumeroBits());

		if (divisor.ehIgual(zero))
			throw new ArithmeticException("Imposs�vel dividir por zero!");

		return faz(dividendo, divisor, zero, 0);
	}

}