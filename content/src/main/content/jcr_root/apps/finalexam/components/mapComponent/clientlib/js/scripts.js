function initialize() {
    var myLatlng = new google.maps.LatLng(40.7033127,-73.979681);
    var myOptions = {
        zoom: 5,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("map"), myOptions);

    var point1 = new google.maps.LatLng(40.7033127,-73.979681);

    var marker = new google.maps.Marker({
        position: point1, map: map, title: 'Пробная точка!',
        icon:'/apps/finalexam/components/mapComponent/clientlib/logo.jpg'
    });

    //map.addOverlay(marker);
    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map,marker);
    });
//
//    //google.maps.event.addListener(map, 'click', alert('hello'));
//
    var date = '<div class="concert" style="margin:10px;"><div class="input-prepend"><span class="add-on">Date</span><br><input class="span4" id="date" name="date" type="text"></div>'
    var place = '<div class="input-prepend"><span class="add-on">Place</span><br><input class="span4" id="place" name="place" type="text"></div>'
    var location = '<div class="input-prepend"><span class="add-on">Location</span><br><input class="span2" id="location" name="location" type="text"></div><br>'
    var button = '<p align="right"><button class="btn btn-success" type="button" onclick="sendAjax()">Add</button></p></div></div>'
    var infowindow = new google.maps.InfoWindow({
        content: date+place+location+button
    });

}
google.maps.event.addDomListener(window, 'load', initialize);

function sendAjax() {
    var concert = new Object();
    concert.date = $('#date').val();
    concert.place = $('#place').val();
    concert.location = $('#location').val();

    $.ajax({
        url: "/bin/test/add",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(concert),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
//            $("tr:has(td)").remove();
//
//            $.each(data, function (index, article) {
//
//                var td_categories = $("<td/>");
//                $.each(article.categories, function (i, tag) {
//                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
//                    span.text(tag);
//                    td_categories.append(span);
//                });
//
//                var td_tags = $("<td/>");
//                $.each(article.tags, function (i, tag) {
//                    var span = $("<span class='label' style='margin:4px;padding:4px' />");
//                    span.text(tag);
//                    td_tags.append(span);
//                });
//
//                $("#added-articles").append($('<tr/>')
//                        .append($('<td/>').html("<a href='"+article.url+"'>"+article.title+"</a>"))
//                        .append(td_categories)
//                        .append(td_tags)
//                );
//
//            });
        },
        error:function(data,status,er) {
//            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}




