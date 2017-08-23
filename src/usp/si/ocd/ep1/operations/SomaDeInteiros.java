package usp.si.ocd.ep1.operations;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa uma soma com números inteiros
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class SomaDeInteiros extends OperacaoCom<Inteiro> {

	private Inteiro carry, resposta;
	private static int overflows = 0;

	/**
	 * Construtor padrão
	 */
	public SomaDeInteiros() {
		overflows = 0;
	}

	/**
	 * Método privado que faz a soma, indicando quantas vezes a operação já foi
	 * chamada (apenas a primeira vez deve iniciar a carry e a resposta)
	 * 
	 * @param inteiro1
	 *            Inteiro 1 a ser somado
	 * @param inteiro2
	 *            Inteiro 2 a ser somado
	 * @param qtd
	 *            Número de vezes que essa operação já foi chamada
	 * @return Resultado da soma
	 * @throws OverflowException
	 *             Caso ocorra overflow na soma
	 */
	private Inteiro faz(Inteiro inteiro1, Inteiro inteiro2, int qtd) throws OverflowException {
		if (qtd == 0)
			inicializaValores(inteiro1);

		for (int i = inteiro1.getNumeroBits() - 1; i >= 0; i--)
			atualizaValores(inteiro1, inteiro2, i);

		somaCarryAResposta(carry, resposta, qtd);

		if (houveOverflow())
			throw new ArithmeticException("Houve overflow! Número insuficiente de bits operados!");

		return resposta;
	}

	/**
	 * @return Se houve ou não overflow na soma
	 */
	private boolean houveOverflow() {
		return overflows >= 2;
	}

	/**
	 * Método que soma a carry atual com a resposta atual
	 *
	 * @param carry
	 *            Carry atual
	 * @param resposta
	 *            Resposta atual
	 * @param qtd
	 *            Número de vezes que essa operação já foi chamada
	 * @throws OverflowException
	 *             Caso ocorra overflow nessa soma
	 */
	private void somaCarryAResposta(Inteiro carry, Inteiro resposta, int qtd) throws OverflowException {
		Inteiro copia = new Inteiro(carry.getNumeroBits());
		copia.copia(carry);
		if (!queryEstaVazia())
			faz(resposta, copia, ++qtd);
	}

	/**
	 * Atualiza os valores da carry e da resposta
	 * 
	 * @param Inteiro1
	 *            Inteiro 1 da soma
	 * @param Inteiro2
	 *            Inteiro 2 da soma
	 * @param i
	 *            Índice que está sendo somado
	 * @throws OverflowException
	 *             Caso ocorra overflow na atualização
	 */
	private void atualizaValores(Inteiro Inteiro1, Inteiro Inteiro2, int i) throws OverflowException {
		int soma = somaBooleana(Inteiro1.bit(i), Inteiro2.bit(i));

		if (soma == ZERO_RESTO_UM && (i - 1) >= 0)
			carry.atualiza(i - 1, true);
		else if (i - 1 != -1)
			carry.atualiza(i - 1, false);

		if (i - 1 == -1 && soma == ZERO_RESTO_UM)
			overflows++;

		resposta.atualiza(i, (soma == ZERO_RESTO_ZERO) || (soma == ZERO_RESTO_UM) ? false : true);
	}

	/**
	 * Inicializa a carry e a resposta
	 * 
	 * @param inteiro
	 *            Inteiro que servirá como base para que se saiba com quantos
	 *            bits eles serão inciados
	 * @throws OverflowException
	 *             Caso ocorra overflow na inicialização
	 */
	private void inicializaValores(Inteiro inteiro) throws OverflowException {
		carry = new Inteiro(inteiro.getNumeroBits());
		resposta = new Inteiro(inteiro.getNumeroBits());
	}

	/**
	 * @return Se a carry está vazia atualmente
	 */
	private boolean queryEstaVazia() {
		for (boolean b : carry.valor())
			if (b)
				return false;
		return true;
	}

	@Override
	public Inteiro faz(Inteiro inteiro1, Inteiro inteiro2) throws OverflowException {
		return faz(inteiro1, inteiro2, 0);
	}

}