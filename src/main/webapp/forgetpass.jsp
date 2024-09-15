<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<title>Login Form</title>
<meta charset="utf-8">
<!-- <meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<!-- <link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<script src="https://kit.fontawesome.com/e9b18be351.js" crossorigin="anonymous"></script><!--  <link rel="stylesheet"  href="/css/style.css"> 
 <link rel="stylesheet"  href="/css/bootstrap.min.css"/> -->
 <style>
<%@ include file ="/WEB-INF/css/style3.css"%>
</style>   

</head>
<body>
	<h2>Home Page</h2>
<div class="container" id="container">
	<div class="form-container sign-in-container">
		<form action="forget" method = "post">
			<h1>Renew password</h1>

			<input type="email" name = "email" placeholder="Email" value='${oldmail}' required/>
			<input type="password" id = "pass-input" name = "password" placeholder="New Password" value='${oldpass}' required/>
			<span id="toggle-pass" class="fa-regular fa-eye toggle-password" ></span>
			<div class ="div-center">
				<input class = "input-otp" type="text" name = "otp-code" placeholder="OTP Code"/>
			
				<button class = "btn otp" name ="button" value ="otp">Get otp</button>
			</div>
			
			<p class="text-center" style="color: ${color}">${msg}</p>
			
			<button class = "btn submit" name ="button" value ="submit">Submit</button>
		</form>
		
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
	<script src="js/password.js"> </script>

</body>
</html>
