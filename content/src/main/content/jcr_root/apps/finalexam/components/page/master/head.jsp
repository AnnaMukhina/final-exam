<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

    <!--[if IE 8]>
    <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="//oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script>
        if (typeof console === "undefined" || typeof console.log === "undefined") {
            console = {};
            console.log = function() {};
        }
    </script>
    <![endif]-->

    <cq:include script="/apps/finalexam/init.jsp" />

    <cq:includeClientLib css="finalexam.bootstrap" />
    <cq:includeClientLib css="finalexam.master.all" />
    <cq:includeClientLib css="finalexam.jquery" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script>
    <cq:includeClientLib js="finalexam.master.all" />
</head>
