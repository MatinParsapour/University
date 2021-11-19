function checkInputs() {
    if (document.getElementById("exampleFormControlInput1").value.length === 0) {
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
    if (document.getElementById("description").value.length === 0) {
        event.preventDefault();
        alert("ورودی را پر کنید")
    }
    if (document.getElementById("quizTime").value.length === 0) {
        event.preventDefault();
        alert("ورودی ها را پر کنید")
    }
}

window.onload = function () {
    var grades = document.getElementsByClassName("points");

    var sum = 0;
    for (let i = 0; i < grades.length; i++) {
        sum += grades[i].grade;
    }
    document.getElementById("gradeSummation").innerHTML = sum;
};

function openQuestionDirectionPad() {
    document.getElementById("selectQuestionDirection").style.display = "block"
}

function closeQuestionDirectionPad() {
    document.getElementById("selectQuestionDirection").style.display = "none"
}

function openQuestionTypePad() {
    document.getElementById("selectQuestionDirection").style.display = "none"
    document.getElementById("questionType").style.display = "block"
}

function closeQuestionTypePad() {
    document.getElementById("selectQuestionDirection").style.display = "block"
    document.getElementById("questionType").style.display = "none"
}

function openQuestionsPad() {
    document.getElementById("displayTeachers").style.display = "block"
    document.getElementById("quizData").style.display = "none"
}

function openEditPad() {
    document.getElementById("displayTeachers").style.display = "none"
    document.getElementById("quizData").style.display = "block"
}

function showOptions(div) {
    if (!checkInputs()) {
        alert("گزینه ها را بنویسید")
    } else {
        var id = div.id;
        var split = id.replace(/[^\d.-]/g, '');
        var inputs = document.getElementsByClassName("optionInput backGroundBlue form-control" + split)
        var newDiv = document.createElement('div')
        newDiv.setAttribute("id", "selectCorrectAnswer" + split)

        var selectTag = "";
        selectTag = "<select id='correctAnswer' name='correctAnswer' th:class='backGroundBlue form-control'>"
        for (let i = 0; i < inputs.length; i++) {
            selectTag += "<option value ='" + inputs[i].value + "'>" + inputs[i].value + "</option>";
        }

        selectTag += "</select>"
        newDiv.innerHTML = selectTag
        var elementById = document.getElementById("selectCorrectAnswer" + split);
        if (elementById === null) {
            document.getElementById("multipleChoicesForm" + split).appendChild(newDiv)
            document.getElementById("correctAnswerLabel" + split).style.display = "block"
        } else {
            document.getElementById("multipleChoicesForm" + split).replaceChild(newDiv, elementById)
            document.getElementById("correctAnswerLabel" + split).style.display = "block"
        }
    }
}

function checkInputs() {
    var inputs = document.getElementsByClassName("optionInput")

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value.length === 0) {
            return false
        }
    }
    return true
}

$('#editDescriptiveForm')
    .each(function () {
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function () {
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() === $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;
$('#editMultipleChoicesForm')
    .each(function () {
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function () {
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() === $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;
$('#editQuizForm')
    .each(function () {
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function () {
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() == $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;

$(document).ready(function (){
    $('#descriptivePage').on("submit",function (){
        updateEntity.abort();
        $("#descriptivePage").submit();
    })

    $("#multipleChociesPage").on("submit",function (){
        updateEntity.abort();
        $("#multipleChociesPage").submit();
    })

    $("#questionsBankPage").on("submit",function (){
        updateEntity.abort();
        $("#questionsBankPage").submit();
    })

    $('form').on("submit",function (event){
        var request = {
            method: "post",
            url: "",
            data: $(this).serialize(),
            success: function (){
                alert("اطلاعات تغییر کرد")
                location.reload();
            },
            error: function (){
                alert("اشتباهی بوجود امده")
            }
        }
        if (event.target.getAttribute("id") === "editDescriptiveForm"){
            request.url = "http://localhost:8080/descriptive-rest/update-question";
        } else if( event.target.getAttribute("id") === "editMultipleChoicesForm"){
            request.url = "http://localhost:8080/multiple-choices-rest/update-question";
        } else {
            request.url = "http://localhost:8080/question-rest/update-question";
        }

        var updateEntity = $.ajax(request);
        event.preventDefault()
    });
})