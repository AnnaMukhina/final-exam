<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.TableComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.TableComponentModel" />

<div class="row">
    <div class="col-sm-5 col-md-6 col-lg-8">
        <div class="eventTable">
            <%--<jsp:include page="/bin/test/events" />--%>
        </div>

        <div style="width:700px;padding:20px;">
            <h5 style="text-align:center"><i style="color:#ccc"><small>Events</small></i></h5>

            <table id="added-articles" class="table">
                <tr>
                    <th>Date</th>
                    <th>Place</th>
                    <th>Location</th>
                </tr>
            </table>
        </div>

        <%--<div class="concert" style="margin:10px;">--%>
            <%--<div class="input-prepend">--%>
                <%--<span class="add-on">Date</span><br>--%>
                <%--<input class="span4" id="date" name="date" type="text">--%>
            <%--</div>--%>
            <%--<br/>--%>
            <%--<div class="input-prepend">--%>
                <%--<span class="add-on">Place</span><br>--%>
                <%--<input class="span4" id="place" name="place" type="text">--%>
            <%--</div>--%>
            <%--<br/>--%>
            <%--<div class="input-prepend">--%>
                <%--<span class="add-on">Location</span><br>--%>
                <%--<input class="span2" id="location" name="location" type="text">--%>
            <%--</div>--%>
            <%--<p>--%>
                <%--<button class="btn btn-success" type="button" onclick="sendAjax()">Add</button>--%>
            <%--</p>--%>
        <%--</div>--%>
    </div>
</div>