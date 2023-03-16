package com.library.Response;

public class ApiResponse 
{
	
	private boolean sucess;
	
	private String message;

	
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(boolean sucess, String message) {
		super();
		this.sucess = sucess;
		this.message = message;
	}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
