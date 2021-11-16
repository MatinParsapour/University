function edit() {
    document.getElementById("changeFirstNameButton").style.display = "block"
    document.getElementById("changeEmailButton").style.display = "block"
    document.getElementById("changeLastNameButton").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
}

$('form')
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
    $('#updateUserInformation').on("submit",function (event){
        var firstName = $("#exampleFormControlInput1").val();
        var lastName = $("#userLastName").val();
        var birthDay = new Date($("#userBirthDay").val());
        var email = $("#userEmail").val();
        var nationalCode = $("#userNationalCode").val();
        var id = $("#userId").val();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/update-user/update-user-information",
            data: {firstName: firstName, lastName: lastName, birthday: birthDay, email: email, nationalCode: nationalCode, id: id},
            success: function (){
                alert("اطلاعات تغییر کرد")
                location.reload();
            },
            error: function (){
                alert("یه اشتباهی بوجود امده")
            }
        });
        event.preventDefault()
    })
})