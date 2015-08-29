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

    var date = '<div class="concert" style="margin:10px;"><div class="input-prepend"><span class="add-on">Date</span><br><input class="span4" id="date" name="date" type="text"></div>'
    var place = '<div class="input-prepend"><span class="add-on">Place</span><br><input class="span4" id="place" name="place" type="text"></div>'
    var city = '<div class="input-prepend"><span class="add-on">City</span><br><input class="span2" id="city" name="city" type="text"></div><br>'
    var button = '<p align="right"><button class="btn btn-success" type="button" onclick="sendAjax()">Add</button></p></div></div>'

    function placeMarker(location) {
        var marker = new google.maps.Marker({
            position: location,
            map: map,
            icon:'/apps/finalexam/components/mapComponent/clientlib/logo.jpg'
        });

        var infowindow = new google.maps.InfoWindow({
            content: date+place+city+button
        });
        infowindow.open(map,marker);
    }
}
google.maps.event.addDomListener(window, 'load', initialize);



function sendAjax() {
    var concert = new Object();
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

        success: function (data) {
//            infowindow.setContent('Event is successfully added.');
//            infowindow.close();
        },
        error:function(data,status,er) {
//            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}





