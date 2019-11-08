package com.bridgelabz.fundoo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * purpose:Response Message Class
 * @author bridgeit
 *
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
	private int statuscode;
	private String message;
	private Object data;

}
