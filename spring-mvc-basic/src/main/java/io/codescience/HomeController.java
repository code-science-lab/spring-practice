package io.codescience;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "我的个人博客");
        model.addAttribute("content", "欢迎来到我的博客！这里是一些示例内容。");
        return "index";
    }
}