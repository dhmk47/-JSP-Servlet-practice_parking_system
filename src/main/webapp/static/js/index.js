/* main */
const showDtl = document.querySelector("main div span");
const mainTopBox = document.querySelector(".main-top-box");

/*===========================================================*/
/* aside */
const asideBox = document.querySelector("aside");
const loginBox = document.querySelector(".login-box");
const usernameInputBox = document.querySelector(".username");
const passwordInputBox = document.querySelector(".password");
const signupBtn = document.querySelector(".signup");
const signinBtn = document.querySelector(".signin");

/* 로그인 성공시 */
const userBox = document.querySelector(".login-user-box");
const userSpanBox = document.querySelector(".login-user-box span");

/* 로그인 하지않고 버튼 클릭시*/
const notLoginErrorBox = document.querySelector(".error-msg");
/*===========================================================*/
/* section */
const sectionBox = document.querySelector("section");

/* button */
const btnBox = document.querySelectorAll("main .button-box");
const btnList = document.querySelectorAll(".btn");

const registrationBox = document.querySelector("main .registration-box");
const registerCarBtn = document.querySelector(".register-car-btn");
const carNumberInputBox = document.querySelector("#car-number");
const selectBox = document.querySelector(".select-option select");

const infoBox = document.querySelector(".info-box");
const showMyInfoBtn = document.querySelector(".show-my-info-btn");

const paymentBox = document.querySelector(".payment-box");
const payBtn = document.querySelector(".pay-btn");

let flag1 = false;
let flag2 = false;
let flag3 = false;

let loginFlag = false;
let loginUsername = null;

registerCarBtn.onclick = () => {
	let carNumber = carNumberInputBox.value;
	let parkingTicket = selectBox.options[selectBox.selectedIndex].value;
	
	$.ajax({
		type: "post",
		url: "/root/registerCar",
		data: {
			carNumber: carNumber,
			parkingTicket: parkingTicket,
			username: loginUsername
		},
		dataTyep: "text",
		success: (response) => {
			if(response == "true"){
				alert("등록 성공");
			}else {
				alert("등록 실패");
			}
		},
		error: (request, status, error) => {
			alert("요청 실패");
			console.log(request.status);
			console.log(request.responseText);
			console.log(error);
		}
	});
}

signupBtn.onclick = () => {
	if(loginFlag){
		location.href = "";
	}else {
	location.href = "signup";
	}
}

signinBtn.onclick = () => {
	if(loginFlag == true) {
		loginFlag = false;
		userBox.style.display = "none";
		loginBox.style.display = "flex";
		signupBtn.innerHTML = "회원가입";
		signinBtn.innerHTML = "로그인";
		passwordInputBox.value = "";
	}else {
		let username = usernameInputBox.value;
		let password = passwordInputBox.value;
		$.ajax({
			type: "get",
			url: `login?username=${username}&password=${password}`,
			dataType: "text",
			success: (response) => {
				let resultID = response.slice(0, 1);
				let name = response.slice(2);
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
					notLoginErrorBox.style.display = "none";
					loginUsername = username;
				}
			},
			error: (request, status, error) => {
				alert("요청 실패");
			}
		});
	}
	
}

btnList[0].onclick = () => {
	if(loginFlag){
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
	}else {
		notLoginErrorBox.style.display = "inline-block";
		usernameInputBox.focus();
	}
	
	
}

btnList[1].onclick = () => {
	if(loginFlag){
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
	}else {
		notLoginErrorBox.style.display = "inline-block";
		usernameInputBox.focus();
	}
	
}

btnList[2].onclick = () => {
	if(loginFlag){
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
	}else{
		notLoginErrorBox.style.display = "inline-block";
		usernameInputBox.focus();
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