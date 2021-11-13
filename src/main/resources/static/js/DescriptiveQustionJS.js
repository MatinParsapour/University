document.getElementById("acceptButton").addEventListener("click",function(event){
    if(document.getElementById("problem").value.length === 0
        || document.getElementById("correctAnswer").value.length === 0
        || document.getElementById("title").value.length === 0
        || document.getElementById("grade").value === 0){
        event.preventDefault();
        alert("لطفا تمام ورودی ها را پر کنید")
    }
})