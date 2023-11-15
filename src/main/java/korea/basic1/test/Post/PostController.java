package korea.basic1.test.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.post.PostRepository;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/korea/basic1/test")
    @ResponseBody public String test() {
        return "korea/basic1/test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        List<Post> postList = postService.findAllabout();

        if(postList.isEmpty()){
            postService.newPost();
            return "redirect:/";
        }

        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));


        return "main";
    }

    @PostMapping("/write")
    public String write(@RequestParam Long id) {
        this.postService.newPost();

        return "redirect:/book/detail/" + id ;
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Post post = this.postService.findByID(id);
        model.addAttribute("targetPost", post);
        model.addAttribute("postList", postService.findAllabout());

        return "main";
    }
    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        this. postService.updatepost(id, title, content);
        return "redirect:/detail/" + id;
    }
    @PostMapping("/delete")
    public String update(Long id) {
        this. postService.deletepost(id);
        return "redirect:/";
    }
}
