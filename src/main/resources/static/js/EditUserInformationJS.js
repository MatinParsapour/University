function edit() {
    document.getElementById("changeFirstNameButton").style.display = "block"
    document.getElementById("changeEmailButton").style.display = "block"
    document.getElementById("changeLastNameButton").style.display = "block"
    document.getElementById("cancelButton").style.display = "block"
}

$('form')
    .each(function(){
        $(this).data('serialized', $(this).serialize())
    })
    .on('change input', function(){
        $(this)
            .find('input:submit, button:submit')
            .prop('disabled', $(this).serialize() == $(this).data('serialized'))
        ;
    })
    .find('input:submit, button:submit')
    .prop('disabled', true)
;