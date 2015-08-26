<%@page session="false" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@include file="/apps/finalexam/global.jsp" %>


<!-- Navbar
================================================== -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="./index.html">Home Page</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active">
                        <a href="./index.html">Home</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="jumbotron masthead">
    <div class="container">
        <h1>CQ5 Sample</h1>

        <p>Quick, intuitive way to start writing new challenging CQ5-based applications.</p>
        <ul class="masthead-links">
            <li>
                <a href="https://github.com/yurishchev/cq5_archetypes.git">GitHub project</a>
            </li>
            <li>Version 1.0</li>
        </ul>
    </div>
</div>


<div class="container">
    <h3>Generate your new project with the following command:</h3>
    <pre class="prettyprint">
      mvn archetype:generate -DarchetypeGroupId=com.yurishchev.aem -DarchetypeVersion=1.0 -DarchetypeArtifactId=basic-archetype
    </pre>

    <div class="marketing">
        <cq:include path="simpleComponent" resourceType="finalexam/components/modular/simpleComponent"/>
    </div>
</div>
