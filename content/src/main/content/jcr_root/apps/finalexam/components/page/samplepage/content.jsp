<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>


<div class="container">
    <h3>Generate your new project with the following command:</h3>
    <pre class="prettyprint">
      mvn archetype:generate -DarchetypeGroupId=com.yurishchev.aem -DarchetypeVersion=1.0 -DarchetypeArtifactId=basic-archetype
    </pre>

    <div class="marketing">
        <cq:include path="par" resourceType="foundation/components/parsys"/>
    </div>
</div>


