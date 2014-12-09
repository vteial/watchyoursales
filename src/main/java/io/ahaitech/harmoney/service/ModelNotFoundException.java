package io.ahaitech.harmoney.service;

public class ModelNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModelNotFoundException() {
		super();
	}

	public ModelNotFoundException(String s) {
		super(s);
	}
}
