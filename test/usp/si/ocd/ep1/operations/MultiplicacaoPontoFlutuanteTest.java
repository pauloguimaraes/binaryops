package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de testes de multiplicação com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class MultiplicacaoPontoFlutuanteTest {
	
	/**
	 * Método que efetua 10 multiplicações aleatórias
	 */
	@Test
	public void deveResolverMultiplicacaoAleatoria() {
		for(int i=0; i<512; i++) {
			double n1 = new Random().nextDouble();
			double n2 = new Random().nextDouble();
			
			n1 = Math.round(n1 * 1) / 1;
			n2 = Math.round(n2 * 1) / 1;
			
			double resultadoEsperado = n1 * n2;
			
			double resultadoOperando = new MultiplicacaoDePontosFlutuantes().faz(n1, n2);
			
			Assert.assertEquals(resultadoEsperado, resultadoOperando, 0.0001);
		}
	}
	
}