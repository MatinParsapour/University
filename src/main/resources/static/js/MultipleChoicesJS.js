document.getElementById("acceptButton").addEventListener("click",function(event){
    if(document.getElementById("problem").value.length === 0
        || document.getElementById("title").value.length === 0){
        event.preventDefault();
        alert("لطفا تمام ورودی ها را پر کنید")
    }
    if(document.getElementById("grade").value <= 0){
        event.preventDefault();
        alert("میزان نمره باید مثبت باشد و بیشتر از صفر")
    }
    if(document.getElementById("selectCorrectAnswer") === null){
        event.preventDefault();
        alert("با زدن بر روی دکمه 'انتخاب گزینه درست' گزینه درست برای این سوال را انتخاب کنید")
    }

    if(document.getElementsByClassName("optionInput").length !== document.getElementById("correctAnswer").length){
        event.preventDefault()
        alert("با زدن بر روی دکمه 'انتخاب گزینه درست' لیست گزینه ها را به روز کنید و گزینه صحیح را انتخاب کنید")
    }
})

$(document).ready(function () {


    $(".add").click(function () {

        var lastid = $(".element:last").attr("id");
        var split_id = lastid.split("_");
        var nextindex = Number(split_id[1]) + 1;

        $(".element:last").after("<div class='element' id='div_" + nextindex + "'></div>");

        $("#div_" + nextindex).append(
            "<input th:field='${options}' class='optionInput backGroundBlue form-control' type='text' name = 'option' placeholder='گزینه جدید را وارد کنید' id='" + nextindex +
            "'>&nbsp;<input type='button' id='remov_" + nextindex + "' class='remove' value='حذف گزینه'>");
    });


    $('.container').on('click', '.remove', function () {

        var id = this.id;
        var split_id = id.split("_");
        var deleteindex = split_id[1];


        $("#div_" + deleteindex).remove();

    });
});

function setCorrectAnswer(){
    if(!checkInputs()){
        alert("گزینه ها را بنویسید")
    }else {
        var inputs = document.getElementsByClassName("optionInput")
        var newDiv = document.createElement('div')
        newDiv.setAttribute("id", "selectCorrectAnswer")

        var selectTag = "";
        selectTag = "<select id='correctAnswer' class='backGroundBlue form-control' name='correctAnswer'>"
        for (let i = 0; i < inputs.length; i++) {
            selectTag += "<option value ='" + inputs[i].value + "'>" + inputs[i].value + "</option>";
        }
        selectTag += "</select>"
        newDiv.innerHTML = selectTag
        var elementById = document.getElementById("selectCorrectAnswer");
        if (elementById === null) {
            document.getElementById("container").appendChild(newDiv)
        } else {
            document.getElementById("container").replaceChild(newDiv, elementById)
        }
    }

}

function displayTitleLength(){
    document.getElementById("lengthOfInput").innerHTML = 40 - document.getElementById("title").value.length
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