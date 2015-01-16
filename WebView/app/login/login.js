// Viewmodel - defines the data and behavior of your UI
function LoginViewModel() {
	var self = this;
	self.username = ko.observable();
	self.password = ko.observable();
	self.loginData = ko.observable();

	self.login = function() {
		loginService.login(this.username(), this.password);
	};
}

// Activates knockout.js
ko.applyBindings(new LoginViewModel());

// Activate validation framework
$(document).ready(function() {
	$('form').formValidation();
});