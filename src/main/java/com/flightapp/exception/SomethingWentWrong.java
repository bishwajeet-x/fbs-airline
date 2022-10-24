package com.flightapp.exception;

public class SomethingWentWrong extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SomethingWentWrong() {
		super();
	}
	
	public SomethingWentWrong(String msg) {
		super(msg);
	}

	public SomethingWentWrong(Exception e) {
		super(e);
	}
	
	public SomethingWentWrong(String msg, Exception e) {
		super(msg, e);
	}
}
