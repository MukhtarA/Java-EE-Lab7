<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.Lab7.bean.Post" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="dPost" class="com.example.Lab7.database.DPost" scope="session"/>
<jsp:useBean id="dComment" class="com.example.Lab7.database.DComment" scope="session"/>
<jsp:useBean id="dUser" class="com.example.Lab7.database.DUser" scope="session"/>

<%
    List<Post> posts = new ArrayList<>(dPost.getAllPost());
    String postRender = "";

    for (Post post : posts) {
        postRender += "<div>\n" +
                "      <a href=\"post?id=" + post.getId() + "\">\n" +
                "      <div>" + post.getTitle() + "</div>\n" +
                "                </a>\n" +
                "\n" +
                "       <div>\n" +
                "\n" +
                "       <div>\n" +
                "       <span>" + post.getCountLike() + " likes</span>\n" +
                "       <span>" + dComment.getCountCommentsById(post.getId()) + " comments</span>\n" +
                "       </div>\n" +
                "\n" +
                "        <div>\n" +
                "        <div>\n" +
                "        <div>" + post.getDay() + "</div>\n" +
                "        <div> Author: " + dUser.getUserById(post.getUserId()).getUsername() + "</div>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "\n" +
                "        </div>";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
</head>
<body>
<%@ include file="head.jsp"%>

    <div>
        <%= postRender %>
    </div>
</body>
</html>