package usp.si.ocd.ep1;

import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa um binário
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class Binario {

	// Flags de binários
	public static final boolean POSITIVO = false;
	public static final boolean NEGATIVO = true;

	// Vetor de booleanos
	private boolean[] vetor;

	/**
	 * Construtor que inicia esse binário
	 * 
	 * @param numeroDeBits
	 *            Número de bits que esse binário ocupa
	 * @throws OverflowException
	 *             Caso o número de bits designado para esse binário seja
	 *             insuficiente para iniciá-lo
	 */
	public Binario(int numeroDeBits) throws OverflowException {
		this(numeroDeBits, POSITIVO);
	}

	/**
	 * Construtor que inicia um binário
	 * 
	 * @param numeroDeBits
	 *            Número de bits que esse binário ocupa
	 * @param sinal
	 *            Se tal binário é positivo ou negativo
	 * @throws OverflowException
	 *             Caso o número de bits designado para esse binário seja
	 *             insuficiente para iniciá-lo
	 */
	public Binario(int numeroDeBits, boolean sinal) throws OverflowException {
		iniciaOVetorComBits(numeroDeBits);
		inicializaBinarioVazio();
		atualiza(0, sinal);
	}

	/**
	 * Método que inicia este binário
	 * 
	 * @param numeroDeBits
	 *            Número de bits que esse binário possui
	 */
	protected void iniciaOVetorComBits(int numeroDeBits) {
		vetor = new boolean[numeroDeBits];
	}

	/**
	 * Inicia o binário com todas as posições com 0
	 */
	protected void inicializaBinarioVazio() {
		for (int i = 1; i < vetor.length; i++)
			vetor[i] = false;
	}

	/**
	 * @return O vetor de booleanos deste binário
	 */
	public boolean[] valor() {
		return vetor;
	}

	/**
	 * @return Número de bits que esse binário ocupa
	 */
	public int getNumeroBits() {
		return vetor.length;
	}

	/**
	 * @param indice
	 *            Índice a ser observado
	 * @return Valor na posição designada
	 */
	public boolean bit(int indice) {
		return vetor[indice];
	}

	/**
	 * Altera uma determinada posição no binário
	 * 
	 * @param indice
	 *            Índice a ser atualizado
	 * @param novoValor
	 *            Novo valor dessa posição
	 * @throws OverflowException
	 *             Caso a posição designada seja inválida para esse binário
	 */
	public void atualiza(int indice, boolean novoValor) throws OverflowException {
		if (indice < 0 || indice >= vetor.length)
			throw new OverflowException("Número insuficiente de bits para operar!");
		vetor[indice] = novoValor;
	}

	/**
	 * Copia um binário para esse
	 * 
	 * @param clonado
	 *            Binário a ser copiado
	 */
	public void copia(Binario clonado) {
		for (int i = 0; i < clonado.getNumeroBits(); i++)
			vetor[i] = clonado.bit(i);
	}

	/**
	 * @return Se tal binário representa um número negativo
	 */
	public boolean ehNegativo() {
		return vetor[0];
	}

	@Override
	public String toString() {
		String saida = "";
		for (int i = 0; i < vetor.length; i++) {
			saida += (vetor[i] ? "1" : "0");
			if (i == 0)
				saida += " ";
		}
		return saida;
	}

}