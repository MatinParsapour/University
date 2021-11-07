function checkSignUpUsername() {
    if (document.getElementById("signUpUserName").value.length > 4) {
        document.getElementById("signUpUserName").style.borderColor = "white"
    }
}

function checkSignUpPassword() {
    if (document.getElementById("password").value.length > 8 && document.getElementById("password").value.length < 16) {
        document.getElementById("password").style.borderColor = "white"
    }
}

function checkLogInUsername() {
    if (document.getElementById("logInUserName").value.length > 4) {
        document.getElementById("logInUserName").style.borderColor = "white"
    }
}

function checkLogInPassword() {
    if (document.getElementById("logInPassword").value.length > 8 && document.getElementById("password").value.length < 16) {
        document.getElementById("logInPassword").style.borderColor = "white"
    }
}

function checkPasswords() {
    if (document.getElementById("password").value == document.getElementById("repeatPassword").value) {
        document.getElementById("password").style.borderColor = "white"
        document.getElementById("repeatPassword").style.borderColor = "white"
    }
}

function checkRole() {
    if (document.getElementById("student").checked || document.getElementById("teacher").checked) {
        document.getElementById("studentLabel").style.color = "black"
        document.getElementById("teacherLabel").style.color = "black"
    }
}

function checkSex() {
    if (document.getElementById("male").checked || document.getElementById("female").checked) {
        document.getElementById("male").style.color = "black"
        document.getElementById("female").style.color = "black"
    }
}

document.getElementById("signUpButton").addEventListener("click", function (event) {
    if (document.getElementById("signUpUserName").value.length < 4) {
        event.preventDefault()
        alert("نام کاربری باید بیش از 4 کاراکتر باشد")
        document.getElementById("signUpUserName").style.borderColor = "red"
    }
    if (document.getElementById("password").value.length < 8 || document.getElementById("password").value.length > 16) {
        event.preventDefault()
        alert("رمز عبور باید بین 8 تا 16 کاراکتر باشد")
        document.getElementById("password").style.borderColor = "red"
    }
    if (document.getElementById("password").value != document.getElementById("repeatPassword").value) {
        event.preventDefault()
        alert("رمز عبور یکسان نیست")
        document.getElementById("password").style.borderColor = "red"
        document.getElementById("repeatPassword").style.borderColor = "red"
    }
    if (!document.getElementById("student").checked && !document.getElementById("teacher").checked) {
        event.preventDefault()
        alert("دانشجو یا استاد بودن را مشخص کنید")
        document.getElementById("studentLabel").style.color = "red"
        document.getElementById("teacherLabel").style.color = "red"
    }
    if (!document.getElementById("female").checked && !document.getElementById("male").checked) {
        event.preventDefault()
        alert("جنسیت خود را مشخص کنید")
        document.getElementById("male").style.color = "red"
        document.getElementById("female").style.color = "red"
    }
})

document.getElementById("logInButton").addEventListener("click", function (event) {
    if (document.getElementById("logInUserName").value.length < 4) {
        event.preventDefault()
        alert("یک نام کاربری صحیح وارد نمایید")
        document.getElementById("logInUserName").style.borderColor = "red"
    }
    if (document.getElementById("logInPassword").value.length < 8 || document.getElementById("logInPassword").value.length > 16) {
        event.preventDefault()
        alert("یک رمز عبور صحیح وارد نمایید")
        document.getElementById("logInPassword").style.borderColor = "red"
    }
})

var inputPass2 = document.getElementById('pass2'),
    icon1 = document.getElementById('icon1'),
    icon2 = document.getElementById('icon2'),
    icon3 = document.getElementById('icon3');

icon1.onclick = eye();
icon2.onclick = eye();
icon3.onclick = eye();

function eye() {

    if (inputPass2.className === 'active') {
        inputPass2.setAttribute('type', 'text');
        inputPass2.className = '';

    } else {
        inputPass2.setAttribute('type', 'password');
        inputPass2.className = 'active';
    }

}