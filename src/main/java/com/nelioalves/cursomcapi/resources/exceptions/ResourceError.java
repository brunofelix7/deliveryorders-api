package com.nelioalves.cursomcapi.resources.exceptions;

import java.io.Serializable;

/**
 * Classe padrao de retorno do meu resource caso ocorra alguma execao
 */
public class ResourceError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Long timeStamp;
	
	public ResourceError() {
		
	}
	
	public ResourceError(Integer status, String message, Long timeStamp) {
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
	
	public Long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
