const usernameCheckBtn = document.querySelector("button[type=button]");
const submitBtn = document.querySelector("button[type=submit]");
const password = document.querySelector("#password");
const passwordCheck = document.querySelector("#password-check");
const formBox = document.querySelector("form");

const passwordExplainBox = document.querySelector(".explain-password-box");
const passwordCheckBox = document.querySelector(".pc");
const passwordErrorSpan = document.querySelector(".password-error");

const emailInputBox = document.querySelector("#email");
const selectBox = document.querySelector(".select-box");

let specialText = new Array("!", "@", "#", "$", "%", "^", "&", "(", ")", ".", ",", "?", "<", ">", "/", "+");


usernameCheckBtn.onclick = () => {
	
}

passwordCheck.onblur = () => {
	checkPassword();
}

/*formBox.onclick = () => {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			return true;
		}else {
			return false;
		}
	}
}*/

password.onfocus = () => {
	passwordExplainBox.classList.add("show-explain-password-box");
}

password.onblur = () => {
	passwordExplainBox.classList.remove("show-explain-password-box");
}

formBox.addEventListener("submit", function checkPassword(event) {
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
			alert("이메일 형식을 지켜주세요.")
			event.preventDefault();
		}
	}else{
		let checkEmail = (/^[\w*]+$/).test(userEmail);
		if(!checkEmail){
			alert("이메일 형식을 지켜주세요.")
			event.preventDefault();
		}
		/*else {
			let value = selectBox.options[selectBox.selectedIndex].text;
			emailInputBox.value += value;
		}*/
	}
	
});

function checkPassword() {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			passwordErrorSpan.style.display = "none";
		}else {
			passwordErrorSpan.style.display = "inline-block";
		}
	}
}