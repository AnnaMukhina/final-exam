<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
        <div class="col-lg-6 col-md-5 col-sm-12 col-xs-12">
            <h3 style="text-align:center"><b style="color:black">Tour</b></h3>
            <div id="eventTable"></div>
        </div>
        <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
        <div class="col-lg-3 col-md-2 col-sm-12 col-xs-12">
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
            <button class="btn btn-primary" id="search" type="button" onclick="showBlock()">Find event</button>
            <div id="log"></div>
            <div id="search-block" style="display: none">
                <p>City:</p>
                <form id="find-event">
                    <input id="cityName" type="text">
                    <button id="find" onclick="findEvent()">Find</button>
                </form>
            </div>
            <h2>${currentPage.path}</h2>
        </div>
    </div>
</div>

