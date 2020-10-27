package com.example.webapplicationboot.controller;

import com.example.webapplicationboot.dto.CommentCreateDto;
import com.example.webapplicationboot.dto.PostCreateDto;
import com.example.webapplicationboot.entity.Comment;
import com.example.webapplicationboot.entity.Like;
import com.example.webapplicationboot.entity.Post;
import com.example.webapplicationboot.entity.User;
import com.example.webapplicationboot.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/create")
    public ModelAndView getCreatePostForm(ModelAndView modelAndView) {
        modelAndView.addObject("postCreateDto", new PostCreateDto());
        modelAndView.setViewName("post-form");
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public ModelAndView createPost(@Valid @ModelAttribute("postCreateDto") PostCreateDto postCreateDto,
                                   BindingResult bindingResult,
                                   @SessionAttribute("user") User user,
                                   ModelAndView modelAndView) {
        if (!bindingResult.hasErrors()) {
            Post post = new Post();
            post.setTitle(postCreateDto.getTitle());
            post.setMassage(postCreateDto.getMassage());
            post.setAuthor(user);
            postService.createPost(post);
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("post-form");
        }
        return modelAndView;
    }

    @GetMapping(path = "/show")
    public ModelAndView getPost(@RequestParam("post-id") long postId, ModelAndView modelAndView) {
        Post post = postService.GetPostById(postId);
        modelAndView.addObject("post", post);
        modelAndView.addObject("commentCreateDto", new CommentCreateDto());
        modelAndView.setViewName("post");
        return modelAndView;
    }

    @PostMapping(path = "/set-comment")
    public ModelAndView setCommentAndGetPost(@Valid @ModelAttribute("commentCreateDto") CommentCreateDto commentCreateDto,
                                             BindingResult bindingResult,
                                             @RequestParam("post-id") long postId,
                                             @SessionAttribute("user") User user,
                                             ModelAndView modelAndView) {
        if (!bindingResult.hasErrors()) {
            Comment comment = new Comment();
            comment.setMassage(commentCreateDto.getMassage());
            comment.setAuthor(user);
            comment.setPostId(postId);
            postService.setComment(comment);
            modelAndView.setViewName(String.format("redirect:/post/show?post-id=%s", postId));
        }else{
            Post post = postService.GetPostById(postId);
            modelAndView.addObject("post", post);
            modelAndView.setViewName("post");
        }
        return modelAndView;
    }

    @GetMapping(path = "/set-like")
    public ModelAndView setLikeAndGetPost(@RequestParam("post-id") long postId,
                                          @SessionAttribute("user") User user,
                                          ModelAndView modelAndView) {
        Like like = new Like();
        like.setAuthor(user);
        like.setPostId(postId);
        postService.setLike(like);
        modelAndView.setViewName(String.format("redirect:/post/show?post-id=%s", postId));
        return modelAndView;
    }

    @GetMapping(path = "like")
    public ModelAndView getLikes(@RequestParam("post-id") long postId, ModelAndView modelAndView) {
        Post post = postService.GetPostById(postId);
        modelAndView.addObject("likes", post.getLikes());
        modelAndView.addObject("postId", postId);
        modelAndView.setViewName("like");
        return modelAndView;
    }
}
