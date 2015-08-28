function validForm(form) {
    date = form.date.value;
    place = form.place.value;
    location = form.location.value;
    if(date == null || place==null || location==null) alert("Error")
    else form.submit() // Отправляем на сервер
}

var req;

function validate() {
    var idField = document.getElementById("userid");
    var url = "validate?id=" + encodeURIComponent(idField.value);
    if (typeof XMLHttpRequest != "undefined") {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}
