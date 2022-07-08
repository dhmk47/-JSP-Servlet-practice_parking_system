const inputItems = document.querySelectorAll("main input");
const selectBoxes = document.querySelectorAll("main select");

const modifyUserInfoButton = document.querySelector(".userinfo-modify-btn");

const deleteCarButton = document.querySelector(".delete-car-btn");

load();

modifyUserInfoButton.onclick = () => {
	
	for(let i = 0; i < inputItems.length; i++){
		if(isEmpty(inputItems[i].value)) {
			alert(
				(i == 0 ? "이름을"
				: i == 1 ? "비밀번호를"
				: i == 2 ? "비밀번호 확인을"
				: "이메일을") + " 입력해주세요."
			);
			return;
		}
	}
	let userEmail = inputItems[3].value;
	if(selectBoxes[0].options[selectBoxes[0].selectedIndex].text == "직접입력"){
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
		userEmail += selectBoxes[0].options[selectBoxes[0].selectedIndex].text;
	}
	
	$.ajax({
		type: "post",
		url: "/root/modifyAccountInfo",
		data: {
			name: inputItems[0].value,
			password: inputItems[1].value,
			email: userEmail
		},
		dataType: "text",
		success: (response) => {
			if(response == "true") {
				alert("정보 수정 완료");
				location.replace("/root/index");
			}else {
				alert("정보 수정 오류");
			}
		},
		error: errorMessage
	});
}

deleteCarButton.onclick = () => {
	
}

function load() {
	$.ajax({
		type: "get",
		url: "/root/api/v1/userinfo/session",
		dataType: "json",
		success: (response) => {
			alert("유저 요청 성공");
			inputItems[0].value = `${response.name}`;
			inputItems[3].value = `${response.email}`;
			
			$.ajax({
				type: "get",
				url: "/root/api/v1/carListInfo/session",
				dataType: "json",
				success: (response) => {
					alert("자동차 정보 요청 성공");
					
					console.log(response);
					console.log(response.length);
					
					if(response.length == 0){
						selectBoxes[1].innerHTML = `<option>등록된 차량이 없습니다.</option>`;
					}else {
						selectBoxes[1].innerHTML = `<option>차량을 선택해 주세요.</option>`;
						for(let i = 0; i < response.length; i++){
							console.log(response[i].car_number);
							selectBoxes[1].innerHTML += `
							<option>${response[i].car_number}</option>
							`;
						}
					}
					
				},
				error: errorMessage
			});
		},
		error: errorMessage
	});
}

function errorMessage(request, status, error) {
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);
}

function isEmpty(value) {
	return value == null || value == "" || value == undefined;
}