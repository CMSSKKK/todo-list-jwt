package kr.codesquad.todolist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("test")
public class JwtTestController {

    private final Logger log = LoggerFactory.getLogger(JwtTestController.class);

    @GetMapping
    public void getAccessToken(HttpServletRequest request) {

        String access_token = request.getHeader("ACCESS_TOKEN");

        log.info("access_token : {}", access_token);
    }
}
