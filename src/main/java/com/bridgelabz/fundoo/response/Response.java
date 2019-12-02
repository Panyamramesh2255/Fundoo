package com.bridgelabz.fundoo.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int statusCode;
	Object data;
	String message;
	
	

}
