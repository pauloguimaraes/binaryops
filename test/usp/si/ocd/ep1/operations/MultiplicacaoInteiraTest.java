package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe de testes para multiplica��o de n�meros inteiros
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class MultiplicacaoInteiraTest {
	
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
				int resultadoEsperado = x * y;

				try {
					Inteiro bin1 = new Inteiro(32);
					bin1.valor(x);
					Inteiro bin2 = new Inteiro(32);
					bin2.valor(y);

					Assert.assertEquals(resultadoEsperado, new MultiplicacaoDeInteiros().faz(bin1, bin2).emInteiroDecimal(),
							0.001);
				} catch (OverflowException ex) {
					System.out.println(ex.getMessage());
				}

			}
		}

	}

	/**
	 * M�todo que executa todas as multiplica��es poss�veis no intervalo [1...511] * [1...511]
	 */
	@Test
	public void deveResolverMultiplicacaoDePositivos() {
		limites(1, 512, 1, 512);
	}

	/**
	 * M�todo que executa todas as multipli��es poss�veis no intervalo [1...511] * [-512...-1]
	 */
	@Test
	public void deveResolverMultiplicacaoDePositivoComNegativo() {
		limites(1, 512, -512, 0);
	}

	/**
	 * M�todo que executa todas as multiplica��es poss�veis no intervalo [-512...-1] * [1...511]
	 */
	@Test
	public void deveResolverMultiplicacaoDeNegativoComPositivo() {
		limites(-512, 0, 1, 512);
	}

	/**
	 * M�todo que executa todas as multiplica��es poss�veis no intervalo [-512...-1] * [-512...-1]
	 */
	@Test
	public void deveResolverMultiplicacaoDeNegativos() {
		limites(-512, 0, -512, 0);
	}
	
	/**
	 * M�todo que resolve uma multiplica��o aleat�ria
	 */
	@Test
	public void deveResolverMultiplicacaoAleatoria() {
		
		int i = new Random().nextInt(5000);
		int j = new Random().nextInt(5000);
		
		int resultadoEsperado = i*j;
		
		try {
			Inteiro bin1 = new Inteiro(32);
			bin1.valor(i);
			Inteiro bin2 = new Inteiro(32);
			bin2.valor(j);
			
			Assert.assertEquals(resultadoEsperado, new MultiplicacaoDeInteiros().faz(bin1, bin2).emInteiroDecimal(), 0.001);
		} catch(OverflowException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
}