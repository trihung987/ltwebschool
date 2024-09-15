<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<title>Login Form</title>
<meta charset="utf-8">
<!-- <meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<!--  <link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> --> 

<!--  <link rel="stylesheet"  href="/css/style.css"> 
 <link rel="stylesheet"  href="/css/bootstrap.min.css"/> -->
<!--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 --> <style>
<%@ include file ="/WEB-INF/css/style2.css"%>
</style>   
<!-- <script>
	if ("${type}".length==0){
		$type = "Login";
		console.log("${type}");
	}
		
		
</script> -->
<script src="https://kit.fontawesome.com/e9b18be351.js" crossorigin="anonymous"></script>

</head>
<body>
	<h2>Home Page</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="register" method ="post">
			<h1>Create Account</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
			<span>or use your email for registration</span>
			<input type="text" name = "username" placeholder="Username" required/>
			<input type="email" name = "email" placeholder="Email" required/>
			<input type="text" name = "fullname" placeholder="Fullname" required/>
			<input type="password" id = "pass-input" name = "password" placeholder="Password" required/>
			<span id="toggle-pass" class="fa-regular fa-eye toggle-password" ></span>
			<p class="text-center" style="color: ${color}">${error2}</p>
			<button class = "btn">Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="login" method = "post">
			<h1>Sign in</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
			<span>or use your account</span>
			<input type="text" name = "username" placeholder="Username" required/>
			<input type="password" name = "password" placeholder="Password" required/>
			
			<p class="text-center" style="color: ${color}">${error}</p>
			
			<a href="forget">Forgot your password?</a>
			<button class = "btn">Sign In</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>

<footer>
	<p>
		Created <i class="fa fa-heart"></i> by
		TriHung
		- You can get the code github 
		<a target="_blank" href="https://github.com/trihung987/ltwebschool">here</a>.
	</p>
</footer>
<script src="js/function.js"> </script>
<script src="js/password2.js"> </script>
	<script> 
		let test = "${type}";
		
		if (test.length>5){
			document.getElementById('container').classList.add('right-panel-active');
			console.log("${type}");
		}
		
	</script>
</body>
</html>




<!-- <form action="login" method="post">
	<input type="text" name="username" id="username"
		placeholder="Tai khoan..." required> <input type="text"
		name="password" id="password" placeholder="Mat khau..." required>
	<input type="submit" value="Dang Nhap"> <input type="checkbox"
		id="remember" name="remember" autocomplete="off">

</form> -->