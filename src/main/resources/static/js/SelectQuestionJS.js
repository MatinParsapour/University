function openDescriptiveGradeInput(){
    document.getElementById("addDescriptiveToQuizButton").style.display = "block"
    document.getElementById("descriptiveGradeLabel").style.display = "block"
    document.getElementById("descriptiveGrade").style.display = "block"
    document.getElementById("descriptiveCancelButton").style.display = "block"
    document.getElementById("setDescriptiveGrade").style.display = "none"
}

function openMultipleChoicesGradeInput(){
    document.getElementById("addMultipleChoicesToQuizButton").style.display = "block"
    document.getElementById("multipleChoicesGrade").style.display = "block"
    document.getElementById("multipleChoicesGradeLabel").style.display = "block"
    document.getElementById("multipleChoicesCancelButton").style.display = "block"
    document.getElementById("setMultipleChoicesGrade").style.display = "none"
}
function closeDescriptiveGradeInput(){
    document.getElementById("addDescriptiveToQuizButton").style.display = "none"
    document.getElementById("descriptiveGradeLabel").style.display = "none"
    document.getElementById("descriptiveGrade").style.display = "none"
    document.getElementById("descriptiveCancelButton").style.display = "none"
    document.getElementById("setDescriptiveGrade").style.display = "block"
}

function closeMultipleChoicesGradeInput(){
    document.getElementById("addMultipleChoicesToQuizButton").style.display = "none"
    document.getElementById("multipleChoicesGrade").style.display = "none"
    document.getElementById("multipleChoicesGradeLabel").style.display = "none"
    document.getElementById("multipleChoicesCancelButton").style.display = "none"
    document.getElementById("setMultipleChoicesGrade").style.display = "block"
}