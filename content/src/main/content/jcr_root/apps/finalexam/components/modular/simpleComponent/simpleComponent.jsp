<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>
<%@include file="/apps/finalexam/init.jsp" %>

<slice:lookup var="model" type="<%=com.annamukhina.practice.components.model.SimpleComponentModel.class%>" />
<jsp:useBean id="model" type="com.annamukhina.practice.components.model.SimpleComponentModel" />

<h2>This document was last modified on: ${model.lastModified}</h2>
<h3>Document's custom text is: ${model.text}</h3>
