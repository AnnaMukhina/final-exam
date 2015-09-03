<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.MapComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.MapComponentModel" />

<div class="col-sm-1 col-md-1 col-lg-1">
</div>
    <div id="map" style="width:auto; height:${model.height}px"></div>
<div class="col-sm-1 col-md-1 col-lg-1"></div>

<div style="display: none">
    <span id="latitude">${model.latitude}</span>
    <span id="longitude">${model.longitude}</span>
    <span id="zoom">${model.zoom}</span>
</div>