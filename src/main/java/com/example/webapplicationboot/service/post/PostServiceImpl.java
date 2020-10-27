package com.example.webapplicationboot.service.post;

import com.example.webapplicationboot.entity.Comment;
import com.example.webapplicationboot.entity.Like;
import com.example.webapplicationboot.entity.Post;
import com.example.webapplicationboot.repository.CommentRepository;
import com.example.webapplicationboot.repository.LikeRepository;
import com.example.webapplicationboot.repository.PostRepository;
import com.example.webapplicationboot.repository.UserRepository;
import com.example.webapplicationboot.service.post.exception.NotFoundPostByIdException;
import com.example.webapplicationboot.service.post.exception.NotFoundPostByTitleException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class PostServiceImpl implements PostService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private LikeRepository likeRepository;

    public PostServiceImpl(UserRepository userRepository,
                           PostRepository postRepository,
                           CommentRepository commentRepository,
                           LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }



    private Post postHandler(Post post) {
        List<Comment> comments = commentRepository.findAllByPostId(post.getId());
        post.setComments(comments != null ? comments : new ArrayList<>());
        List<Like> likes = likeRepository.findAllByPostId(post.getId());
        post.setLikes(likes != null ? likes : new ArrayList<>());
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post getPostByTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title is null!");
        }
        Optional<Post> postBox = postRepository.findByTitle(title);
        Post post = postBox.orElseThrow(NotFoundPostByTitleException::new);
        return postHandler(post);
    }

    @Override
    public Post GetPostById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is not correct!");
        }
        Optional<Post> postBox = postRepository.findById(id);
        Post post = postBox.orElseThrow(NotFoundPostByTitleException::new);
        return postHandler(post);
    }

    @Override
    public void createPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post is null!");
        }
        postRepository.save(post);
    }

    @Override
    public void setComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment is null!");
        }
        commentRepository.save(comment);
    }

    @Override
    public void setLike(Like like) {
        if (like == null) {
            throw new IllegalArgumentException("Comment is null!");
        }
        likeRepository.save(like);
    }
}
