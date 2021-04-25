<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

%>

<html>
<head>
    <title>Sign in Page</title>
</head>
<body>

    <%@ include file="head.jsp"%>

        <div>
            <div>
                <form action="signin" method="post">
                    <label for="username">Username: </label>
                    <input type="text" id="username" name="username">
                    <hr>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">

                    <button type="submit">Sign in</button>
                </form>
            </div>
        </div>

</body>
</html>
