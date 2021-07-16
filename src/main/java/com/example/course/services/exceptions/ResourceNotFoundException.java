package com.example.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L; //compilador nao obriga a tratar

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
