const btnList = document.querySelectorAll(".btn");
const showDtl = document.querySelector("main div span");
const btnBox = document.querySelectorAll("main .button-box");
const mainTopBox = document.querySelector(".main-top-box");
const loginBox = document.querySelector("aside");

const registrationBox = document.querySelector("main .registration-box");
const infoBox = document.querySelector(".info-box");
const paymentBox = document.querySelector(".payment-box");

const sectionBox = document.querySelector("section");

const signupBtn = document.querySelector(".signup");
const signinBtn = document.querySelector(".signin");


let flag1 = false;
let flag2 = false;
let flag3 = false;

let signinFlag = false;

signupBtn.onclick = () => {
	location.href = "signup";
}

btnList[0].onclick = () => {
	btn2_click_remove();
	btn3_click_remove();
	flag2 = false;
	flag3 = false;
	if(flag1 == false){
		btn1_click();
		loginBox.classList.add("aside-position-move");
		flag1 = true;
	}else{
		btn1_click_remove();
		loginBox.classList.remove("aside-position-move");
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
		loginBox.classList.add("aside-position-move");
		flag2 = true;
	}else{
		btn2_click_remove();
		loginBox.classList.remove("aside-position-move");
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
		loginBox.classList.add("aside-position-move");
		flag3 = true;
	}else{
		btn3_click_remove();
		loginBox.classList.remove("aside-position-move");
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