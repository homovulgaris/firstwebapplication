package com.in28minutes.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String string) {
	super(string);
    }


    public static String BUSINESS_EXCEPTION = "Exception from ExceptionController";

    /**
     *
     */
    private static final long serialVersionUID = -201870547416383419L;


}
