package usp.si.ocd.ep1.operations;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa uma subtração de números inteiros
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class SubtracaoDeInteiros extends OperacaoCom<Inteiro> {
	
	@Override
	public Inteiro faz(Inteiro binario1, Inteiro binario2) throws OverflowException {
		return new SomaDeInteiros().faz(binario1, binario2.seuComplementoDe2());
	}

}