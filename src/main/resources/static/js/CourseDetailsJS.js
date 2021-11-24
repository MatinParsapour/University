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
        var courseId = $("#courseId").val()
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
        event.preventDefault();
    })
})