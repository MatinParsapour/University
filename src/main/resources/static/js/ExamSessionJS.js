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



var timeSecond = document.getElementById("timer").value
var timeMinute = timeSecond * 60
function startGRBTimer(duration, display) {
    var timer = window.name == '' ? duration : window.name,
        hours, minutes, seconds;

    setInterval(function () {
        days = parseInt(timer / (24 * 60 * 60), 10);
        hours = parseInt(timer % (24 * 60 * 60) / (60 * 60), 10);
        minutes = parseInt(timer % (60 * 60) / 60, 10);
        seconds = parseInt(timer % 60, 10);
        hours = hours < 10 ? "0" + hours : hours;
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = hours + "h " + minutes + "m " + seconds + "s";
        --timer;

        if (timer <= 0) {
            timer = duration;
        }

        window.name = timer;
    }, 1000);
}

var display = document.querySelector("#grb");
startGRBTimer(timeMinute, display);