package usp.si.ocd.ep1.exceptions;

/**
 * Exce��o de overflow na opera��o
 * 
 * @author Carlos F. Traldi - NUSP: 9390322
 * @author Paulo H. F. Guimaraes - NUSP: 9390361
 * @author Pedro A. Minutentag - NUSP: 7994580
 */
public class OverflowException extends Exception {

	// Serial ID que se deve implementar para evitar warnings chatos
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor que define uma mensagem para a exce��o
	 * 
	 * @param msg
	 *            Mensagem definida
	 */
	public OverflowException(String msg) {
		super(msg);
	}

}