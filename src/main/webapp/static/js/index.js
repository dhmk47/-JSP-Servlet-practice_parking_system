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
const nameInInfoBox = document.querySelector(".info-box h1");
const showUserCarInfoSelectBox = document.querySelector(".info-box select");
const parkingTicketType = document.querySelector(".ticket-type");
const overtimePayButton = document.querySelector(".overtime-payment-btn");
const alreadyRegisteredCarTicketUpdateButton = document.querySelector(".already-register-car-btn");

const paymentBox = document.querySelector(".payment-box");
const userInfoInPaymentBox = document.querySelector(".payment-box h1");
const payBtn = document.querySelector(".pay-btn");

let flag1 = false;
let flag2 = false;
let flag3 = false;

let loginFlag = false;
let paymentFlag = false;
let noParkingTicket = false;

let carListObject = null;
let carObj = null

overtimePayButton.style.opacity = "0.3";
overtimePayButton.style.cursor = "default";
alreadyRegisteredCarTicketUpdateButton.style.opacity = "0.3";
alreadyRegisteredCarTicketUpdateButton.style.cursor = "default";
load();

showUserCarInfoSelectBox.onchange = () => {
	let select = showUserCarInfoSelectBox.options[showUserCarInfoSelectBox.selectedIndex].value;
	carObj = carListObject[select];
	if(select > -1 && carObj.ticket_dtl != null){
		
		if(carObj.remaining_day < 0){
			parkingTicketType.innerHTML = `
			주차권: ${carObj.ticket_dtl}
			-> 초과 일: ${carObj.remaining_day * -1}<br>초과 시간: ${carObj.remaining_hour}`;
			overtimePayButton.style.opacity = "1";
			overtimePayButton.style.cursor = "pointer";
			paymentFlag = true;
		}else {
			parkingTicketType.innerHTML = `
			주차권: ${carObj.ticket_dtl}
			-> 남은 일: ${carObj.remaining_day}<br>남은 시간: ${carObj.remaining_hour}`;
			overtimePayButton.style.opacity = "0.3";
			overtimePayButton.style.cursor = "default";
			paymentFlag = false;
		}
		
	}else {
		parkingTicketType.innerHTML = "주차권: ";
		overtimePayButton.style.opacity = "0.3";
		overtimePayButton.style.cursor = "default";
		paymentFlag = false;
	}
	if(select > -1 && carObj.ticket_dtl == null){
		alreadyRegisteredCarTicketUpdateButton.style.opacity = "1.0";
		alreadyRegisteredCarTicketUpdateButton.style.cursor = "pointer";
		noParkingTicket = true;
		
	}else {
		alreadyRegisteredCarTicketUpdateButton.style.opacity = "0.3";
		alreadyRegisteredCarTicketUpdateButton.style.cursor = "default";
		noParkingTicket = false;
	}
	
}

overtimePayButton.onclick = () => {
	if(paymentFlag){
		let price = (carObj.remaining_hour + (carObj.remaining_day * -1 * 24)) * 500;
		let flag = confirm(`지불금액: ${price}원입니다.\n지불하시겠습니까?`);
		if(flag){
			parkingTicketCancel();
			alert("지불 완료!");
		}
	}
}

alreadyRegisteredCarTicketUpdateButton.onclick = () => {
	if(noParkingTicket){
		carNumberInputBox.value = carObj.car_number;
		btnList[0].click();
	}else{
		alert("에러");
	}
}

registerCarBtn.onclick = () => {
	let carNumber = carNumberInputBox.value;
	let parkingTicket = selectBox.options[selectBox.selectedIndex].value;
	
	$.ajax({
		type: "post",
		url: "/root/registerCar",
		data: {
			carNumber: carNumber,
			parkingTicket: parkingTicket
		},
		dataTyep: "text",
		success: (response) => {
			if(response == "true"){
				alert("등록 성공");
				location.replace("/root/index");
			}else {
				alert("등록 실패");
			}
		},
		error: errorMessage
	});
}

signupBtn.onclick = () => {
	if(loginFlag){
		location.href = "modifyAccountInfo";
	}else {
	location.href = "signup";
	}
}

signinBtn.onclick = () => {
	if(loginFlag == true) {
		$.ajax({
			type: "get",
			url: "/root/logout",
			dataType: "text",
			success: (response) => {
				if(response == "true"){
				alert("로그아웃됨");
				location.replace("/root/index");
				}else {
					alert("로그아웃 오류");
				}
			},
			error: errorMessage
		});
	}else {
		let username = usernameInputBox.value;
		let password = passwordInputBox.value;
		$.ajax({
			type: "get",
			url: `login?username=${username}&password=${password}`,
			dataType: "text",
			success: (response) => {
				if(response == "null"){
					alert("계정이 옳바르지 않습니다.");
				}else {
					alert("로그인 성공!");
					location.replace("/root/index");
				}
			},
			error: errorMessage
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

function load() {
	$.ajax({
		type: "get",
		url: "/root/api/v1/userinfo/session",
		dataType: "json",
		success: (response) => {
			if(response != null){
				loginFlag = true;
				userBox.style.display = "flex";
				loginBox.style.display = "none";
				signupBtn.innerHTML = "정보 수정";
				signinBtn.innerHTML = "로그아웃";
				userSpanBox.innerHTML = `${response.name}님 환영합니다.`;
				notLoginErrorBox.style.display = "none";
				
				nameInInfoBox.innerHTML = `${response.name}님의 정보`;
				userInfoInPaymentBox.innerHTML = `${response.name}님의 요금현황`;
				$.ajax({
					type: "get",
					url: "/root/api/v1/load/carinfo",
					dataType: "json",
					success: (response) => {
						alert("차량 정보 불러오기 성공");
						
						if(response.length == 0){
							showUserCarInfoSelectBox.innerHTML = `<option>차량을 먼저 등록해주세요.</option>`;
						}else{
							carListObject = response;
						
							showUserCarInfoSelectBox.innerHTML = `<option value=-1>차량을 선택해주세요.</option>`
							for(let i = 0; i < response.length; i++){
								showUserCarInfoSelectBox.innerHTML += `
								<option value=${i}>${response[i].car_number}</option>
								`;
							}	
						}
						
					},
					error: errorMessage
				})
			}else {
				loginFlag = false;
				userBox.style.display = "none";
				loginBox.style.display = "flex";
				signupBtn.innerHTML = "회원가입";
				signinBtn.innerHTML = "로그인";
				passwordInputBox.value = "";
				alert("세련이 만료되어 로그아웃 되었습니다.");
			}
		},
		error: errorMessage
	})
}

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

function parkingTicketCancel() {
	$.ajax({
		type: "put",
		url: `/root/api/v1/ticket/cancel?car_code=${carObj.car_code}`,
		dataType: "text",
		success: (response) => {
			if(response == "true"){
				alert("주차권이 해제되었습니다.");
			}else{
				alert("주차권 해제중 오류 발생.");
			}
		},
		error: errorMessage
	});
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