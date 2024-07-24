package com.evo.evoproject.controller.user;

import com.evo.evoproject.domain.user.User;
import com.evo.evoproject.service.user.TermsService;
import com.evo.evoproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TermsService termsService;

    // 약관 동의 폼
    @GetMapping("/terms")
    public String showTermsForm(HttpSession session, Model model) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/index"; // 로그인 상태라면 인덱스 페이지로 리디렉트
        }
        session.setAttribute("acceptedTerms", false); // 약관 동의 초기화

        Map<String, String> serviceTerms = termsService.getLatestTerms("service_term");
        model.addAttribute("serviceTerms", serviceTerms.get("content"));

        Map<String, String> privacyPolicy = termsService.getLatestTerms("privacy_policy");
        model.addAttribute("privacyPolicy", privacyPolicy.get("content"));

        Map<String, String> dataDelegationConsent = termsService.getLatestTerms("data_delegation_consent");
        model.addAttribute("dataDelegationConsent", dataDelegationConsent.get("content"));

        Map<String, String> marketingConsent = termsService.getLatestTerms("marketing_consent");
        model.addAttribute("marketingConsent", marketingConsent.get("content"));

        return "terms";
    }


    // 약관 동의 처리
    @PostMapping("/terms")
    public String acceptTerms(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/"; // 로그인 상태라면 인덱스 페이지로 리디렉트
        }
        session.setAttribute("acceptedTerms", true); // 약관 동의 표시
        return "redirect:/register";
    }

    // 회원가입 폼
    @GetMapping("/register")
    public String showRegisterForm(HttpSession session, Model model) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/"; // 로그인 상태라면 인덱스 페이지로 리디렉트
        }
        Boolean acceptedTerms = (Boolean) session.getAttribute("acceptedTerms");
        if (acceptedTerms == null || !acceptedTerms) {
            return "redirect:/terms"; // 약관에 동의하지 않았다면 약관 페이지로 리디렉트
        }
        model.addAttribute("user", new User());
        return "register";
    }

    // 회원가입 처리 메소드
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpSession session, Model model) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/"; // 로그인 상태라면 인덱스 페이지로 리디렉트
        }
        if (userService.isUserIdTaken(user.getUserId())) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }
    // 중복 아이디 체크 메소드
    @GetMapping("/check-username")
    @ResponseBody
    public Map<String, Object> checkUsername(@RequestParam String userId) {
        boolean isTaken = userService.isUserIdTaken(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("available", !isTaken);
        response.put("message", isTaken ? "이미 존재하는 아이디입니다." : "사용 가능한 아이디입니다.");
        return response;
    }

    // 로그인 폼
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 마이페이지 폼
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            // userId가 없을 경우 로그인 페이지로 리디렉션
            return "redirect:/login";
        }
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        return "mypage";
    }
    // 로그아웃 메소드
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login";
    }

    // 회원탈퇴 폼
    @GetMapping("/withdrawal")
    public String withdrawalForm(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        return "withdrawal";
    }
    // 회원탈퇴 처리
    @PostMapping("/delete-user")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteUser(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        User user = userService.findUserByUserId(userId);
        Map<String, String> response = new HashMap<>();

        if (userService.checkPassword(user, password)) {
            try {
                userService.deleteUser(userId);
                session.invalidate(); // 세션 무효화
                response.put("message", "회원탈퇴가 완료되었습니다.");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                response.put("error", "회원 탈퇴 처리 중 오류가 발생했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            response.put("error", "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }





}
