<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차 관리 시스템</title>
<script src="https://kit.fontawesome.com/87ed282d9d.js" crossorigin="anonymous"></script>
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
			<div>
				<h1>주차 관리 시스템입니다.</h1>
				<p>차량을 간편하게 등록하고 등록된 건물의 출입을 편리하게 할 수 있습니다.</p>
				<span>자세히 보기...</span>
			</div>
			<form action="">
				<section>
					<div class="button-box">
						<div class="registration-box">
							<label for="car-number">차량번호</label>
							<input type="text" id="car-number">
						</div>
						<button type="button">차량 등록</button>
					</div>
					<div>
						<button type="button">내 정보 보기</button>
					</div>
					<div>
						<button type="button">결제하기</button>
					</div>
				</section>
			</form>
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