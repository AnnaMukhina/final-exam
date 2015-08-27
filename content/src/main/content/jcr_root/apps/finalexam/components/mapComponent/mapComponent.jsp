<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.MapComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.MapComponentModel" />
<div class="col-sm-1 col-md-1 col-lg-1"></div>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div id="map"></div>
</body>
</html>