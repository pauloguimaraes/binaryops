package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * M�todo de testes da divis�o com n�meros inteiros
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class DivisaoInteiraTest {

	/**
	 * Limites da execu��o do teste (baseado em dois fors, x e j)
	 * 
	 * @param limiteInferiorX
	 *            Inicializa��o de x
	 * @param limiteSuperior
	 *            X Limite exclusivo de x
	 * @param limiteInferiorY
	 *            Inicializa��o de y
	 * @param limiteSuperiorY
	 *            Limite exclusivo de y
	 */
	public void limites(int limiteInferiorX, int limiteSuperiorX, int limiteInferiorY, int limiteSuperiorY) {

		for (int x = limiteInferiorX; x < limiteSuperiorX; x++) {
			for (int y = limiteInferiorY; y < limiteSuperiorY; y++) {
				int resultadoEsperado = x / y;

				try {
					Inteiro bin1 = new Inteiro(32);
					bin1.valor(x);
					Inteiro bin2 = new Inteiro(32);
					bin2.valor(y);

					Assert.assertEquals(resultadoEsperado, new DivisaoDeInteiros().faz(bin1, bin2).emInteiroDecimal(),
							0.001);
				} catch (OverflowException ex) {
					System.out.println(ex.getMessage());
				}

			}
		}

	}

	/**
	 * M�todo que executa todas as divis�es poss�veis no intervalo [1...511] / [1...511]
	 */
	@Test
	public void deveResolverDivisaoDePositivos() {
		limites(1, 512, 1, 512);
	}

	/**
	 * M�todo que executa todas as divis�es poss�veis no intervalo [1...511] / [-512...-1]
	 */
	@Test
	public void deveResolverDivisaoDePositivoComNegativo() {
		limites(1, 512, -512, 0);
	}

	/**
	 * M�todo que executa todas as divis�es poss�veis no intervalo [-512...-1] / [1...511]
	 */
	@Test
	public void deveResolverDivisaoDeNegativoComPositivo() {
		limites(-512, 0, 1, 512);
	}

	/**
	 * M�todo que executa todas as divis�es poss�veis no intervalo [-512...-1] / [-512...-1]
	 */
	@Test
	public void deveResolverDivisaoDeNegativos() {
		limites(-512, 0, -512, 0);
	}

	/**
	 * M�todo que executa a divis�o de dois n�meros aleat�rios
	 */
	@Test
	public void deveResolverDivisaoAleatoria() {

		int i = new Random().nextInt();
		int j = new Random().nextInt();

		int resultadoEsperado = i / j;

		try {
			Inteiro bin1 = new Inteiro(32);
			bin1.valor(i);
			Inteiro bin2 = new Inteiro(32);
			bin2.valor(j);

			Assert.assertEquals(resultadoEsperado, new DivisaoDeInteiros().faz(bin1, bin2).emInteiroDecimal(), 0.001);
		} catch (OverflowException ex) {
			System.out.println(ex.getMessage());
		}

	}

}