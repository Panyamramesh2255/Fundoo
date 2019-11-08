package com.bridgelabz.fundoo.model;

import java.io.Serializable;

import lombok.Data;
/**
 * purpose:Model Class For Mailing 
 * @author PanyamRamesh
 *
 */
@Data
public class EmailDataModel implements Serializable {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String mailMessage;
private String emailId;
private String token;
	

}
