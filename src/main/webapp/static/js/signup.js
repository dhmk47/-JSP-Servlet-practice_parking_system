/* 내가 입력한 유저정보의 값 */
const inputItems = document.querySelectorAll("main input");
const password = document.querySelector("#password");

/* 숨긴 박스 */
const passwordExplainBox = document.querySelector(".explain-password-box");
const passwordErrorSpan = document.querySelector(".password-error");

/* 아이디, 비밀번호 체크 */
const usernameCheckBtn = document.querySelector(".username-check-btn");
const passwordCheck = document.querySelector("#password-check");

/* 이메일 select 객체*/
const selectBox = document.querySelector(".select-box");

/* 회원가입 버튼 */
const signupBtn = document.querySelector(".signup-btn");


// const formBox = document.querySelector("form");
// let specialText = new Array("!", "@", "#", "$", "%", "^", "&", "(", ")", ".", ",", "?", "<", ">", "/", "+");

let usernameCheckFlag = false;

usernameCheckBtn.onclick = () => {
	let username = inputItems[1].value;
	if(username == ""){
		alert("아이디를 입력해 주세요.");
		return;
	}
	$.ajax({
		type: "get",
		url: `/root/username/check?username=${username}`,
		dataType: "text",
		success: (response) => {
			if(response == "true"){
				alert("이미 존재하는 아이디 입니다.");
				usernameCheckFlag = false;
			}else{
				alert("사용 가능한 아이디 입니다.");
				usernameCheckFlag = true;
			}
		},
		error: errorMsg
	});
}

passwordCheck.onblur = () => {
	checkPassword();
}

password.onfocus = () => {
	passwordExplainBox.classList.add("show-explain-password-box");
}

password.onblur = () => {
	passwordExplainBox.classList.remove("show-explain-password-box");
}

signupBtn.onclick = () => {
	for(let i = 0; i < inputItems.length; i++){
		if(isEmpty(inputItems[i].value)){
			alert((i == 0 ? "이름을"
			: i == 1 ? "아이디를"
			: i == 2 ? "비밀번호를"
			: i == 3 ? "비밀번호 확인을"
			: "이메일을") + " 입력해 주세요."
			);
			return;
		}else if(usernameCheckFlag == false){
			alert("아이디 중복체크를 진행해주세요.");
			return;
		}
	}
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			passwordErrorSpan.style.display = "none";
		}else {
			passwordErrorSpan.style.display = "inline-block";
			return;
		}
	}
	let userEmail = inputItems[4].value;
	if(selectBox.options[selectBox.selectedIndex].text == "직접입력"){
		let checkEmail = (/^[\w*]+@[\w]+.com$/g).test(userEmail);
		if(!checkEmail){
			alert("이메일 형식을 지켜주세요.");
			return;
		}
	}else{
		let checkEmail = (/^[\w*]+$/).test(userEmail);
		if(!checkEmail){
			alert("이메일 형식을 지켜주세요.");
			return;
		}
	}
	submit();
}

/*formBox.addEventListener("submit", function checkPassword(event) {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			passwordErrorSpan.style.display = "none";
		}else {
			event.preventDefault();
			passwordErrorSpan.style.display = "inline-block";
		}
	}
	let userEmail = emailInputBox.value;
	if(selectBox.options[selectBox.selectedIndex].text == "직접입력"){
		let checkEmail = (/^[\w*]+@[\w]+.com$/g).test(userEmail);
		if(!checkEmail){
			alert("이메일 형식을 지켜주세요.");
			event.preventDefault();
		}
	}else{
		let checkEmail = (/^[\w*]+$/).test(userEmail);
		if(!checkEmail){
			alert("이메일 형식을 지켜주세요.");
			event.preventDefault();
		}
	}
});*/

function submit() {
	$.ajax({
		type: "post",
		url: "/root/signup",
		data: {
			name: inputItems[0].value,
			username: inputItems[1].value,
			password: inputItems[2].value,
			email: inputItems[4].value,
			selectEmail: selectBox.options[selectBox.selectedIndex].text
		},
		dataType: "text",
		success: (response) => {
			if(response == "true"){
				alert("회원가입 성공!\n메인페이지로 이동합니다!");
				location.replace("/root/index");
			}else {
				alert("회원가입 실패")
			}
		},
		error: errorMsg
	});
}

function errorMsg(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

function checkPassword() {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			passwordErrorSpan.style.display = "none";
		}else {
			passwordErrorSpan.style.display = "inline-block";
		}
	}
}

function isEmpty(value) {
	return value == "" || value == null || typeof value == undefined;
}