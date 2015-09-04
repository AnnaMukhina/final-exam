<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=KOI8-R">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
                <div class="col-lg-6 col-md-5 col-sm-12 col-xs-12">
                    <h3 style="text-align:center"><b style="color:black"><fmt:message key="tour"/></b></h3>
                    <div id="eventTable"></div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
                <div class="col-lg-3 col-md-2 col-sm-12 col-xs-12">
                    <form id="form" style="margin:10px;display: none">
                        <div class="input-prepend">
                            <span class="add-on"><fmt:message key="date"/></span><br>
                            <input class="span4" id="date" name="date" type="text">
                        </div>
                        <div class="input-prepend">
                            <span class="add-on"><fmt:message key="place"/></span><br>
                            <input class="span4" id="place" name="place" type="text">
                        </div>
                        <div class="input-prepend">
                            <span class="add-on"><fmt:message key="city"/></span><br>
                            <input class="span2" id="city" name="city" type="text">
                        </div>
                        <div class="input-prepend">
                            <span class="add-on"><fmt:message key="latitude"/></span><br>
                            <input class="span2" id="latitude" name="latitude" type="text">
                        </div>
                        <div class="input-prepend">
                            <span class="add-on"><fmt:message key="longitude"/></span><br>
                            <input class="span2" id="longitude" name="longitude" type="text">
                        </div>
                        <p>
                            <button class="btn btn-success" type="button" onclick="sendAjax()"><fmt:message key="add"/></button>
                        </p>
                    </form>
                    <button class="btn btn-success" id="addition" type="button" onclick="addEvent()"><fmt:message key="add"/></button>
                    <button class="btn btn-primary" id="search" type="button" onclick="showBlock()"><fmt:message key="find"/></button>
                    <div id="log"></div>
                    <div id="search-block" style="display: none">
                        <p><fmt:message key="city"/>:</p>
                        <form id="find-event">
                            <input id="cityName" type="text">
                            <button id="find" onclick="findEvent()"><fmt:message key="find"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


