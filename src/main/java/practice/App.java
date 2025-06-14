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
//                .firstName("Altynbek")
//                .lastName("altyn")
//                .email("aaGold@gmail.com")
//                .dateOfBirth(LocalDate.of(2003, 5, 16))
//                .phoneNumber("0508090877")
//                .build());

//        postService.addPostByUserId(1L, Post.builder()
//                .imageUrl("https/image")
//                .description("my life")
//                .build());
////
//        commentService.createComment(2L, 1L, Comment
//                .builder()
//                .commentText("Grate!")
//                .build());
//
        likeService.addLikeForPost(2L, 1L, Like.builder()
                .isLike(true)
                .build());
    }
}
