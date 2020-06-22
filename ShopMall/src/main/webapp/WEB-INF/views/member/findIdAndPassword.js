/**
 * 
 */

function find_id_form() {
	var url = "find_id_form";
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, " + 
			"height=600, top=300, left=300");
}


function findMemberId() {
	
	if (document.findId.name.value == "") {
		alert("이름을 입력하세요.")
		document.findId.name.focus();
	} else if (document.findId.email.value == "") {
		alert("이메일을 입력하세요.")
		document.findId.email.focus();
	} else {
		document.findId.action = "find_id";
		document.findId.submit();
	}
	
}


function findPassword() {
	
	if (document.findPW.id.value == "") {
		alert("아이디를 입력하세요.");
		document.findPW.id.focus();
	} else if (document.findPW.name.value == ""){
		alert("이름을 입력하세요.");
		document.findPW.name.focus();
	} else if (document.findPW.email.value == "") {
		alert("이메일을 입력하세요.");
		document.findPW.email.focus();
	} else {
		document.findPW.action = "find_password";
		document.findId.submit();
	}
	
}

