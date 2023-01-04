package io.myselectshop.controller;

import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.config.security.UserDetailsImpl;
import io.myselectshop.repository.UserRepository;
import io.myselectshop.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final FolderService folderService;

    @GetMapping("/shop")
    public ModelAndView shop() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", "고갱님");
        return modelAndView;
    }

    @GetMapping("/user-folder")
    public String getUserInfo(Model model, HttpServletRequest request) {
        model.addAttribute("folders", folderService.getFolders(request));
        return "/index :: #fragment";
    }

    @GetMapping("/user-info")
    @ResponseBody
    public String getUserName(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.user().getName();
    }
}