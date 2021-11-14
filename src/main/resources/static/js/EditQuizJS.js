function checkInputs(){
    if(document.getElementById("exampleFormControlInput1").value.length === 0){
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
    if(document.getElementById("description").value.length === 0){
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
    if(document.getElementById("quizTime").value.length === 0){
        event.preventDefault();
        alert("ورودی ها را پر کنید")
    }
}

function openQuestionDirectionPad(){
    document.getElementById("selectQuestionDirection").style.display = "block"
}

function closeQuestionDirectionPad(){
    document.getElementById("selectQuestionDirection").style.display = "none"
}

function openQuestionTypePad(){
    document.getElementById("selectQuestionDirection").style.display = "none"
    document.getElementById("questionType").style.display = "block"
}

function closeQuestionTypePad(){
    document.getElementById("selectQuestionDirection").style.display = "block"
    document.getElementById("questionType").style.display = "none"
}

function openQuestionsPad(){
    document.getElementById("displayTeachers").style.display = "block"
    document.getElementById("quizData").style.display = "none"
}

function openEditPad(){
    document.getElementById("displayTeachers").style.display = "none"
    document.getElementById("quizData").style.display = "block"
}