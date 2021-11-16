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

function showOptions(){
    if(!checkInputs()){
        alert("گزینه ها را بنویسید")
    }else {
        var inputs = document.getElementsByClassName("optionInput")
        var newDiv = document.createElement('div')
        newDiv.setAttribute("id", "selectCorrectAnswer")

        var selectTag = "";
        selectTag = "<select id='correctAnswer' name='correctAnswer' class='backGroundBlue form-control'>"
        for (let i = 0; i < inputs.length; i++) {
            selectTag += "<option value ='" + inputs[i].value + "'>" + inputs[i].value + "</option>";
        }
        selectTag += "</select>"
        newDiv.innerHTML = selectTag
        var elementById = document.getElementById("selectCorrectAnswer");
        if (elementById === null) {
            document.getElementById("multipleChoicesForm").appendChild(newDiv)
            document.getElementById("correctAnswerLabel").style.display = "block"
        } else {
            document.getElementById("multipleChoicesForm").replaceChild(newDiv, elementById)
            document.getElementById("correctAnswerLabel").style.display = "block"
        }
    }
}

function checkInputs(){
    var inputs = document.getElementsByClassName("optionInput")

    for(let i = 0; i < inputs.length; i++){
        if (inputs[i].value.length === 0){
            return false
        }
    }
    return true
}

$('#editDescriptiveForm')
    .each(function(){
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function(){
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() == $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;
$('#editMultipleChoicesForm')
    .each(function(){
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function(){
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() == $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;
$('#editQuizForm')
    .each(function(){
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function(){
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() == $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;