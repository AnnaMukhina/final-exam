function initialize() {
    var myLatlng = new google.maps.LatLng(40.7033127,-73.979681);
    var myOptions = {
        zoom: 5,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("map"), myOptions);

    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng);
    });

    var contentString = $("<div class='concert' style='margin:10px;'>" +
        "<div class='input-prepend'>" +
        "<span class='add-on'>Date</span><br>" +
        "<input class='span4' id='date' name='date' type='text'></div>" +
        "<div class='input-prepend'>" +
        "<span class='add-on'>Place</span><br>" +
        "<input class='span4' id='place' name='place' type='text'></div>" +
        "<div class='input-prepend'>" +
        "<span class='add-on'>City</span><br>" +
        "<input class='span4' id='city' name='city' type='text'></div><br>" +
        "<p align='right'><button name='add' class='btn-success'>Add event</p></div>");

    function placeMarker(location) {
        var marker = new google.maps.Marker({
            position: location,
            map: map,
            icon:'/apps/finalexam/components/mapComponent/clientlib/logo.jpg'
        });

        var infowindow = new google.maps.InfoWindow();
        infowindow.setContent(contentString[0]);
        infowindow.open(map,marker);

        var addBtn = contentString.find('button.btn-success')[0];

        google.maps.event.addDomListener(addBtn, 'click', function(event) {
            sendAjax(infowindow);
        });
    }

    function sendAjax(infowindow) {
        var concert = {};
        concert.date = $('#date').val();
        concert.place = $('#place').val();
        concert.city = $('#city').val();

        $.ajax({
            url: "/bin/test/add",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(concert),
            contentType: 'application/json',
            mimeType: 'application/json',

            success: function () {
                infowindow.setContent('Event is successfully added.');
            },
            error:function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
    }
}
google.maps.event.addDomListener(window, 'load', initialize);
