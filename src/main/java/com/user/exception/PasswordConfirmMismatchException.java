package com.user.exception;

public class PasswordConfirmMismatchException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordConfirmMismatchException() {
        super("New password and confirm password do not match!");
    }

    public PasswordConfirmMismatchException(String message) {
        super(message);
    }
}
