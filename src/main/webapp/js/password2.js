

var toggle = document.getElementsByTagName("span")
var input = document.getElementById('pass-input');
if (toggle.length > 0) {
	var tog = toggle[1];
	tog.addEventListener('click', () => {
		console.log(tog.id) 
		if (tog.classList.contains('fa-eye-slash')){
			tog.classList.replace('fa-eye-slash', 'fa-eye');
			input.setAttribute('type', 'password');
		}else{
			tog.classList.replace('fa-eye', 'fa-eye-slash');
			input.setAttribute('type', 'text');
		}
		
	});
	
}
