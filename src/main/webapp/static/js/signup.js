const usernameCheckBtn = document.querySelector("button[type=button]");
const submitBtn = document.querySelector("button[type=submit]");
const password = document.querySelector("#password");
const passwordCheck = document.querySelector("#password-check");
const formBox = document.querySelector("form");

const passwordCheckBox = document.querySelector(".pc");
const passwordErrorSpan = document.querySelector("form span");


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

formBox.addEventListener("submit", function checkPassword(event) {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			alert("동일");
			passwordErrorSpan.style.display = "none";
		}else {
			alert("다름");
			event.preventDefault();
			passwordErrorSpan.style.display = "inline-block";
		}
	}
});

function checkPassword() {
	if(password.value.length != 0){
		if(password.value == passwordCheck.value) {
			alert("동일");
			passwordErrorSpan.style.display = "none";
		}else {
			alert("다름");
			passwordErrorSpan.style.display = "inline-block";
		}
	}
}