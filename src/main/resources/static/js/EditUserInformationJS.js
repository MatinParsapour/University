function checkFirstNameInput() {
    if (document.getElementById("firstNameInput").value.length === 0) {
        event.preventDefault();
        alert("The input is empty")
    }
}

function checkLastNameInput() {
    if (document.getElementById("lastNameInput").value.length === 0) {
        event.preventDefault();
        alert("The input is empty")
    }
}

function checkEmailInput() {
    if (document.getElementById("emailInput").value.length === 0) {
        event.preventDefault();
        alert("The input is empty")
    }
}

function edit() {
    document.getElementById("changeFirstNameButton").style.display = "block"
    document.getElementById("changeEmailButton").style.display = "block"
    document.getElementById("changeLastNameButton").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
}

function cancelEdit() {
    document.getElementById("changeFirstNameButton").style.display = "none"
    document.getElementById("changeEmailButton").style.display = "none"
    document.getElementById("changeLastNameButton").style.display = "none"
    document.getElementById("cancelButton").style.display = "none"
    document.getElementById("firstNameForm").style.display = "none"
    document.getElementById("lastNameForm").style.display = "none"
    document.getElementById("emailForm").style.display = "none"
    document.getElementById("firstName").style.display = "block"
    document.getElementById("lastName").style.display = "block"
    document.getElementById("email").style.display = "block"
}

function showFirstNameForm() {
    document.getElementById("firstNameForm").style.display = "block"
    document.getElementById("firstName").style.display = "none"
}

function showLastNameForm() {
    document.getElementById("lastNameForm").style.display = "block"
    document.getElementById("lastName").style.display = "none"
}

function showEmailForm() {
    document.getElementById("emailForm").style.display = "block"
    document.getElementById("email").style.display = "none"
}