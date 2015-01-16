function LoginService() {
	this.login = function(username, password) {
		alert('Welcome, ' + username);
	};
}
loginService = new LoginService();
