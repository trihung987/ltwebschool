const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	active(true);
});

signInButton.addEventListener('click', () => {
	active(false);
});

function active(check1){
	if (check1){
		$type = "Register";
		container.classList.add("right-panel-active");
	}else{
		$type = "Login";
		container.classList.remove("right-panel-active");
	}
	
}


