<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" href="static/css/signup.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>회원가입을 환영합니다!</h1>
		</header>
		<main>
			<form action="signup" method="post">
				<div>
					<label for="name">이름</label>
					<input type="text" id="name" name="name" required>
				</div>
				<div>
					<label for="username">아이디</label>
					<input type="text" id="username" name="username" required>
					<button type="button">중복체크</button>
				</div>
				<div>
					<label for="password">비밀번호</label>
					<input type="password" id="password" name="password" required>
					<div class="explain-password-box">
						<span class="explain-password">비밀번호는 영문,숫자,*만 포함할 수 있습니다.</span>
					</div>
				</div>
				<div class="pc">
					<label for="password-check">비밀번호 확인</label>
					<input type="password" id="password-check" name="password-check" required>
					<span class="password-error">비밀번호가 다릅니다.</span>
				</div>
				<div>
					<label for="email">이메일</label>
					<input type="text" id="email" name="email" required>
					<select name="select-email" class="select-box">
						<option class="test">직접입력</option>
						<option value="@naver.com">@naver.com</option>
						<option value="@gmail.com">@gmail.com</option>
						<option value="@kakao.com">@kakao.com</option>
					</select>
				</div>
				<button type="submit">회원가입하기</button>
			</form>
		</main>
		<footer>
		
		</footer>
	</div>
<script type="text/javascript" src="static/js/signup.js"></script>
</body>
</html>