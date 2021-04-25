package com.example.Lab7.database;

import com.example.Lab7.bean.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DComment {
    private Connection connection;
    private String tableName;

    public DComment() {
        this.connection = new Connection("jdbc:mysql://localhost:3306/lab7", "tal", "tal");
        this.tableName = "comment";
    }

    public Comment getCommentObject(Integer id, Integer userId, Integer postId, String text, Integer countLike, Date day) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setText(text);
        comment.setCountLike(countLike);
        comment.setDay(day);
        return comment;
    }

    public List<Comment> getCommentsByPostID(Integer postId) {
        List<Comment> comments = new ArrayList<>();
        connection.openConnect();
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName + " WHERE post_id = " + postId);

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer postId_ = resultSet.getInt("post_id");
                String text = resultSet.getString("text");
                Integer countLike = resultSet.getInt("likes");
                Date day = resultSet.getDate("date");

                comments.add(getCommentObject(id, userId, postId_, text, countLike, day));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    public int getCountCommentsById(Integer postId) {
        connection.openConnect();
        int countLikes = 0;
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName + " WHERE post_id = " + postId);

        try {
            while (resultSet.next()) {
                countLikes++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countLikes;
    }
}
