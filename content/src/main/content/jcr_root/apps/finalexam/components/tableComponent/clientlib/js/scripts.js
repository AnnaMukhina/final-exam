$( document ).ready(function() {
    showEvents();
});

function showEvents() {
    $.ajax({
        url: '/bin/test/events',
        type: 'GET',
        dataType: 'json',
        success: function(data){
            if(data){
                var length = data.length;
                var output = "<table class='table'><tr><th>Date</th><th>Place</th><th>Location</th></tr>";
                if(length > 0){
                    for(var i = 0; i < length; i++){
                        if(data[i].date && data[i].place && data[i].city){
                            output += "<tr><td>"+data[i].date+"</td><td>"+data[i].place+"</td><td>"+data[i].city+"</td></tr>";
                        }
                    }
                    output += ("</table>");
                    if(output != ""){
                        $("#eventTable").append(output).removeClass("hidden");
                    }
                }
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('error: ' + textStatus + ': ' + errorThrown);
        }
    });
}

function addEvent() {
    form.reset();
    $("#form").show();
    $("#addition").hide();
}

function sendAjax() {
    var concert = {};
    concert.date = $('#date').val();
    concert.place = $('#place').val();
    concert.city = $('#city').val();
    concert.latitude = $('#latitude').val();
    concert.longitude = $('#longitude').val();

    $.ajax({
        url: "/bin/test/add",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(concert),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function () {
            var output = "<p>Event was successfully added</p>";
            clearLog();
            $("#log").append(output);
            $("#form").hide();
            $("#addition").show();
            clearEventsList();
            showEvents();
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}

function clearEventsList() {
    $("#eventTable").find("table").remove();
}

function clearLog() {
    $("#log").find("p").remove();
}


