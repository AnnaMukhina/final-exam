<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.TableComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.TableComponentModel" />

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3 style="text-align:center"><b style="color:black">Tour</b></h3>
            <div id="eventTable"></div>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-3">
            <form id="form" style="margin:10px;display: none">
                <div class="input-prepend">
                    <span class="add-on">Date</span><br>
                    <input class="span4" id="date" name="date" type="text">
                </div>
                <div class="input-prepend">
                    <span class="add-on">Place</span><br>
                    <input class="span4" id="place" name="place" type="text">
                </div>
                <div class="input-prepend">
                    <span class="add-on">City</span><br>
                    <input class="span2" id="city" name="city" type="text">
                </div>
                <div class="input-prepend">
                    <span class="add-on">Latitude</span><br>
                    <input class="span2" id="latitude" name="latitude" type="text">
                </div>
                <div class="input-prepend">
                    <span class="add-on">Longitude</span><br>
                    <input class="span2" id="longitude" name="longitude" type="text">
                </div>
                <p>
                    <button class="btn btn-success" type="button" onclick="sendAjax()">Add</button>
                </p>
            </form>
            <button class="btn btn-success" id="addition" type="button" onclick="addEvent()">Add event</button>
            <div id="log"></div>
        </div>
    </div>
</div>