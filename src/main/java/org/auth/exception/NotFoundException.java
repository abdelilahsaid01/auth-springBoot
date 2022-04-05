package org.auth.exception;

public class NotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4407425887624859631L;

	public NotFoundException(String str, Long id) {
        super(str + " id not found : " + id);
    }

}
