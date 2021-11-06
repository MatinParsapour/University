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