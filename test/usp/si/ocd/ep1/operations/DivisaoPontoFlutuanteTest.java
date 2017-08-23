package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste para divisões com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class DivisaoPontoFlutuanteTest {
	
	/**
	 * Método que efetua 512 divisões entre doubles aleatórios
	 */
	@Test
	public void deveResolverDivisaoAleatoria() {		
		for(int i=0; i<512; i++) {
			double n1 = new Random().nextDouble();
			double n2 = new Random().nextDouble();
			
			double resultadoEsperado = n1 / n2;
			
			double resultadoOperando = new DivisaoDePontosFlutuantes().faz(n1, n2);
			
			Assert.assertEquals(resultadoEsperado, resultadoOperando, 2.0);
		}
	}
	
}