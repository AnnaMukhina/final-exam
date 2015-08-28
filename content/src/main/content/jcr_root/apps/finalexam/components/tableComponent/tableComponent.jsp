<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.TableComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.TableComponentModel" />

<div class="row">
    <div class="col-sm-5 col-md-6 col-lg-8">
        <%--<div class="eventTable">--%>
            <%--&lt;%&ndash;<jsp:include page="/bin/test/events" />&ndash;%&gt;--%>
        <%--</div>--%>
        <form action="" onsubmit="validForm(this)" method="post" id="form">
            Date:<br>
            <input type=text name=date><br>
            Place:<br>
            <input type=text name=place><br>
            Location:<br>
            <input type=text name=location><br>
            <p><input id="add" type="submit" name="submit" value="Add event"></p>
            <div class="form_result"> </div>
        </form>
    </div>
    <div class="col-sm-2 col-md-2 col-lg-3">
        <button type="button" class="btn btn-success">Add event</button>
        <%--<button type="button" class="btn btn-success" id="show" onclick="showEvents()">Show evenst</button>--%>
        <button type="button" class="btn btn-danger">Delete event</button>
    </div>
</div>