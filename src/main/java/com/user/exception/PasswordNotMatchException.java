package com.user.exception;

public class PasswordNotMatchException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException() {
        super("Current password is incorrect!");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
