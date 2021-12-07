function openCreateQuizPad() {
    document.getElementById("coursesPad").style.display = "block"
    document.getElementById("closeButton").style.display = "block"
}

function closeCreateQuizPad() {
    document.getElementById("coursesPad").style.display = "none"
    document.getElementById("closeButton").style.display = "none"
}

$(document).ready(function (){
    $("#createQuiz").on("submit",function (event){
        var title = $("#title").val();
        var description = $("#message").val();
        var examDate = $("#examDate").val();
        var fromTime = $("#from").val();
        var toTime = $("#to").val();
        var quizTime = $("#minutes").val();
        var courseId = $("#courseId").val();
        var examDateVar = document.getElementById("examDate").value;
        var fromTimeVar = document.getElementById("from").value;
        var toTimeVar = document.getElementById("to").value;
        const getFromTime = fromTimeVar => new Date(2019, 9, 2, fromTimeVar.substring(0, 2), fromTimeVar.substring(3, 5), 0, 0);
        const getToTime = toTimeVar => new Date(2019, 9, 2, toTimeVar.substring(0, 2), toTimeVar.substring(3, 5), 0, 0);
        var examDateDate = new Date(examDateVar);
        if(examDateDate.getDate() > new Date().getDate() && examDateDate.getMonth() >= new Date().getMonth() && examDateDate.getFullYear() >= new Date().getFullYear()){
            if(getToTime(toTimeVar).getHours() >= getFromTime(fromTimeVar).getHours() && getToTime(toTimeVar).getMinutes() >getFromTime(fromTimeVar).getMinutes()){
                $.ajax({
                    url: "http://localhost:8080/question-rest/create-quiz",
                    type: "post",
                    data: {title: title, description: description, inDate: examDate, fromTime: fromTime, toTime: toTime, quizTime: quizTime, courseId: courseId},
                    success: function (){
                        alert("آزمون ذخیره شد")
                        location.reload();
                    },error: function (){
                        alert("مشکلی بوجود امده")
                    }
                })
            }else {
                alert("زمان پایان ازمون مناسب نیست")
            }
        }else{
            alert("تاریخ برگزاری ازمون باید بزرگتر از تاریخ فعلی باشد")
        }
        event.preventDefault();
    })
})