package usp.si.ocd.ep1.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que representa a soma com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class SomaDePontosFlutuantes {

	/**
	 * Faz a soma de dois doubles
	 * 
	 * @param a
	 *            Double 1
	 * @param b
	 *            Double 2
	 * @return Soma desses doubles depois de serem convertidos para binários e a
	 *         resposta convertida para representação decimal novamente
	 */
	public double faz(double a, double b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;
		if (a == b * (-1))
			return 0;

		int ai = (int) a, bi = (int) b, e = 0;
		double rd;

		while (a - ai != 0 || b - bi != 0) {
			a = a * 10;
			b = b * 10;
			ai = (int) a;
			bi = (int) b;
			e++;
			if (e >= 6)
				break;
		}

		boolean[] v1 = new boolean[32], v2 = new boolean[32];

		v1 = binarioNoVetor(ai);
		v2 = binarioNoVetor(bi);

		rd = soma(v1, v2, ai, bi);
		while (e > 0) {
			rd = rd / 10;
			e--;
		}
		BigDecimal bd = new BigDecimal(rd).setScale(5, RoundingMode.HALF_EVEN);

		return bd.doubleValue();
	}

	/**
	 * Efetua a soma baseada em dois vetores de booleanos
	 * 
	 * @param v1
	 *            Vetor 1
	 * @param v2
	 *            Vetor 2
	 * @param a
	 *            Expoente 1
	 * @param b
	 *            Expoente 2
	 * @return Resultado da operação convertido para representação decimal
	 */
	private double soma(boolean[] v1, boolean[] v2, int a, int b) {

		boolean[] total = new boolean[32]; // vetor do resultado
		int exp = 0, e; // Exponete do resultado
		boolean ehSoma = true; // Sinaliza se é soma ou subtração

		if (v1[0] == true && v2[0] == true) {
			/*
			 * Se ambos são negativos o resultado será negativo e realiza a soma
			 * normalmente
			 */
			total[0] = true;
		} else if (v1[0] != false || v2[0] != false) {
			ehSoma = false;
		}

		int exp1 = 0, exp2 = 0, bit = 1;

		// Pega expoente do primeiro número
		for (int i = 8; i >= 1; i--) {
			if (v1[i] == true)
				exp1 = exp1 + bit;
			bit = bit * 2;
		}
		bit = 1;
		exp1 = exp1 - 128;
		if (exp1 == -128)
			return b;

		// Pega expoente do segundo número
		for (int i = 8; i >= 1; i--) {
			if (v2[i] == true)
				exp2 = exp2 + bit;
			bit = bit * 2;
		}
		bit = 1;
		exp2 = exp2 - 128;
		if (exp2 == -128)
			return a;

		// Normaliza
		if (exp1 == exp2) {
			if (ehSoma) {
				/*
				 * Se é soma e os expoentes são iguais incrementa o expoente,
				 * faz RightShift e coloca um 0 na casa depois da vírgula Já
				 * realizando de ante mão a soma dos 1 depois da vírgula
				 */
				for (int i = 31; i >= 10; i--) {
					v1[i] = v1[i - 1];
					v1[i - 1] = false;
				}
				for (int i = 31; i >= 10; i--) {
					v2[i] = v2[i - 1];
					v2[i - 1] = false;
				}
				exp = exp1 + 1;
			} else {
				// complementa o menor
				if (v1[0] == true) {
					a = a * (-1);
					if (a > b) {
						total[0] = true;
						// complementar b
						v2 = complementa(v2, exp2);
					} else {
						// complementar a
						v1 = complementa(v1, exp1);
					}
				} else {
					b = b * (-1);
					if (b > a) {
						total[0] = true;
						// complementar a
						v1 = complementa(v1, exp1);
					} else {
						// complementar b
						v2 = complementa(v2, exp2);
					}
				}
				exp = exp1;
			}
		} else {
			/*
			 * Caso expoentes sejam diferentes incrementa o menor e realiza o
			 * RightShift
			 */
			if (exp1 < exp2) {
				v1 = rightShift(v1, exp2 - exp1);

				if (ehSoma) {
					exp = exp2;
				} else {
					v1 = complementa(v1, exp2);
					if (v2[0] == true)
						total[0] = true;

					for (int i = 31; i >= 10; i--) {
						v1[i] = v1[i - 1];
						v1[i - 1] = false;
					}

					for (int i = 31; i >= 10; i--) {
						v2[i] = v2[i - 1];
						v2[i - 1] = false;
					}
					exp = exp2 + 1;
				}
			}

			else if (exp2 < exp1) {
				v2 = rightShift(v2, exp1 - exp2);

				if (ehSoma) {
					exp = exp1;
				} else {
					v2 = complementa(v2, exp1);

					if (v1[0] == true)
						total[0] = true;

					for (int i = 31; i >= 10; i--) {
						v1[i] = v1[i - 1];
						v1[i - 1] = false;
					}

					for (int i = 31; i >= 10; i--) {
						v2[i] = v2[i - 1];
						v2[i - 1] = false;
					}
					exp = exp1 + 1;
				}
			}
		}
		if (exp > 22)
			e = 22;
		else
			e = exp;

		for (int i = 9 + (e - 1); i >= 9; i--) {
			int s = somaBooleana(v1[i], v2[i]);
			/*
			 * O vai um é jogado no vetor do resultado e se faz a operação com o
			 * resultado do somaBit
			 */
			if (s != 0) {
				if (s != 10) {
					// Aqui é caso s = 1 e a posição do vetor é 0
					if (total[i] == false) {
						total[i] = true;
					}
					/*
					 * Senão vai 1 caso não esteja na última casa antes da
					 * vírgula
					 */
					else {
						if (i != 9) {
							total[i] = false;
							total[i - 1] = true;
						} else {
							/*
							 * Do jeito que fiz o código pode fazer v[9] = 0 e
							 * RightShift colocando mais um zero depois da
							 * vírgula
							 */
							total[9] = false;
							for (int j = 31; j >= 10; j--) {
								total[j] = total[j - 1];
								total[j - 1] = false;
							}
							exp++;
						}
					}

				} else {
					/*
					 * Qunado s igual e não é a última casa antes da vírgula a
					 * casa atual se mantém e vai 1
					 */
					if (i != 9) {
						total[i - 1] = true;
					} else {
						/*
						 * Quando é a última casa antes da vírgula se faz o
						 * RightShift
						 */
						for (int j = 31; j >= 10; j--) {
							total[j] = total[j - 1];
							total[j - 1] = false;
						}
						exp++;
					}
				}
			}
		}

		if (!ehSoma) {
			// Quando é subtração se faz LeftShift até o primeiro 1
			while (true) {
				if (total[9] == false) {
					total = leftShift(total);
					exp--;
				} else {
					total = leftShift(total);
					exp--;
					break;
				}
			}
		}

		// Joga o expoente no vetor
		exp = decimalBinario(exp + 128);
		for (e = 8; exp >= 10; e--) {
			if (exp % 10 == 1)
				total[e] = true;
			else
				total[e] = false;
			exp = exp / 10;
		}
		if (exp == 1)
			total[e] = true;
		else
			total[e] = false;

		// Devolve em decimal
		return binarioVetorDecimal(total);
	}

	/**
	 * Desloca o vetor para a direita
	 * 
	 * @param v
	 *            Vetor a ser deslocado
	 * @param exp
	 *            Expoente do binário
	 * @return Novo vetor
	 */
	private static boolean[] rightShift(boolean[] v, int exp) {

		for (int i = 31; i >= 10; i--) {
			v[i] = v[i - 1];
		}
		v[9] = true;
		exp--;

		while (exp > 0) {
			for (int i = 31; i >= 10; i--) {
				v[i] = v[i - 1];
				v[i - 1] = false;
			}
			exp--;
		}

		return v;
	}

	/**
	 * Desloca o vetor para a esquerda
	 * 
	 * @param v
	 *            Vetor a ser deslocado
	 * @return Novo vetor
	 */
	private static boolean[] leftShift(boolean[] v) {

		for (int i = 9; i < 31; i++) {
			v[i] = v[i + 1];
		}
		v[31] = false;

		return v;
	}

	/**
	 * Converte um número binário para um vetor de booleano
	 * 
	 * @param n
	 *            Número decimal para ser convertido
	 * @return Número binário
	 */
	public boolean[] binarioNoVetor(int n) {
		boolean[] v = new boolean[32];
		int exp = 0, j;

		if (n < 0) {
			v[0] = true;
			n = n * (-1);
		}

		while (n >= 2) {
			if (n % 2 == 0)
				v[9] = false;
			else
				v[9] = true;
			n = n / 2;
			exp++;

			if (n >= 2)
				for (int i = 31; i >= 10; i--) {
					v[i] = v[i - 1];
					v[i - 1] = false;
				}
		}
		exp = decimalBinario(exp + 128);

		for (j = 8; exp >= 10; j--) {
			if (exp % 10 == 0)
				v[j] = false;
			else
				v[j] = true;
			exp = exp / 10;
		}
		if (exp == 0)
			v[j] = false;
		else
			v[j] = true;

		return v;
	}

	/**
	 * Converte um decimal para um binário
	 * 
	 * @param d
	 *            Decimal a ser convertido
	 * @return Número binário
	 */
	private int decimalBinario(int d) {
		int b = 0, dec = 1;

		while (d >= 2) {
			b = b + (d % 2 * dec);
			d = d / 2;
			dec = dec * 10;
		}
		b = b + dec;

		return b;
	}

	/**
	 * Efetua a soma booleana de dois bits
	 * 
	 * @param x
	 *            Bit 1
	 * @param y
	 *            Bit 2
	 * @return Valor da operação
	 */
	public int somaBooleana(boolean x, boolean y) {

		if (x == false && y == false)
			return 0;

		if (y == true && x == true)
			return 10;

		return 1;
	}

	/**
	 * Converte um vetor de booleano para binário
	 * 
	 * @param vetor
	 *            Vetor a ser convertido
	 * @return Valor em binário
	 */
	public int binarioVetorDecimal(boolean[] vetor) {
		int exp = 0, t = 0;
		int d = 1;

		for (int i = 8; i >= 1; i--) {
			if (vetor[i])
				exp += d;
			d *= 10;
		}
		d = 1;
		exp = binarioDecimal(exp) - 128;

		for (int i = 9 + (exp - 1); i >= 9; i--) {
			if (vetor[i])
				t += d;
			d *= 2;
		}
		t += d;

		if (vetor[0])
			t *= -1;

		return t;
	}

	/**
	 * Converte um número binário para decimal
	 * 
	 * @param b
	 *            Binário usado para conversão
	 * @return Número decimal
	 */
	public int binarioDecimal(int b) {
		int d = 0;
		int bit = 1;

		while (b >= 10) {
			d = d + ((b % 10) * bit);
			bit = bit * 2;
			b = b / 10;
		}
		d = d + bit;

		return d;
	}

	/**
	 * Calcula o complemento de 2 do vetor booleano
	 * 
	 * @param v
	 *            Vetor usado
	 * @param exp
	 *            Expoente do vetor
	 * @return Novo vetor
	 */
	private boolean[] complementa(boolean[] v, int exp) {

		for (int i = 9 + (exp - 1); i >= 9; i--) {
			if (v[i] == false)
				v[i] = true;
			else
				v[i] = false;
		}

		for (int i = 9 + (exp - 1); i >= 9; i--) {
			if (v[i] == true)
				v[i] = false;
			else {
				v[i] = true;
				break;
			}
		}

		return v;
	}
}