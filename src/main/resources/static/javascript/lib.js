function getMyCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
};

function isAdmin() {
	var is_admin = getMyCookie("is_admin");
	return is_admin == "yes";
};

function goToAdmin() {
	if (isAdmin() == true) 
		window.location.href = "/admin";
	else 
		window.location.href = "/users";
};

function randomDoc() {
	var num = Math.floor(Math.random() * 13) + 1  
	window.location.href = "details?name=document_" + num + ".txt"
}