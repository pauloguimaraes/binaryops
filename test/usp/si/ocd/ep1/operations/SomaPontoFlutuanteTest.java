package usp.si.ocd.ep1.operations;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de testes para soma com pontos flutuantes
 * @author phfgu
 *
 */
public class SomaPontoFlutuanteTest {
	
	/**
	 * Método que realiza 512 operações com pontos flutuantes
	 */
	@Test
	public void deveResolverSomaAleatoria() {
		
		for(int i=0; i<512; i++) {
			double n1 = new Random().nextDouble();
			double n2 = new Random().nextDouble();
			
			double resultadoEsperado = n1 + n2;
			
			double resultadoOperando = new SomaDePontosFlutuantes().faz(n1, n2);
			
			Assert.assertEquals(resultadoEsperado, resultadoOperando, 0.0001);
		}
		
	}
	
}