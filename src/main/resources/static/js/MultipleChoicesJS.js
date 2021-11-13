document.getElementById("acceptButton").addEventListener("click",function(event){
    if(document.getElementById("problem").value.length === 0
        || document.getElementById("title").value.length === 0
        || document.getElementById("grade").value === 0){
        event.preventDefault();
        alert("لطفا تمام ورودی ها را پر کنید")
    }
})

$(document).ready(function () {


    $(".add").click(function () {


        var total_element = $(".element").length;


        var lastid = $(".element:last").attr("id");
        var split_id = lastid.split("_");
        var nextindex = Number(split_id[1]) + 1;

        $(".element:last").after("<div class='element' id='div_" + nextindex + "'></div>");

        $("#div_" + nextindex).append(
            "<input th:field='${options}' type='text' name = 'option' placeholder='گزینه جدید را وارد کنید' id='txt_" + nextindex +
            "'>&nbsp;<input type='button' id='remove_" + nextindex + "' class='remove' value='حذف گزینه'>");

    });


    $('.container').on('click', '.remove', function () {

        var id = this.id;
        var split_id = id.split("_");
        var deleteindex = split_id[1];


        $("#div_" + deleteindex).remove();

    });
});