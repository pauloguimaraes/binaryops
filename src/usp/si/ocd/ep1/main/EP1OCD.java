package usp.si.ocd.ep1.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;
import usp.si.ocd.ep1.operations.DivisaoDeInteiros;
import usp.si.ocd.ep1.operations.DivisaoDePontosFlutuantes;
import usp.si.ocd.ep1.operations.MultiplicacaoDeInteiros;
import usp.si.ocd.ep1.operations.MultiplicacaoDePontosFlutuantes;
import usp.si.ocd.ep1.operations.SomaDeInteiros;
import usp.si.ocd.ep1.operations.SomaDePontosFlutuantes;
import usp.si.ocd.ep1.operations.SubtracaoDeInteiros;
import usp.si.ocd.ep1.operations.SubtracaoDePontosFlutuantes;

public class EP1OCD {

	// Flags para tipos de operação
	public static final int INTEIRO = 0;
	public static final int PONTO_FLUTUANTE = 1;

	// Flags para representação dos operadores
	public static final char SOMA = '+';
	public static final char SUBTRACAO = '-';
	public static final char MULTIPLICACAO = '*';
public static final char DIVISAO = '/';

	public static void main(String[] args) {
		mostraSaudacao();

		Scanner scanner = new Scanner(System.in);
		int tipoDeOperacao = leTipoDeOperacao(scanner, -1);

		switch (tipoDeOperacao) {

		case INTEIRO:
			int continuar = 1;

			while (continuar == 1) {
				try {
					int numeroDeBits = leNumeroDeBitsDaOperacao(scanner);

					Inteiro bin1 = new Inteiro(numeroDeBits);
					Inteiro bin2 = new Inteiro(numeroDeBits);

					char operacao = 'A';
					operacao = pedeOperador(scanner, operacao);

					int primeiroNumero = leNumeroInteiro(scanner, 1);
					bin1.valor(primeiroNumero);

					int segundoNumero = leNumeroInteiro(scanner, 2);
					bin2.valor(segundoNumero);

					Inteiro resultado = null;
					resultado = new Inteiro(bin1.getNumeroBits());
					resultado.valor(0);
					switch (operacao) {

					case SOMA:
						try {
							resultado = new SomaDeInteiros().faz(bin1, bin2);
						} catch (ArithmeticException ex) {
							System.out.println("\n\n" + ex.getMessage());
						}
						break;

					case SUBTRACAO:
						try {
							resultado = new SubtracaoDeInteiros().faz(bin1, bin2);
						} catch (ArithmeticException ex) {
							System.out.println("\n\n" + ex.getMessage());
						}
						break;

					case MULTIPLICACAO:
						try {
							resultado = new MultiplicacaoDeInteiros().faz(bin1, bin2);
						} catch (ArithmeticException ex) {
							System.out.println("\n\n" + ex.getMessage());
						}
						break;

					case DIVISAO:
						try {
							resultado = new DivisaoDeInteiros().faz(bin1, bin2);
						} catch (ArithmeticException ex) {
							System.out.println("\n\n" + ex.getMessage());

						}
						break;

					}
					System.out.println("Em decimal: " + resultado.emInteiroDecimal());

					System.out.print("\n\nDeseja Continuar (1 - Sim | 2 - Não): ");
					continuar = scanner.nextInt();
				} catch (InputMismatchException ex) {
					continue;
				} catch (OverflowException ex) {
					System.out.println(ex.getMessage());
				}
			}
			break;

		case PONTO_FLUTUANTE:
			continuar = 1;

			while (continuar == 1) {
				try {
					char operacao = 'A';
					operacao = pedeOperador(scanner, operacao);

					double primeiroNumero = leNumeroDouble(scanner, 1);
					double segundoNumero = leNumeroDouble(scanner, 2);

					double resultado = 0;
					switch (operacao) {

					case SOMA:
						resultado = new SomaDePontosFlutuantes().faz(primeiroNumero, segundoNumero);
						break;

					case SUBTRACAO:
						resultado = new SubtracaoDePontosFlutuantes().faz(primeiroNumero, segundoNumero);
						break;

					case MULTIPLICACAO:
						resultado = new MultiplicacaoDePontosFlutuantes().faz(primeiroNumero, segundoNumero);
						break;

					case DIVISAO:
						try {
							resultado = new DivisaoDePontosFlutuantes().faz(primeiroNumero, segundoNumero);
						} catch (ArithmeticException ex) {
							System.out.println("\n\n" + ex.getMessage());
							resultado = 0;
						}
						break;

					}

					System.out.println("Em decimal: " + resultado);

					System.out.print("\n\nDeseja Continuar (1 - Sim | 2 - Não): ");
					continuar = scanner.nextInt();
				} catch (InputMismatchException ex) {
					continue;
				}
			}
			break;

		}

		scanner.close();
	}

	/**
	 * Método que lê um operador
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @return Operador escolhido
	 */
	private static char leOperador(Scanner scanner) {
		System.out.print(
				"Digite a operação que será realizada (\"+\" Soma | \"-\" Subtração | \"*\" Multiplicação | \"/\" Divisão): ");
		return scanner.next().trim().charAt(0);
	}

	/**
	 * Método que pede um operador para o usuário
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @param tipo
	 *            Operador conhecido até agora
	 * @return Operador inputado
	 */
	private static char pedeOperador(Scanner scanner, char tipo) {
		while (true) {
			tipo = leOperador(scanner);

			if (tipo == SOMA || tipo == SUBTRACAO || tipo == MULTIPLICACAO || tipo == DIVISAO)
				break;
			else
				continue;
		}
		return tipo;
	}

	/**
	 * Método que lê o tipo de operação inputado
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @param tipoDeOperacao
	 *            Tipo de operação inputado
	 * @return Qual o tipo de operação
	 * @throws InputMismatchException
	 *             Caso a entrada não seja compreendida
	 */
	private static int leTipoDeOperacao(Scanner scanner, int tipoDeOperacao) throws InputMismatchException {
		while (true) {
			tipoDeOperacao = pedeTipoDeOperacao(scanner);

			if (tipoDeOperacao == INTEIRO || tipoDeOperacao == PONTO_FLUTUANTE)
				break;
			else
				continue;
		}
		return tipoDeOperacao;
	}

	/**
	 * Método que pede um tipo de operação para ser feita
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @return Tipo de operação inputado
	 * @throws InputMismatchException
	 *             Caso não consiga compreender a operação que está sendo
	 *             inputada
	 */
	private static int pedeTipoDeOperacao(Scanner scanner) throws InputMismatchException {
		System.out.print("\n\nQual tipo numérico você irá manipular (0 - inteiro / 1 - Ponto flutuante): ");
		return scanner.nextInt();
	}

	/**
	 * Lê um número double
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @param numero
	 *            Número de vezes que esse método já foi chamado
	 * @return Inteiro inputado
	 */
	private static int leNumeroInteiro(Scanner scanner, int numero) {
		System.out.print("\nDigite o " + numero + "o número da operação: ");
		return scanner.nextInt();
	}

	/**
	 * Lê um número double
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @param numero
	 *            Número de vezes que esse método foi chamado
	 * @return Double inputado
	 */
	private static double leNumeroDouble(Scanner scanner, int numero) {
		System.out.print("\nDigite o " + numero + "o número da operação: ");
		return scanner.nextDouble();
	}

	/**
	 * Método que lê com quantos bits a operação será feita
	 * 
	 * @param scanner
	 *            Scanner usado no programa
	 * @return Número de bits inputado
	 */
	private static int leNumeroDeBitsDaOperacao(Scanner scanner) {
		System.out.println("\n\nEscolha com quantos bits a conta será feita: ");
		return scanner.nextInt();
	}

	/**
	 * Mostra a saudação na inicialização do programa
	 */
	private static void mostraSaudacao() {
		System.out.print("\n\nBem-vindo(a) à calculadora de binários!\n\n");
	}

}