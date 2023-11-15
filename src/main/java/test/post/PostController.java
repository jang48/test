package test.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.postbook.PostBook;
import test.postbook.PostBookService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostBookService postBookService;

    @RequestMapping("/test")
    @ResponseBody public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<Post> postList = postService.findallpost();
        List<PostBook> postBookList = this.postBookService.findallbook();

        if(postList.isEmpty()){
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));

        model.addAttribute("bookList", postBookList);
        model.addAttribute("targetBook", postBookList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write(@RequestParam Long id) {
        PostBook postBook = this.postBookService.findbyIdbook(id);
        this.postService.addpost(postBook);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        PostBook postBook = this.postBookService.findbyIdbook(id);

        model.addAttribute("postList", postBook.getPostList());
        model.addAttribute("targetPost", postBook.getPostList().get(0));

        model.addAttribute("bookList", postBook);
        model.addAttribute("targetBook", postBook);

        return "main";
    }
    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        this.postService.updatepost(id,title,content);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        this.postService.deletepost(id);
        return "redirect:/";
    }
}
