package org.auth.exception;

public class NotAllowedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4407425887624859631L;

	public NotAllowedException() {
        super("Not allowed");
    }

}
