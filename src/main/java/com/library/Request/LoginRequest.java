package com.library.Request;

import javax.validation.constraints.NotBlank;

public class LoginRequest 
{
	
	 @NotBlank
	    private String usernameOrEmail;

	    @NotBlank
	    private String password;

		public String getUsernameOrEmail() {
			return usernameOrEmail;
		}

		public void setUsernameOrEmail(String usernameOrEmail) {
			this.usernameOrEmail = usernameOrEmail;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public LoginRequest(@NotBlank String usernameOrEmail, @NotBlank String password) {
			super();
			this.usernameOrEmail = usernameOrEmail;
			this.password = password;
		}

		public LoginRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    


}
