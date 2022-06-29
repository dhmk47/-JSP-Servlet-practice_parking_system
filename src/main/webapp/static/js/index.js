const btnList = document.querySelectorAll(".btn");
const showDtl = document.querySelector("main div span");
const btnBox = document.querySelectorAll("main .button-box");
const mainTopBox = document.querySelector("main > div");

const registrationBox = document.querySelector("main .registration-box");
const infoBox = document.querySelector(".info-box");
const paymentBox = document.querySelector(".payment-box");

function btn1_click() {
	registrationBox.classList.toggle("show-registration-box");
	btnBox[0].classList.add("move-div-box-right");
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox[0].style.width = "600px";
	btnBox[0].style.height = "600px";
	mainTopBox.style.marginBottom = "50px";
}

function btn1_click_remove() {
	registrationBox.classList.remove("show-registration-box");
	btnBox[0].classList.toggle("move-div-box-right");
	/*registrationBox.style.width = "600px";
	registrationBox.style.height = "600px";*/
	btnBox[0].style.width = "140px";
	btnBox[0].style.height = "50px";
	mainTopBox.style.marginBottom = "300px";
}

function btn2_click() {
	infoBox.classList.add("show-info-box");
	btnBox[1].style.width = "600px";
	btnBox[1].style.height = "600px";
}

function btn2_click_remove() {
	infoBox.classList.remove("show-info-box");
	btnBox[1].style.width = "140px";
	btnBox[1].style.height = "50px";
}

function btn3_click() {
	paymentBox.classList.add("show-payment-box");
	btnBox[2].classList.add("move-div-box-left");
	btnBox[2].style.width = "600px";
	btnBox[2].style.height = "600px";
}

function btn3_click_remove() {
	paymentBox.classList.remove("show-payment-box");
	btnBox[2].classList.remove("move-div-box-left");
	btnBox[2].style.width = "140px";
	btnBox[2].style.height = "50px";
}

let flag1 = false;
let flag2 = false;
let flag3 = false;

btnList[0].onclick = () => {
	btn2_click_remove();
	btn3_click_remove();
	flag2 = false;
	flag3 = false;
	if(flag1 == false){
		btn1_click();
		flag1 = true;
	}else{
		btn1_click_remove();
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
		flag2 = true;
	}else{
		btn2_click_remove();
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
		flag3 = true;
	}else{
		btn3_click_remove();
		flag3 = false;
	}
}


showDtl.onclick = () => {
	location.href = "showDtl";
}