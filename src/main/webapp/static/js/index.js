const btnList = document.querySelectorAll(".btn");
const showDtl = document.querySelector("main div span");
const btnBox = document.querySelectorAll("main .button-box");
const mainTopBox = document.querySelector(".main-top-box");
const asideBox = document.querySelector("aside");
const loginBox = document.querySelector(".login-box");

const registrationBox = document.querySelector("main .registration-box");
const infoBox = document.querySelector(".info-box");
const paymentBox = document.querySelector(".payment-box");

const sectionBox = document.querySelector("section");

const signupBtn = document.querySelector(".signup");
const signinBtn = document.querySelector(".signin");

const userBox = document.querySelector(".login-user-box");
const userSpanBox = document.querySelector(".login-user-box span");


let flag1 = false;
let flag2 = false;
let flag3 = false;

let loginFlag = false;

signupBtn.onclick = () => {
	location.href = "signup";
}

signinBtn.onclick = () => {
	if(loginFlag == true) {
		loginFlag = false;
		userBox.style.display = "none";
		loginBox.style.display = "flex";
		signupBtn.innerHTML = "회원가입";
		signinBtn.innerHTML = "로그인";
		document.querySelector(".password").value = "";
	}else {
		let username = document.querySelector(".username").value;
		let password = document.querySelector(".password").value;
		$.ajax({
			type: "get",
			url: `login?username=${username}&password=${password}`,
			dataType: "text",
			success: (response) => {
				console.log(response);
				let resultID = response.slice(0, 1);
				let name = response.slice(2);
				console.log(resultID);
				console.log(name);
				if(resultID == "0"){
					alert("아이디가 존재하지 않습니다.")
				}else if(resultID == "1"){
					alert("비밀번호가 틀렸습니다.")
				}else {
					alert("로그인 성공!");
					loginFlag = true;
					userBox.style.display = "flex";
					loginBox.style.display = "none";
					signupBtn.innerHTML = "정보 수정";
					signinBtn.innerHTML = "로그아웃";
					userSpanBox.innerHTML = `${name}님 환영합니다.`
				}
			},
			error: (request, status, error) => {
				alert("요청 실패");
			}
		});
	}
	
}

btnList[0].onclick = () => {
	btn2_click_remove();
	btn3_click_remove();
	flag2 = false;
	flag3 = false;
	if(flag1 == false){
		btn1_click();
		asideBox.classList.add("aside-position-move");
		flag1 = true;
	}else{
		btn1_click_remove();
		asideBox.classList.remove("aside-position-move");
		flag1 = false;
	}
	
}

btnList[1].onclick = () => {
	btn1_click_remove();
	btn3_click_remove();
	flag1 = false;
	flag3 = false;
	if(flag2 == false){
		btn2_click();
		asideBox.classList.add("aside-position-move");
		flag2 = true;
	}else{
		btn2_click_remove();
		asideBox.classList.remove("aside-position-move");
		flag2 = false;
	}
	
}

btnList[2].onclick = () => {
	btn1_click_remove();
	btn2_click_remove();
	flag1 = false;
	flag2 = false;
	if(flag3 == false){
		btn3_click();
		asideBox.classList.add("aside-position-move");
		flag3 = true;
	}else{
		btn3_click_remove();
		asideBox.classList.remove("aside-position-move");
		flag3 = false;
	}
}

showDtl.onclick = () => {
	location.href = "showDtl";
}

function btn1_click() {
	registrationBox.classList.toggle("show-registration-box");
	sectionBox.classList.add("section-move-right");
	btnList[2].classList.add("up-btn-box");
	/*btnBox[0].classList.add("move-div-box-right");*/
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox[0].style.width = "400px";
	btnBox[0].style.height = "500px";
	mainTopBox.style.marginBottom = "50px";
}

function btn1_click_remove() {
	registrationBox.classList.remove("show-registration-box");
	sectionBox.classList.remove("section-move-right");
	btnList[2].classList.remove("up-btn-box");
	/*btnBox[0].classList.toggle("move-div-box-right");*/
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox[0].style.width = "140px";
	btnBox[0].style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}

function btn2_click() {
	infoBox.classList.add("show-info-box");
	btnBox[1].style.width = "400px";
	btnBox[1].style.height = "500px";
	mainTopBox.style.marginBottom = "50px";
}

function btn2_click_remove() {
	infoBox.classList.remove("show-info-box");
	btnBox[1].style.width = "140px";
	btnBox[1].style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}

function btn3_click() {
	paymentBox.classList.add("show-payment-box");
	sectionBox.classList.add("section-move-left");
	btnList[0].classList.add("up-btn-box3");
	btnList[1].classList.add("up-btn-box2");
	/*btnBox[2].classList.add("move-div-box-left");*/
	btnBox[2].style.marginLeft = "20px";
	btnBox[2].style.width = "400px";
	btnBox[2].style.height = "500px";
	mainTopBox.style.marginBottom = "50px";
}

function btn3_click_remove() {
	paymentBox.classList.remove("show-payment-box");
	sectionBox.classList.remove("section-move-left");
	btnList[0].classList.remove("up-btn-box3");
	btnList[1].classList.remove("up-btn-box2");
	/*btnBox[2].classList.remove("move-div-box-left");*/
	btnBox[2].style.width = "140px";
	btnBox[2].style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}