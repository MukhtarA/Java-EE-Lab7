<%@ page import="com.example.Lab7.bean.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="dPost" class="com.example.Lab7.database.DPost" scope="session"/>
<jsp:useBean id="dComment" class="com.example.Lab7.database.DComment" scope="session"/>
<jsp:useBean id="dUser" class="com.example.Lab7.database.DUser" scope="session"/>
<jsp:useBean id="post" class="com.example.Lab7.bean.Post" scope="session"/>

<%
    Integer postId = Integer.parseInt(request.getParameter("id"));
    post = dPost.getPostById(postId);
    List<Comment> comments = new ArrayList<>(dComment.getCommentsByPostID(postId));

    String commentsRender = "";
    for (Comment comment : comments) {
        commentsRender += "<div>\n" +
                "\n" +
                "          <div>\n" +
                "          <p>\n" +
                "          " + comment.getText() + "\n" +
                "          </p>\n" +
                "          </div>\n" +
                "\n" +
                "          <div>\n" +
                "\n" +
                "          <div>\n" +
                "          <div>\n" +
                "          " + comment.getCountLike() + " likes\n" +
                "          </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div>\n" +
                "          <div>\n" +
                "          " + dUser.getUserById(comment.getId()).getUsername() + "\n" +
                "          </div>\n" +
                "          <div>\n" +
                "          " + comment.getDay() + "\n" +
                "          </div>\n" +
                "          </div>\n" +
                "\n" +
                "          </div>\n" +
                "\n" +
                "          </div>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <link href="./styles/main.css" rel="stylesheet" type="text/css">
    <title>Post Page</title>
</head>
<body>
    <%@ include file="head.jsp"%>

    <div>
        <div>
            Create date: <%= post.getDay() %>
        </div>
        <h1>
            <%= post.getTitle() %>
        </h1>
        <p>
            <%= post.getText() %>
        </p>
        <div>Comments: </div>
         <%= commentsRender %>
    </div>

</body>
</html>
