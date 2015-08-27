function validForm(form) {
    date = form.date.value;
    place = form.place.value;
    location = form.location.value;
    if(date == null || place==null || location==null) alert("Error")
    else form.submit() // Отправляем на сервер
}
