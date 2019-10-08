<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>수은대학교 학사관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="/eunsu/resources/images/icons/fi.png" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="/eunsu/resources/css/main.css">
<!--===============================================================================================-->
</head>
<body style="background-color: #666666;">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="/eunsu/login" method="post">
					<span class="login100-form-title p-b-43"> 수은 대학교 학사관리 </span>


					<div class="wrap-input100 validate-input" data-validate="학번을 입력하세요">
						<input class="input100" type="text" name="no" id="no"> <span
							class="focus-input100"></span> <span class="label-input100">학번</span>
					</div>


					<div class="wrap-input100 validate-input"
						data-validate="비밀번호를 입력하세요">
						<input class="input100" type="password" name="pass" id="pass"> <span
							class="focus-input100"></span> <span class="label-input100">비밀번호</span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-32">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox"
								name="remember-me"> <label class="label-checkbox100"
								for="ckb1"> 자동 저장 </label>
						</div>

						<div>
							<a href="#" class="txt1"> 비밀번호 찾기 </a>
						</div>
					</div>


					<div class="container-login100-form-btn">
						<button class="login100-form-btn">로그인</button>
					</div>
				</form>

				<div class="login100-more"
					style="background-image: url('/eunsu/resources/images/bg-01.jpg');">
				</div>
			</div>
		</div>
	</div>





	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/bootstrap/js/popper.js"></script>
	<script src="/eunsu/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="/eunsu/resources/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="/eunsu/resources/js/main.js"></script>

</body>
</html>