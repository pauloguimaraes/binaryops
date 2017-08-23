package usp.si.ocd.ep1;

import usp.si.ocd.ep1.exceptions.OverflowException;
import usp.si.ocd.ep1.operations.SomaDeInteiros;

/**
 * Classe que representa um número inteiro
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class Inteiro extends Binario implements CalculadorDeComplemento<Inteiro> {

	/**
	 * Construtor que inicia esse inteiro
	 * 
	 * @param numeroDeBits
	 *            Número de bits que esse inteiro ocupa
	 * @throws OverflowException
	 *             Caso ocorra overflow na inicialização
	 */
	public Inteiro(int numeroDeBits) throws OverflowException {
		super(numeroDeBits);
	}

	/**
	 * Construtor que inicia esse inteiro
	 * 
	 * @param numeroDeBits
	 *            Número de bits que esse inteiro ocupa
	 * @param sinal
	 *            Se esse inteiro é positivo ou negativo
	 * @throws OverflowException
	 *             Caso ocorra overflow na inicialização
	 */
	public Inteiro(int numeroDeBits, boolean sinal) throws OverflowException {
		super(numeroDeBits, sinal);
	}

	/**
	 * Define o valor desse binário
	 * 
	 * @param numeroBinario
	 *            Representação decimal deste binário
	 * @throws OverflowException
	 *             Caso ocorra overflow na inicialização
	 */
	public void valor(int numeroBinario) throws OverflowException {
		boolean ehNegativo = false;

		if (numeroBinario < 0) {
			ehNegativo = true;
			numeroBinario = Math.abs(numeroBinario);
		}

		int posicaoLivre = getNumeroBits() - 1;
		while (numeroBinario > 0) {
			atualiza(posicaoLivre, (numeroBinario % 2 == 0) ? false : true);
			numeroBinario /= 2;
			posicaoLivre--;
		}

		if (ehNegativo) {
			Inteiro aux = new Inteiro(getNumeroBits(), NEGATIVO);
			aux.copia(seuComplementoDe2());

			this.copia(aux);
		}
	}

	/**
	 * Duplica o número de bits que o binário ocupa colocando 0s à direita
	 * (altera o valor do binário)
	 * 
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public void duplicaBitsParaDireita() throws OverflowException {
		Inteiro aux = new Inteiro(getNumeroBits());
		aux.copia(this);

		iniciaOVetorComBits(getNumeroBits() * 2);
		for (int i = 0; i < aux.getNumeroBits(); i++)
			atualiza(i, aux.bit(i));
	}

	/**
	 * Duplica o número de bits que o binário ocupa colocando 0s à esquerda (não
	 * altera o valor do número)
	 * 
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public void duplicaBitsParaEsquerda() throws OverflowException {
		Inteiro aux = new Inteiro(getNumeroBits());
		aux.copia(this);

		iniciaOVetorComBits(getNumeroBits() * 2);
		for (int i = 0; i < aux.getNumeroBits(); i++)
			atualiza(i + (getNumeroBits() / 2), aux.bit(i));

	}

	/**
	 * @param comparado
	 *            Inteiro a ser comparado com este
	 * @return Se esse é maior que o comparado ou não
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public boolean ehMaiorQue(Inteiro comparado) throws OverflowException {
		return emInteiroDecimal() > comparado.emInteiroDecimal();
	}

	/**
	 * @param comparado
	 *            Inteiro a ser comparado com este
	 * @return Se este é igual ao comparado ou não
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public boolean ehIgual(Inteiro comparado) throws OverflowException {
		return emInteiroDecimal() == comparado.emInteiroDecimal();
	}

	/**
	 * @param comparado
	 *            Inteiro a ser comparado com este
	 * @return Se este é menor que o comparado ou não
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public boolean ehMenorQue(Inteiro comparado) throws OverflowException {
		return emInteiroDecimal() < comparado.emInteiroDecimal();
	}

	/**
	 * Adiciona um bit no final deste binário
	 * 
	 * @param bit
	 *            Valor do bit adicionado
	 * @return Novo inteiro com tal bit
	 * @throws OverflowException
	 *             Caso ocorra overflow na operação
	 */
	public Inteiro adicionaBitNoFinal(boolean bit) throws OverflowException {
		Inteiro aux = new Inteiro(getNumeroBits());
		aux.copia(this);

		iniciaOVetorComBits(aux.getNumeroBits() + 1);
		for (int i = 0; i < aux.getNumeroBits(); i++)
			atualiza(i, aux.bit(i));
		atualiza(getNumeroBits() - 1, bit);

		return this;
	}

	/**
	 * @return Novo inteiro após a remoção do último bit
	 * @throws OverflowException
	 *             Caso alguma operação seja inválida devido a um overflow
	 */
	public Inteiro removeUltimoBit() throws OverflowException {
		Inteiro aux = new Inteiro(getNumeroBits());
		aux.copia(this);

		iniciaOVetorComBits(aux.getNumeroBits() - 1);
		for (int i = 0; i < aux.getNumeroBits() - 1; i++)
			atualiza(i, aux.bit(i));

		return this;
	}

	/**
	 * @return Representação decimal desse binário
	 * @throws OverflowException
	 *             Caso o número não possa ser representado devido a um overflow
	 *             (complemento de 2 com overflow)
	 */
	public int emInteiroDecimal() throws OverflowException {
		int resultado = 0;

		if (!bit(0)) {
			for (int i = getNumeroBits() - 1; i >= 1; i--)
				if (bit(i))
					resultado += Math.pow(2, getNumeroBits() - i - 1);
		} else {
			Inteiro aux = new Inteiro(getNumeroBits());
			aux = seuComplementoDe2();

			for (int i = aux.getNumeroBits() - 1; i >= 1; i--)
				if (aux.bit(i))
					resultado += Math.pow(2, aux.getNumeroBits() - i - 1);

			resultado = (-resultado);
		}

		return resultado;
	}

	@Override
	public Inteiro seuComplementoDe2() throws OverflowException {
		Inteiro um = new Inteiro(getNumeroBits());
		um.valor(1);

		Inteiro binario = new Inteiro(getNumeroBits());
		for (int i = 0; i < getNumeroBits(); i++)
			binario.atualiza(i, !(bit(i)));

		return new SomaDeInteiros().faz(binario, um);
	}

}