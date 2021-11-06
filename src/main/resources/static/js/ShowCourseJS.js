function showChooseStudentField() {
    document.getElementById("addStudentButton").style.display = "none"
    document.getElementById("studentsList").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
}

function hideChooseStudentField() {
    document.getElementById("addStudentButton").style.display = "block"
    document.getElementById("studentsList").style.display = "none"
    document.getElementById("cancelButton").style.display = "none"
}

function editPad() {
    document.getElementById("editPad").style.display = "block"
}

function closeEditPad() {
    document.getElementById("editPad").style.display = "none"
}

$(document).ready(function () {
    $('input').focusin(function () {
        $('label').transition({
            x: '80px'
        }, 500, 'ease').next()
            .transition({
                x: '5px'
            }, 500, 'ease');
        setTimeout(function () {
            $('label').next().addClass('move-pen');
        }, 100);

    });

    $('input').focusout(function () {
        $('label').transition({
            x: '0px'
        }, 500, 'ease').next()
            .transition({
                x: '-100px'
            }, 500, 'ease').removeClass('move-pen');
    });
})