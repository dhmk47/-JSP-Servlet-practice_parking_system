const btnList = document.querySelectorAll(".btn");
const showDtl = document.querySelector("main div span");
const registrationBox = document.querySelector("main .registration-box");
const btnBox = document.querySelector("main .button-box");
const mainTopBox = document.querySelector("main > div");

function btn1_click() {
	registrationBox.classList.toggle("show-registration-box");
	btnBox.classList.toggle("move-div-box-right");
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox.style.width = "600px";
	btnBox.style.height = "600px";
	mainTopBox.style.marginBottom = "50px";
}

function btn1_click_remove() {
	registrationBox.classList.toggle("show-registration-box");
	btnBox.classList.toggle("move-div-box-right");
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox.style.width = "140px";
	btnBox.style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}

let flag = true;
btnList[0].onclick = () => {
	if(flag == true){
		btn1_click();
		flag = false;		
	}
	
}

/*btnList[0].onblur = () => {
	registrationBox.classList.toggle("show-registration-box");
	btnBox.classList.toggle("move-div-box-right");
	registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";
	btnBox.style.width = "140px";
	btnBox.style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}*/

btnList[1].onclick = () => {
	if(flag == false){
		btn1_click_remove();
		flag = true;	
	}
	
}


btnList[2].onclick = () => {
	
}


showDtl.onclick = () => {
	location.href = "showDtl";
}