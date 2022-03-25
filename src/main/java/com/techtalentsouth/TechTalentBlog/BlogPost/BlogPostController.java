package com.techtalentsouth.TechTalentBlog.BlogPost;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();



//    @GetMapping(value="/") //READ
//    public String index(BlogPost blogPost) {
//        return "blogpost/index";
//    }

//    private BlogPost blogPost;
//
//    @PostMapping(value = "/") //CREATE
//    public String addNewBlogPost(BlogPost blogPost, Model model) {
//        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
//        model.addAttribute("title", blogPost.getTitle());
//        model.addAttribute("author", blogPost.getAuthor());
//        model.addAttribute("blogEntry", blogPost.getBlogEntry());
//        return "blogpost/result";
//    }
//private BlogPost blogPost;
//    @PostMapping(value = "/blogposts")
//    public String addNewBlogPost(BlogPost blogPost, Model model) {
//        blogPostRepository.save(blogPost);
//        posts.add(blogPost);
//        model.addAttribute("title", blogPost.getTitle());
//        model.addAttribute("author", blogPost.getAuthor());
//        model.addAttribute("blogEntry", blogPost.getBlogEntry());
//        return "blogpost/result";
//    }
private BlogPost blogPost;
@GetMapping(value = "/")
    public String index(BlogPost blogPost, Model model) {
      posts.removeAll(posts);
        for (BlogPost post : blogPostRepository.findAll()) {
            posts.add(post);}

    model.addAttribute("posts", posts);
        return "blogpost/index";
    }



    @GetMapping(value = "/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }
    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        //posts.add(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }
    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/edit";
    }
    @RequestMapping(value = "/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }

        return "blogpost/result";
    }


    @RequestMapping(value = "blogposts/delete/{id}")
    public String deletePostById(@PathVariable Long id, BlogPost blogPost) {
        blogPostRepository.deleteById(id);
        return "blogpost/delete";
    }



//    @PostUpdate(value ="/blogposts")
//    public String updateBlogPost(long id) {
//        posts.remove(id);
//        return "blogpost/result";
//    }



    }


