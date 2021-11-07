function showTeachers() {
    document.getElementById("displayTeachers").style.display = "block"
    document.getElementById("displayStudents").style.display = "none"
    document.getElementById("displayCourses").style.display = "none"
}

function showStudents() {
    document.getElementById("displayTeachers").style.display = "none"
    document.getElementById("displayStudents").style.display = "block"
    document.getElementById("displayCourses").style.display = "none"
}

function showCourses() {
    document.getElementById("displayTeachers").style.display = "none"
    document.getElementById("displayStudents").style.display = "none"
    document.getElementById("displayCourses").style.display = "block"
}

function openSearchBoxPad() {
    document.getElementById("searchBoxPad").style.display = "block"
    document.getElementById("closeButton").style.display = "block"
}

function closeSearchBoxPad() {
    document.getElementById("searchBoxPad").style.display = "none"
    document.getElementById("closeButton").style.display = "none"
}

document.getElementById("search").addEventListener("click", function (event) {
    if (!document.getElementById("In progress").checked && !document.getElementById("Rejected").checked && !document.getElementById("Accepted").checked) {
        event.preventDefault();
        alert("وضعیت فرد را مشخص کنید")
        document.getElementById("In progress").style.color = "red"
        document.getElementById("Rejected").style.color = "red"
        document.getElementById("Accepted").style.color = "red"
    }
    if (!document.getElementById("Teacher").checked && !document.getElementById("Student").checked) {
        event.preventDefault();
        alert("نقش فرد را مشخص کنید")
        document.getElementById("Teacher").style.borderColor = "red"
        document.getElementById("Student").style.borderColor = "red"
    }
    if (!document.getElementById("male").checked && !document.getElementById("female").checked){
        event.preventDefault();
        alert("جنسیت فرد را مشخص کنید")
        document.getElementById("male").style.color = "red"
        document.getElementById("female").style.color = "red"
    }
        })