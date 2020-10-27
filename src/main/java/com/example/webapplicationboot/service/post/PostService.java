package com.example.webapplicationboot.service.post;

import com.example.webapplicationboot.entity.Comment;
import com.example.webapplicationboot.entity.Like;
import com.example.webapplicationboot.entity.Post;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostByTitle(String title);
    Post GetPostById(long id);
    void createPost(Post post);
    void setComment(Comment comment);
    void setLike(Like like);
}
