package usp.si.ocd.ep1.operations;

import usp.si.ocd.ep1.Inteiro;
import usp.si.ocd.ep1.exceptions.OverflowException;

/**
 * Classe que representa a multiplicação de inteiros usando o algoritmo de Booth
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class MultiplicacaoDeInteiros
	extends OperacaoCom<Inteiro> {
	
	/**
	 * Desloca o vetor para a direita
	 * 
	 * @param bin
	 * Binário a ser deslocado
	 * @throws OverflowException
	 * Caso ocorra overflow nessa operação
	 */
	public void rightShift(Inteiro bin) throws OverflowException {
		for (int i=bin.getNumeroBits()-1; i > 0; i--)
			bin.atualiza(i, bin.bit(i-1));
	}
	
	@Override
	public Inteiro faz(Inteiro multiplicador, Inteiro multiplicando) throws OverflowException {
		Inteiro A = new Inteiro(multiplicador.getNumeroBits());
		A.copia(multiplicador);
		A.duplicaBitsParaDireita();
		A = A.adicionaBitNoFinal(false);

		Inteiro S = new Inteiro(multiplicador.getNumeroBits());
		S.copia(multiplicador.seuComplementoDe2());
		S.duplicaBitsParaDireita();
		S = S.adicionaBitNoFinal(false);
		
		Inteiro P = new Inteiro(multiplicando.getNumeroBits());
		P.copia(multiplicando);
		P.duplicaBitsParaEsquerda();
		P = P.adicionaBitNoFinal(false);
		
		for(int i=0; i<multiplicador.getNumeroBits(); i++) {
			String stringDeP = P.toString();
			String comparador = stringDeP.substring(
					stringDeP.length()-2, stringDeP.length());
			
			if(comparador.equals("00") || comparador.equals("11"));
			
			else if(comparador.equals("10")) {
				Inteiro novo = new SomaDeInteiros().faz(P, S);
				P = new Inteiro(novo.getNumeroBits());
				P.copia(novo);
			}
			
			else if(comparador.equals("01")) {
				Inteiro novo = new SomaDeInteiros().faz(P, A);
				P = new Inteiro(novo.getNumeroBits());
				P.copia(novo);
			}
			
			rightShift(P);
		}
		
		P = P.removeUltimoBit();
		return P;
	}
	
}