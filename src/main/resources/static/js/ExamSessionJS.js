$(document).ready(loadQuestions);

function loadQuestions(){
    var idOfQuiz = $("#idOfQuiz").val();
    $.ajax({
        method: "post",
        url: "http://localhost:8080/question-rest/get-all-quiz-questions",
        data: {idOfQuiz: idOfQuiz},
        dataType: "json",
        success: showQuestions,
        error: showError
    })
}

function showQuestions(questions){
    DisplayList(questions, list_element, rows, current_page);
    SetupPagination(questions, pagination_element, rows);
}

function showError(){
    alert("اشتباهی رخ داده")
}

const list_element = document.getElementById('list');
const pagination_element = document.getElementById('pagination');

let current_page = 1;
let rows = 1;


function DisplayList(items, wrapper, rows_per_page, page) {
    wrapper.innerHTML = "";
    page--;

    let start = rows_per_page * page;
    let end = start + rows_per_page;
    let paginatedItems = items.slice(start, end);

    for (let i = 0; i < paginatedItems.length; i++) {

        let item_element = document.createElement('div');
        wrapper.appendChild(item_element);
        let item;
        if(paginatedItems[i].questionHeader.descriptive == null){
            item = paginatedItems[i].questionHeader.multipleChoices.header
            let select = document.createElement("select")
            select.setAttribute("class","search_categories")
            var noneValueOption = document.createElement("option")
            noneValueOption.value = "";
            noneValueOption.text = "-";
            select.appendChild(noneValueOption)
            for (let j = 0; j < paginatedItems[i].questionHeader.multipleChoices.options.length; j++){
                var option = document.createElement("option");
                option.value = paginatedItems[i].questionHeader.multipleChoices.options[j].options
                option.text = paginatedItems[i].questionHeader.multipleChoices.options[j].options
                select.appendChild(option);
            }
            wrapper.append(select);
        }else{
            item = paginatedItems[i].questionHeader.descriptive.header;
            let textArea = document.createElement("textarea");
            textArea.setAttribute("class","backGroundBlue form-control resize-none")
            textArea.placeholder = "جواب خود را وارد کنید"
            wrapper.appendChild(textArea);
        }
        item_element.classList.add('item');
        item_element.innerText = item;
    }
}

function SetupPagination(items, wrapper, rows_per_page) {
    wrapper.innerHTML = "";

    let page_count = Math.ceil(items.length / rows_per_page);
    for (let i = 1; i < page_count + 1; i++) {
        let btn = PaginationButton(i, items);
        wrapper.appendChild(btn);
    }
}

function PaginationButton(page, items) {
    let button = document.createElement('button');
    button.innerText = page;

    if (current_page == page) button.classList.add('active');

    button.addEventListener('click', function () {
        current_page = page;
        DisplayList(items, list_element, rows, current_page);

        let current_btn = document.querySelector('.pagenumbers button.active');
        current_btn.classList.remove('active');

        button.classList.add('active');
    });

    return button;
}



var minutes = document.getElementById("timer").value
var deadline = new Date().getTime() + minutes * 60 * 1000;

var x = setInterval(function () {

    var now = new Date().getTime();
    var t = deadline - now;
    var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((t % (1000 * 60)) / 1000);
    var hours = Math.floor((t % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    document.getElementById("hour").innerHTML = hours;
    document.getElementById("minute").innerHTML = minutes;
    document.getElementById("second").innerHTML = seconds;
    if (t <= 0) {
        document.getElementById("form").submit()
        clearInterval(x);
        document.getElementById("demo").innerHTML = "TIME UP";
        document.getElementById("hour").innerHTML = '0';
        document.getElementById("minute").innerHTML = '0';
        document.getElementById("second").innerHTML = '0';
    }
}, 1000);