package io.myselectshop.controller;

import io.jsonwebtoken.Claims;
import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.entity.User;
import io.myselectshop.repository.UserRepository;
import io.myselectshop.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public String getUserName(HttpServletRequest request) {
        return getJwtUser(request).getName();
    }

    private User getJwtUser(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw new SecurityException("인증 실패");
        }
        Claims claims = jwtUtil.getClaims(token);
        Long userId = Long.parseLong(claims.get("userId").toString());
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }
}