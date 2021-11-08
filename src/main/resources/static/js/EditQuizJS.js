function showTitleForm(){
    document.getElementById("titleForm").style.display = "block"
}

function showDescriptionForm(){
    document.getElementById("descriptionForm").style.display = "block"
}

function showTimeForm(){
    document.getElementById("timeForm").style.display = "block"
}

function showButtons(){
    document.getElementById("changeQuizTitle").style.display = "block"
    document.getElementById("changeQuizDescription").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
    document.getElementById("changeQuizTime").style.display = "block"
}
function cancelEdit(){
    document.getElementById("changeQuizTitle").style.display = "none"
    document.getElementById("changeQuizDescription").style.display = "none"
    document.getElementById("changeQuizTime").style.display = "none"
    document.getElementById("descriptionForm").style.display = "none"
    document.getElementById("titleForm").style.display = "none"
    document.getElementById("cancelButton").style.display = "none"
    document.getElementById("timeForm").style.display = "none"
}

function checkQuizTitleInput(){
    if(document.getElementById("newTitle").value.length === 0){
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
}

function checkQuizDescriptionInput(){
    if(document.getElementById("message").value.length === 0){
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
}

function checkQuizTimeInputs(){
    if(document.getElementById("minutes").value.length === 0 || document.getElementById("seconds").value.length === 0){
        event.preventDefault();
        alert("ورودی ها را پر کنید")
    }
}