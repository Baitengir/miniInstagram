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
//                .firstName("Zahir")
//                .lastName("jj")
//                .email("ld@gmail.com")
//                .dateOfBirth(LocalDate.of(2003, 5, 16))
//                .phoneNumber("0508090877")
//                .build());

//        postService.addPostByUserId(7L, Post.builder()
//                .imageUrl("https/image")
//                .description("my life")
//                .build());
//
//        postService.addPostByUserId(5L, Post.builder()
//                .imageUrl("https/image")
//                .description("moon")
//                .build());
////
//        commentService.createComment(5L, 8L, Comment
//                .builder()
//                .commentText("yoooou!!")
//                .build());
//        commentService.createComment(7L, 7L, Comment
//                .builder()
//                .commentText("crush!")
//                .build());
//
        likeService.addLikeForPost(7L, 6L, Like.builder().build());
//        likeService.addLikeForPost(8L, 6L, Like.builder().build());

//        likeService.deleteLikeFromPost(8L, 5L);

// todo drop and create db for new lines in entities





    }
}
