$(document).ready(function (){
    $('form').on("submit",function (event){
        $.ajax({
            method: "post",
            url: "http://localhost:8080/student-result/change-grade",
            data: $(this).serialize(),
            success: function (){
                alert("نمره برای دانشجو ثبت شد")
                location.reload();
            },
            error: function (){
                alert("مشکلی در ثبت نمره پیش امده و نمره ثبت نشده")
            }
        })
        event.preventDefault();
    })
})