<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" href="static/css/modifyAccount.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>회원정보 수정 페이지</h1>
		</header>
		<main>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password">
				<div class="explain-password-box">
					<span class="explain-password">비밀번호는 영문,숫자,*만 포함할 수 있습니다.</span>
				</div>
			</div>
			<div class="pc">
				<label for="password-check">비밀번호 확인</label>
				<input type="password" id="password-check" name="password-check">
				<span class="password-error">비밀번호가 다릅니다.</span>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email">
				<select name="select-email" class="select-box">
					<option class="test">직접입력</option>
					<option value="@naver.com">@naver.com</option>
					<option value="@gmail.com">@gmail.com</option>
					<option value="@kakao.com">@kakao.com</option>
				</select>
			</div>
			<button type="button" class="userinfo-modify-btn">수정하기</button>
			<div class="delete-car-box">
				<span>등록 해제할 차량</span>
				<select>
				
				</select>
				<span class="delete-error-msg">주차권이 아직 유효한 차량은 등록해제 할 수 없습니다.</span>
			</div>
			<button type="button" class="delete-car-btn">차량 등록 해제</button>
		</main>
		<footer>
		
		</footer>
	</div>
	<script type="text/javascript" src="static/js/modifyAccount.js"></script>
</body>
</html>