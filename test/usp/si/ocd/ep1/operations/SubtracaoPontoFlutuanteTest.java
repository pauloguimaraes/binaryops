package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de testes para subtrações com pontos flutuantes
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class SubtracaoPontoFlutuanteTest {
	
	/**
	 * Método que realiza 512 subtrações com pontos flutuantes
	 */
	@Test
	public void deveResolverSubtracaoAleatoria() {
		for(int i=0; i<512; i++) {
			double n1 = new Random().nextDouble();
			double n2 = new Random().nextDouble();
			
			double resultadoEsperado = n1 - n2;
			
			double resultadoOperando = new SubtracaoDePontosFlutuantes().faz(n1, n2);
			
			Assert.assertEquals(resultadoEsperado, resultadoOperando, 0.0001);
		}
	}
	
}