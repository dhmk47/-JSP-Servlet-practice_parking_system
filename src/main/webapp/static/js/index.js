const btnList = document.querySelectorAll(".button-box button");
const showDtl = document.querySelector("main div span");
const registrationBox = document.querySelector(".registration-box");
const btnBox = document.querySelector(".button-box");
const mainTopBox = document.querySelector("main > div");

btnList[0].onfocus = () => {
	registrationBox.classList.toggle("show-registration-box");
	btnBox.style.width = "800px";
	btnBox.style.height = "800px";
	mainTopBox.style.marginBottom = "50px";
	
	
}

btnList[0].onblur = () => {
	registrationBox.classList.toggle("show-registration-box");
	btnBox.style.width = "100px";
	btnBox.style.height = "100px";
	mainTopBox.style.marginBottom = "300px";
}

btnList[1].onfocus = () => {
	
}

btnList[1].onblur = () => {
	
}

btnList[2].onfocus = () => {
	
}

btnList[2].onblur = () => {
	
}

showDtl.onclick = () => {
	location.href = "showDtl";
}