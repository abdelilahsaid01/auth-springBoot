package org.auth.exception;

public class ForbiddenException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4407425887624859631L;

	public ForbiddenException() {
        super(" Login ou password incorrect");
    }

}
