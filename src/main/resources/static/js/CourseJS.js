(function () {
    'use strict'
    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()


document.getElementById("createCourseButton").addEventListener("click",function (event){
    let valueStartDate = document.getElementById("validationCustomUsername").value;
    let valueFinishDate = document.getElementById("validationCustom03").value;
    var startDate = new Date(valueStartDate);
    var finishDate = new Date(valueFinishDate);
    if(startDate.getDate() < new Date().getDate()){
        event.preventDefault();
        alert("تاریخ انتخابی اشتباه است " +
            "باید بزرگتر از تاریخ فعلی باشد")
    }
    if(finishDate.getDate() < startDate.getDate()){
        event.preventDefault();
        alert("تاریخ پایانی دوره باید بعد از تاریخ شروع دوره باشد")
    }
})

function openTeachersPad() {
    document.getElementById("teachersPad").style.display = "block"
    document.getElementById("closeButton").style.display = "block"
}

function closeCoursesPad() {
    document.getElementById("teachersPad").style.display = "none"
    document.getElementById("closeButton").style.display = "none"
}


