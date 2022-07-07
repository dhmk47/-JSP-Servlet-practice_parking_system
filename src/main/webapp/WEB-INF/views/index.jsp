<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차 관리 시스템</title>
<script src="https://kit.fontawesome.com/87ed282d9d.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" href="static/css/index.css">
</head>
<body>
	<div id="container">
		<header>
			<div>
				<h1 class="fa-solid fa-square-parking header-logo"></h1>
			</div>
		</header>
		<main>
			<div class="main-top-box">
				<h1>주차 관리 시스템입니다.</h1>
				<p>차량을 간편하게 등록하고 등록된 건물의 출입을 편리하게 할 수 있습니다.</p>
				<span class="show-dtl">자세히 보기...</span>
			</div>
			<aside>
				<div class="login-box">
					<div class="error-msg">
						<span>로그인을 먼저 진행해 주세요</span>
					</div>
					<input type="text" class="username" placeholder="아이디" name="username" required>
					<input type="password" class="password" placeholder="비밀번호" name="password" required>
				</div>
				<div class="login-user-box">
					<span>~~~님 환영합니다</span>
				</div>
				<div class="signup-signin-box">
					<div>
						<button type="button" class="signup">회원가입</button>
					</div>
					<div>
						<button type="button" class="signin">로그인</button>
					</div>
				</div>
			</aside>
			<section>
				<form action="" method="post">
					<div class="button-box btn1">
						<div class="registration-box">
							<div class="add-car">
								<label for="car-number">차량번호:&nbsp</label>
								<input type="text" id="car-number" name="car-number">
							</div>
							<div class="select-option">
								<span>주차권:&nbsp</span>
								<select name="select-option">
									<option value="1">1일 주차권</option>
									<option value="2">3일 주차권</option>
									<option value="3">일주일 주차권</option>
									<option value="4">한달 주차권</option>
								</select>
							</div>
							<div>
								<button type="button" class="register-car-btn">등록하기</button>
							</div>
						</div>
						<button type="button" class="btn">차량 등록</button>
					</div>
				</form>
				<div class="button-box btn2">
					<div class="info-box">
						<h1>~~님의 정보</h1>
						<p>등록 차량 번호:&nbsp
							<select>
								
							</select>
						</p>
						<p class="ticket-type">주차권:&nbsp</p>
					</div>
					<button type="button" class="btn show-my-info-btn">내 정보 보기</button>
				</div>
				<div class="button-box btn3">
					<div class="payment-box">
						<h1>~~님의 요금현황</h1>
						<p>지불금액:&nbsp</p>
					</div>
					<button type="button" class="btn pay-btn">결제하기</button>
				</div>
			</section>
		</main>
		<footer>
			<nav>
				<ul>
					<li>회사소개</li>
					<li>이용약관</li>
					<li>개인정보처리방침</li>
					<li>고객센터</li>
				</ul>
			</nav>
		</footer>
	</div>
	<script type="text/javascript" src="static/js/index.js"></script>
</body>
</html>