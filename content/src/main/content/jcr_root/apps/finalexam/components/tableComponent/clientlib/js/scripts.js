//function showEvents() {        // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
//    $.get("/bin/test/events", function(responseJson) {          // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
//        var $table = $("<table>").appendTo($("#eventTable")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
//        $.each(responseJson, function(index, event) {    // Iterate over the JSON array.
//            $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
//                .append($("<td>").text(event.date))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
//                .append($("<td>").text(event.place))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
//                .append($("<td>").text(event.location));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
//        });
//    });
//}