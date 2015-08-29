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
    </div>
</div>