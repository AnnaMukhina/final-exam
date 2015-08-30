<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.TableComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.TableComponentModel" />

<div class="row">
    <div class="col-sm-5 col-md-6 col-lg-8">
        <div style="width:700px;padding:20px;">
            <h3 style="text-align:center"><b style="color:black">Tour</b></h3>
            <table id="eventTable" class="table">
                <tr>
                    <th>Date</th>
                    <th>Place</th>
                    <th>Location</th>
                </tr>
            </table>
        </div>

        <div class="form" style="margin:10px;">
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
                <button class="btn btn-success" type="button" onclick="sendAjax()">Add event</button>
            </p>
        </div>
        <div id="log"></div>
    </div>
</div>