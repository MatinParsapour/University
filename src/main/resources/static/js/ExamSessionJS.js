$(document).ready(loadQuestions);

var questionList;

function loadQuestions() {
    var idOfQuiz = $("#idOfQuiz").val();
    $.ajax({
        method: "post",
        url: "http://localhost:8080/question-rest/get-all-quiz-questions",
        data: {idOfQuiz: idOfQuiz},
        dataType: "json",
        success: showQuestions ,
        error: showError
    })
}

function showQuestions(questions) {
    questionList = questions;
    DisplayList(questions, list_element, rows, current_page);
    SetupPagination(questions, pagination_element, rows);
}

function showError() {
    alert("اشتباهی رخ داده")
}

window.onbeforeunload = function (){
    setToLocalStorage()
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
        if (paginatedItems[i].type === "MultipleChoices") {
            let hiddenInput = document.createElement("input");
            hiddenInput.setAttribute("type", "hidden")
            hiddenInput.setAttribute("value", paginatedItems[i].id)
            hiddenInput.setAttribute("class", "MCQId")
            item = paginatedItems[i].header
            let select = document.createElement("select")
            select.setAttribute("id", paginatedItems[i].id)
            select.setAttribute("class", "search_categories")
            var noneValueOption = document.createElement("option")
            noneValueOption.value = "";
            noneValueOption.text = "-";
            select.appendChild(noneValueOption)
            for (let j = 0; j < paginatedItems[i].options.length; j++) {
                var option = document.createElement("option");
                option.value = paginatedItems[i].options[j].options
                option.text = paginatedItems[i].options[j].options
                select.appendChild(option);
            }
            wrapper.append(hiddenInput);
            wrapper.append(select);
        } else {
            let hiddentInput = document.createElement("input")
            hiddentInput.setAttribute("type", "hidden")
            hiddentInput.setAttribute("value", paginatedItems[i].id)
            hiddentInput.setAttribute("class", "DQId");
            item = paginatedItems[i].header;
            let textArea = document.createElement("textarea");
            textArea.setAttribute("class", "backGroundBlue form-control resize-none")
            textArea.setAttribute("id", paginatedItems[i].id)
            textArea.placeholder = "جواب خود را وارد کنید"
            wrapper.append(hiddentInput)
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
        setToLocalStorage()
        current_page = page;
        DisplayList(items, list_element, rows, current_page);

        let current_btn = document.querySelector('.pagenumbers button.active');
        current_btn.classList.remove('active');

        button.classList.add('active');
        setToInput()
    });
    return button;
}

function checkStorage() {
    var list = [];
    for (let j = 0; j < questionList.length; j++) {
        list.push(questionList[j].id)
    }
    var archive = [],
        keys = Object.keys(localStorage),
        i = keys.length;

    while (i--){
        archive.push(parseInt(keys[i]))
    }
    for (let h = 0; h < list.length; h++) {
        if(!archive.includes(list[h])){
            localStorage.setItem(list[h],"")
        }
    }
}

function setToInput(){
    var archive = {},
        keys = Object.keys(localStorage),
        i = keys.length;

    while(i--){
        if(document.getElementById(keys[i]) !== null){
            document.getElementById(parseInt(keys[i])).value = localStorage.getItem(keys[i])
        }
    }
}

var timeSecond = document.getElementById("timer").value
var timeMinute = timeSecond * 60

function startGRBTimer(duration, display) {
    var timer = window.name == '' ? duration : window.name,
        hours, minutes, seconds;

    setInterval(function () {
        hours = parseInt(timer % (24 * 60 * 60) / (60 * 60), 10);
        minutes = parseInt(timer % (60 * 60) / 60, 10);
        seconds = parseInt(timer % 60, 10);
        hours = hours < 10 ? "0" + hours : hours;
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = hours + "h " + minutes + "m " + seconds + "s";
        --timer;

        if (timer <= 0) {
            setToLocalStorage();
            finishExam()
        }

        window.name = timer;
    }, 1000);
}

var display = document.querySelector("#grb");
startGRBTimer(timeMinute, display);

function setToLocalStorage() {
    var multipleChoices = document.getElementsByClassName("MCQId");
    for (let i = 0; i < multipleChoices.length; i++) {
        window.localStorage.setItem(multipleChoices[i].value, document.getElementById(multipleChoices[i].value).value);
    }
    var descriptive = document.getElementsByClassName("DQId");
    for (let i = 0; i < descriptive.length; i++) {
        window.localStorage.setItem(descriptive[i].value, document.getElementById(descriptive[i].value).value);
    }
}

function finishExam() {
    var list = [];
    var archive = {},
        keys = Object.keys(localStorage),
        i = keys.length;

    while (i--) {
        list.push({
            "id": parseInt(keys[i]),
            "answer": localStorage.getItem(keys[i])
        })
    }
    localStorage.clear();
    console.log(archive)
    axios.post("http://localhost:8080/question-status-rest/questions-result", list)
        .then(response => {
            window.location.href = "http://localhost:8080/student/student-main"
        })
}

document.getElementById("submitButton").onclick = function () {
    var confirm1 = confirm("مطمئن هستید میخواهیدآزمون را تمام کنید؟");
    if (confirm1) {
        setToLocalStorage();
        checkStorage()
        finishExam();
    } else {
        alert("آزمون تمام نشد")
    }
}