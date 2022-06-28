const navBtn = document.querySelectorAll("header nav ul li");
const showDtl = document.querySelector("main div span");

navBtn[0].onclick = () => {
	location.href = "registrations";
}

navBtn[1].onclick = () => {
	location.href = "showMyInfo";
}

navBtn[2].onclick = () => {
	location.href = "payment";
}

showDtl.onclick = () => {
	location.href = "showDtl";
}