$(document).ready(function () {
   getMap();
});

function getMap() {
    google.maps.visualRefresh = true;

    var zoom = parseInt($("span#zoom").text());
    var latitude = parseFloat($("span#latitude").text());
    var longitude = parseFloat($("span#longitude").text());

    var myLatlng = new google.maps.LatLng(latitude,longitude);

    var mapOptions = {
        zoom: zoom,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

    $.getJSON('/bin/test/events', function (data) {
        $.each(data, function (i, item) {
            var marker = new google.maps.Marker({
                'position': new google.maps.LatLng(item.latitude, item.longitude),
                'map': map,
                'title': item.place
            });

            marker.setIcon('/apps/finalexam/components/mapComponent/clientlib/logo.jpg');

            var id = item.id;
            var oldDate = item.date;

            var contentString = $("<div class='concertInfo'><h4><b>" + item.date + "</b></h2><div><h4>"
                + item.place + "</h4></div><div><h4>" + item.city +"</h4></div>" +
                "<button class='btn-danger'>Delete<button class='btn-default'>Edit</div>");

            var infowindow = new google.maps.InfoWindow();
            infowindow.setContent(contentString[0]);

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
                var delBtn = contentString.find('button.btn-danger')[0];
                google.maps.event.addDomListener(delBtn, 'click', function(event) {
                    delEvent(marker, id);
                });
                var editBtn = contentString.find('button.btn-default')[0];
                google.maps.event.addDomListener(editBtn, 'click', function(event) {
                    editEvent(infowindow, id, oldDate);
                });
            });

            function delEvent(marker, id){
                marker.setMap(null);
                $.ajax({
                    url: "/bin/test/events",
                    type: 'DELETE',
                    dataType: 'text',
                    data: id.toString(),
                    contentType: 'text/plain',

                    success: function () {
                        var output = "<p>Event was successfully deleted</p>";
                        clearLog();
                        $("#log").append(output);
                        clearEventsList();
                        showEvents();
                    },
                    error:function(data,status,er) {
                        alert("error: "+data+" status: "+status+" er:"+er);
                    }
                });
            }

            function editEvent(infowindow, id, oldDate){
                var editWindow = $("<div class='dialog'><b><p>Old date: " + oldDate
                    + "</p><p>New date: <input type='text' id='newDate'/></b><button class='btn-default'>Edit</p></div>");
                infowindow.setContent(editWindow[0]);

                var btn = editWindow.find('button.btn-default')[0];
                google.maps.event.addDomListener(btn, 'click', function(event) {
                    var newDate = $('#newDate').val();
                    sendDate(id, newDate);
                });

                function sendDate(id, newDate){
                    $.ajax({
                        url: "/bin/test/events",
                        type: 'PUT',
                        dataType: 'text',
                        data: id.toString()+" "+newDate.toString(),
                        contentType: 'text/plain',

                        success: function () {
                            var output = "Event was successfully edited";
                            infowindow.setContent(output);
                            clearEventsList();
                            showEvents();
                        },
                        error:function(data,status,er) {
                            alert("error: "+data+" status: "+status+" er:"+er);
                        }
                    });
                }
            }
        })
    });
}

//google.maps.event.addDomListener(window, 'resize', getMap());
//google.maps.event.addDomListener(window, 'load', getMap());

