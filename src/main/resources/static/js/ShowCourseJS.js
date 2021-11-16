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
    var startDate = new Date(document.getElementById("newStartDate").value);
    var finishDate = new Date(document.getElementById("newFinishDate").value);
    var now = new Date();
    if(startDate < now){
        event.preventDefault();
        alert("تاریخ انتخابی اشتباه است " +
            "باید بزرگتر از تاریخ فعلی باشد")
    }
    if(finishDate < startDate){
        event.preventDefault();
        alert("تاریخ پایانی دوره باید بعد از تاریخ شروع دوره باشد")
    }
})

$('#editPadForm')
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

$(document).ready(function (){
    $("#editPadForm").on("submit",function (event){
        var courseId = $("#courseId").val();
        var title = $("#exampleFormControlInput1").val();
        var courseCode = $("#courseCode").val();
        var startDate = new Date($("#newStartDate").val());
        var finishDate = new Date($("#newFinishDate").val());
        $.ajax({
            method: "post",
            url: "http://localhost:8080/course-rest/update-course-data",
            data: {courseId: courseId, title: title, courseCode: courseCode, startDate: startDate, finishDate: finishDate},
            success: function (){
                alert("اطلاعات تغیر کرد")
                location.reload();
            },
            error: function (){
                alert("یک اشتباهی بوجود آمده")
            }
        });
        event.preventDefault()
    })
})