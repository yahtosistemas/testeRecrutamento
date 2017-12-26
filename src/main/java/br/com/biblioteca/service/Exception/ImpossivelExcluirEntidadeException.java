package br.com.biblioteca.service.Exception;

public class ImpossivelExcluirEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirEntidadeException(String message) {
		super(message);
	}

}
