$(document).ready(function () {
    GetMap();
});

function GetMap() {
    google.maps.visualRefresh = true;

    var myLatlng = new google.maps.LatLng(40.7033127,-73.979681);

    var mapOptions = {
        zoom: 5,
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

            marker.setIcon('/apps/finalexam/components/mapComponent/clientlib/logo.jpg')

            var infowindow = new google.maps.InfoWindow({
                content: "<div class='concertInfo'><h4><b>" + item.date + "</b></h2><div><h4>"
                    + item.place + "</h4></div><div><h4>" + item.city +"</h4></div></div>"
            });

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });
        })
    });
}


