function showChooseStudentField() {
    document.getElementById("addStudentButton").style.display = "none"
    document.getElementById("studentsList").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
}

function hideChooseStudentField() {
    document.getElementById("addStudentButton").style.display = "block"
    document.getElementById("studentsList").style.display = "none"
    document.getElementById("cancelButton").style.display = "none"
}

function editPad() {
    document.getElementById("editPad").style.display = "block"
}

function closeEditPad() {
    document.getElementById("editPad").style.display = "none"
}

$(document).ready(function () {
    $('input').focusin(function () {
        $('label').transition({
            x: '80px'
        }, 500, 'ease').next()
            .transition({
                x: '5px'
            }, 500, 'ease');
        setTimeout(function () {
            $('label').next().addClass('move-pen');
        }, 100);

    });

    $('input').focusout(function () {
        $('label').transition({
            x: '0px'
        }, 500, 'ease').next()
            .transition({
                x: '-100px'
            }, 500, 'ease').removeClass('move-pen');
    });
})

function openStudentsPad() {
    document.getElementById("studentsPad").style.display = "block"
    document.getElementById("closeButton").style.display = "block"
    document.getElementById("editPadInfo").style.display = "none"
}

function closeStudentsPad() {
    document.getElementById("studentsPad").style.display = "none"
    document.getElementById("closeButton").style.display = "none"
}
function openTeachersPad() {
    document.getElementById("teachersPad").style.display = "block"
    document.getElementById("closeTeachersPadButton").style.display = "block"
}

function closeTeachersPad() {
    document.getElementById("teachersPad").style.display = "none"
    document.getElementById("closeTeachersPadButton").style.display = "none"
}

document.getElementById("changeButton").addEventListener("click",function (event){
    let valueStartDate = document.getElementById("newStartDate").value;
    let valueFinishDate = document.getElementById("newFinishDate").value;
    var startDate = new Date(valueStartDate);
    var finishDate = new Date(valueFinishDate);
    if(startDate.getDate() < new Date().getDate() || startDate.val() === ""){
        event.preventDefault();
        alert("تاریخ انتخابی اشتباه است " +
            "باید بزرگتر از تاریخ فعلی باشد")
    }
    if(finishDate.getDate() < startDate.getDate() || finishDate.val() === ""){
        event.preventDefault();
        alert("تاریخ پایانی دوره باید بعد از تاریخ شروع دوره باشد")
    }
})