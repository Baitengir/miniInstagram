package practice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        PostServiceImpl postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        LikeService likeService = new LikeServiceImpl();

        // todo check user methods
//        userService.createUser(User.builder()
//                .firstName("Kelechek")
//                .lastName("jj")
//                .email("kk@gmail.com")
//                .dateOfBirth(LocalDate.of(2003, 5, 16))
//                .phoneNumber("0508090877")
//                .build());
//        userService.createUser(User.builder()
//                .firstName("Aiym")
//                .lastName("jj")
//                .email("ld@gmail.com")
//                .dateOfBirth(LocalDate.of(2003, 5, 16))
//                .phoneNumber("0508090877")
//                .build());
//        userService.createUser(User.builder()
//                .firstName("Jenish")
//                .lastName("jj")
//                .email("ld@gmail.com")
//                .dateOfBirth(LocalDate.of(2003, 5, 16))
//                .phoneNumber("0508090877")
//                .build());

//        System.out.println(userService.getUserById(2L));

//        System.out.println(userService.getAllUsers());

//        User user = User.builder()
//                .firstName("Sadyr")
//                .lastName("ss")
//                .email("ss@gmail.com")
//                .dateOfBirth(LocalDate.now())
//                .phoneNumber("123456789")
//                .build();
//        userService.updateUserByID(1L, user);

//        userService.deleteUserById(2L);

//        System.out.println(userService.getUserByMostPopularPost());

        // todo check post methods

//        postService.addPostByUserId(3L, Post.builder()
//                .imageUrl("https/image")
//                .description("paris")
//                .build());

//        postService.addPostByUserId(4L, Post.builder()
//                .imageUrl("https/image")
//                .description("my birthday")
//                .build());
//
//        postService.addPostByUserId(1L, Post.builder()
//                .imageUrl("https/image")
//                .description("happy holidays 2 ")
//                .build());

//        System.out.println(postService.getPostById(2L));

//        System.out.println(postService.getAllPostsByUserId(1L));

//        System.out.println(postService.getSortedPostsByLike("desc")); // todo

//        System.out.println(postService.getMostPopularPostByCommentCount());

//        postService.deletePostById(1L);

        // todo check comment methods n
//        commentService.createComment(3L, 4L, Comment
//                .builder()
//                .commentText("yoooou!!")
//                .build());
//        commentService.createComment(1L, 3L, Comment
//                .builder()
//                .commentText("crush!")
//                .build());
//        commentService.createComment(4L, 2L, Comment
//                .builder()
//                .commentText("genius!!")
//                .build());
//        commentService.createComment(3L, 2L, Comment
//                .builder()
//                .commentText("crazy!")
//                .build());
//
//        System.out.println(commentService.getCommentById(3L));
//
//        System.out.println(commentService.getCommentsByPostId(4L));
//
//        System.out.println(commentService.getCommentsByUserId(3L));
//
//        System.out.println(commentService.getPopularComment());
//        System.out.println(commentService.getPopularCommentByPostId(2L));
//        System.out.println(commentService.getPopularCommentByUserId(1L));
//
//        commentService.updateCommentById(2L, Comment.builder()
//                        .commentText("This is a comment")
//                .build());
//
//        commentService.deleteCommentById(1L);

        // todo check like methods

//        likeService.addLikeForPost(2L, 1L, Like.builder().build());
//        likeService.addLikeForPost(1L, 3L, Like.builder().build());
//        likeService.addLikeForPost(3L, 4L, Like.builder().build());
//        likeService.addLikeForPost(3L, 5L, Like.builder().build());
//        likeService.deleteLikeFromPost(3L, 3L);

//        likeService.addLikeForComment(3L, 2L, Like.builder().build());
//        likeService.addLikeForComment(4L, 2L, Like.builder().build());
//        likeService.addLikeForComment(1L, 3L, Like.builder().build());

//        likeService.deleteLikeFromComment(3L, 1L);






    }
}
