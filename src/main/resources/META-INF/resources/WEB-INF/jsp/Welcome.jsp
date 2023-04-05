<%@ include file="common\navigation.jspf" %>
<%@ include file="common\header.jspf" %>
<html>
    <head>
        <title>Welcome page</title>
    </head>
    <body>
        <div class="container">
            <h1>
                Welcome to First ToDo app ${name}
            </h1>
            <hr>
            <a href="list-todos">Manage</a> Your Todos.
        </div>
        <%@ include file="common\footer.jspf" %>
    </body>
</html>