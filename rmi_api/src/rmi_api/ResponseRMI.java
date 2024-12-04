package rmi_api;

import java.io.Serializable;

public class ResponseRMI implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseRMI() {};
	
	public ResponseRMI(String statuscode, String body) {
		this.statuscode = statuscode;
		this.body = body;
	}

	String statuscode;
	
	public String body ;
}
