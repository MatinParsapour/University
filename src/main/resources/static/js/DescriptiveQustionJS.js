document.getElementById("acceptButton").addEventListener("click",function(event){
    if(document.getElementById("problem").value.length === 0
        || document.getElementById("correctAnswer").value.length === 0
        || document.getElementById("title").value.length === 0){
        event.preventDefault();
        alert("لطفا تمام ورودی ها را پر کنید")
    }

    if(document.getElementById("grade").value <= 0){
        event.preventDefault();
        alert("میزان نمره باید مثبت و بیشتر از صفر باشد")
    }
})

function displayTitleLength(){
    document.getElementById("lengthOfInput").innerHTML = 40 - document.getElementById("title").value.length
}