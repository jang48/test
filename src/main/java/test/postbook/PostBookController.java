package test.postbook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.post.Post;
import test.post.PostService;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class PostBookController {
    private final PostService postService;
    private final PostBookService postBookService;
    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<Post> postList = postService.findallpost();
        List<PostBook> postBookList = this.postBookService.findallbook();


        if(postList.isEmpty()){
            this.postService.addpost(null);
            return "redirect:/";
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));

        model.addAttribute("bookList", postBookList);
        model.addAttribute("targetBook", postBookList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        PostBook postBook = this.postBookService.addbook(null);
        this.postService.addpost(postBook);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Post post = this.postService.findbyidPost(id);
        PostBook postBook = this.postBookService.findbyIdbook(post.getPostBook().getId());

        model.addAttribute("targetPost", post);
        model.addAttribute("postList", this.postService.findallpost());

        model.addAttribute("bookList", this.postBookService.findallbook());
        model.addAttribute("targetBook", postBook);

        return "main";
    }

}
