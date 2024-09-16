
/* use ajax jquerry this file instead vanilla js on the password1, 2, function.js */
$("form").on("submit", function(event) {
  	$(event.target).find("button[id='btn-otp']").trigger("click");
	
});

var canClick = false;

const validateEmail = (email) => {
  return String(email)
    .toLowerCase()
    .match(
        /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);
};

$("button[id='btn-otp']").on("click", function() {
  	var email = $("input[type='email']");
  	var password = $("input[type='password']").val();
  	if (validateEmail(email.val()) && password!=""){
		$("img[class='loading']").css('left', '38px');
		$("p[class='text-center']").attr('style','color: black')
		$("p[class='text-center']").text('Hãy chờ trong giây lát..');	
	}

});
/*if (toggle.length > 0) {
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
	
}*/
