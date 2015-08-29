$( document ).ready(function() {
    $.ajax({
        url: '/bin/test/events',
        type: 'GET',
        dataType: 'json',
//        data: JSON.stringify(concert),
//        contentType: 'application/json',
//        mimeType: 'application/json',
//        success: function(data){
//
//        }
//    });
//    $("#eventTable").load('/bin/test/events');;
        success: function(data){
        if(data){
            var len = data.length;
            var txt = "";
            if(len > 0){
                for(var i=0;i<len;i++){
                    if(data[i].date && data[i].place && data[i].city){
                        txt += "<tr><td>"+data[i].date+"</td><td>"+data[i].place+"</td><td>"+data[i].city+"</td></tr>";
                    }
                }
                if(txt != ""){
                    $("#eventTable").append(txt).removeClass("hidden");
                }
            }
        }
    },
    error: function(jqXHR, textStatus, errorThrown){
        alert('error: ' + textStatus + ': ' + errorThrown);
    }
});
});

