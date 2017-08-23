package usp.si.ocd.ep1;

import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa um bin�rio
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class Binario {

	// Flags de bin�rios
	public static final boolean POSITIVO = false;
	public static final boolean NEGATIVO = true;

	// Vetor de booleanos
	private boolean[] vetor;

	/**
	 * Construtor que inicia esse bin�rio
	 * 
	 * @param numeroDeBits
	 *            N�mero de bits que esse bin�rio ocupa
	 * @throws OverflowException
	 *             Caso o n�mero de bits designado para esse bin�rio seja
	 *             insuficiente para inici�-lo
	 */
	public Binario(int numeroDeBits) throws OverflowException {
		this(numeroDeBits, POSITIVO);
	}

	/**
	 * Construtor que inicia um bin�rio
	 * 
	 * @param numeroDeBits
	 *            N�mero de bits que esse bin�rio ocupa
	 * @param sinal
	 *            Se tal bin�rio � positivo ou negativo
	 * @throws OverflowException
	 *             Caso o n�mero de bits designado para esse bin�rio seja
	 *             insuficiente para inici�-lo
	 */
	public Binario(int numeroDeBits, boolean sinal) throws OverflowException {
		iniciaOVetorComBits(numeroDeBits);
		inicializaBinarioVazio();
		atualiza(0, sinal);
	}

	/**
	 * M�todo que inicia este bin�rio
	 * 
	 * @param numeroDeBits
	 *            N�mero de bits que esse bin�rio possui
	 */
	protected void iniciaOVetorComBits(int numeroDeBits) {
		vetor = new boolean[numeroDeBits];
	}

	/**
	 * Inicia o bin�rio com todas as posi��es com 0
	 */
	protected void inicializaBinarioVazio() {
		for (int i = 1; i < vetor.length; i++)
			vetor[i] = false;
	}

	/**
	 * @return O vetor de booleanos deste bin�rio
	 */
	public boolean[] valor() {
		return vetor;
	}

	/**
	 * @return N�mero de bits que esse bin�rio ocupa
	 */
	public int getNumeroBits() {
		return vetor.length;
	}

	/**
	 * @param indice
	 *            �ndice a ser observado
	 * @return Valor na posi��o designada
	 */
	public boolean bit(int indice) {
		return vetor[indice];
	}

	/**
	 * Altera uma determinada posi��o no bin�rio
	 * 
	 * @param indice
	 *            �ndice a ser atualizado
	 * @param novoValor
	 *            Novo valor dessa posi��o
	 * @throws OverflowException
	 *             Caso a posi��o designada seja inv�lida para esse bin�rio
	 */
	public void atualiza(int indice, boolean novoValor) throws OverflowException {
		if (indice < 0 || indice >= vetor.length)
			throw new OverflowException("N�mero insuficiente de bits para operar!");
		vetor[indice] = novoValor;
	}

	/**
	 * Copia um bin�rio para esse
	 * 
	 * @param clonado
	 *            Bin�rio a ser copiado
	 */
	public void copia(Binario clonado) {
		for (int i = 0; i < clonado.getNumeroBits(); i++)
			vetor[i] = clonado.bit(i);
	}

	/**
	 * @return Se tal bin�rio representa um n�mero negativo
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