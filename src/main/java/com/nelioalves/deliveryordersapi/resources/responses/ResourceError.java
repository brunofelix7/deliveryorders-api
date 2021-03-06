package com.nelioalves.deliveryordersapi.resources.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe padrao de retorno do meu resource caso ocorra alguma execao
 */
public class ResourceError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date timeStamp;
	
	public ResourceError() {
		
	}
	
	public ResourceError(Integer status, String message, Date timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
