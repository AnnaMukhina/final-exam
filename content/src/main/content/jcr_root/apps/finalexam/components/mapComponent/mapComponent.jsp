<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.MapComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.MapComponentModel" />

<!DOCTYPE html>
<html>
<head>


</head>
<body>
<div id="map"></div>
</body>
</html>