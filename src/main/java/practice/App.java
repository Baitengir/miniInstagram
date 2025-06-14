package practice;
import practice.entities.Comment;
import practice.entities.Like;
import practice.entities.Post;
import practice.entities.User;
import practice.service.CommentService;
import practice.service.LikeService;
import practice.service.UserService;
import practice.service.serviceImpl.CommentServiceImpl;
import practice.service.serviceImpl.LikeServiceImpl;
import practice.service.serviceImpl.PostServiceImpl;
import practice.service.serviceImpl.UserServiceImpl;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {


        UserService userService = new UserServiceImpl();
        PostServiceImpl postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        LikeService likeService = new LikeServiceImpl();

//        userService.createUser(User.builder()
//                .firstName("Junus")
//                .lastName("jj")
//                .email("ld@gmail.com")
//                .dateOfBirth(LocalDate.of(2013, 5, 16))
//                .phoneNumber("0508090877")
//                .build());

//        postService.addPostByUserId(3L, Post.builder()
//                .imageUrl("https/image")
//                .description("my life")
//                .build());
//
//        postService.addPostByUserId(4L, Post.builder()
//                .imageUrl("https/image")
//                .description("moon")
//                .build());
////
//        commentService.createComment(4L, 2L, Comment
//                .builder()
//                .commentText("Grate!")
//                .build());
//        commentService.createComment(3L, 3L, Comment
//                .builder()
//                .commentText("Grate!")
//                .build());
//
//        likeService.addLikeForPost(3L, 3L, Like.builder()
//                .isLike(true)
//                .build());

//        likeService.deleteLikeFromPost(3L, 2L);
// todo drop and create db for new lines in entities





    }
}
