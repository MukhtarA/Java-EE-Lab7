package com.example.Lab7.database;

import com.example.Lab7.bean.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DPost {
    private Connection connection;
    private String tableName;

    public DPost() {
        this.connection = new Connection("jdbc:mysql://localhost:3306/lab7", "tal", "tal");
        this.tableName = "post";
    }

    private Post getPostObject(int id, Integer userId, String title, String text, Integer countLike, Date day) {
        Post post = new Post();
        post.setId(id);
        post.setUserId(userId);
        post.setTitle(title);
        post.setText(text);
        post.setCountLike(countLike);
        post.setDay(day);
        return post;
    }

    public Post getPostById(Integer postId) {
        connection.openConnect();
        Post post = null;
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName + " WHERE id = " + postId);

        try {
            resultSet.next();
            Integer id = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            String title = resultSet.getString("title");
            String text = resultSet.getString("text");
            Integer countLike = resultSet.getInt("likes");
            Date day = resultSet.getDate("date");

            post = getPostObject(id, userId, title, text, countLike, day);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    public List<Post> getAllPost() {
        connection.openConnect();
        List<Post> posts = new ArrayList<>();
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName);

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Integer countLike = resultSet.getInt("likes");
                Date day = resultSet.getDate("date");

                posts.add(getPostObject(id, userId, title, text, countLike, day));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public int getCountByUserId(Integer userId) {
        String sql = "SELECT COUNT(user_id) as 'count_post_id' FROM " + tableName + " WHERE user_id = " + userId;
        ResultSet resultSet = connection.getData("SELECT * FROM " + tableName);
        int count = 0;

        try{
            resultSet.next();
            count = resultSet.getInt("count_post_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
