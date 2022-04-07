package com.c4c.vue3ProjectServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4c.vue3ProjectServer.form.SignUpReq;
import com.c4c.vue3ProjectServer.form.UserRes;
import com.c4c.vue3ProjectServer.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    /**
     * ユーザー情報検索
     * @param PathVariable
     * @return UserRes
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserRes> search(@PathVariable("userId") int userId) {
        UserRes resForm = userServiceImpl.search(userId);
        return ResponseEntity.ok(resForm);
    }

    /**
     * ユーザー情報取得
     * @return List{@literal<UserRes>}
     */
    @GetMapping("/")
    public ResponseEntity<List<UserRes>> index() {
        List<UserRes> resFromList = userServiceImpl.index();
        return ResponseEntity.ok(resFromList);
    }

    /**
     * ユーザー情報登録
     * @param SignUpReq
     */
    @PostMapping("/")
    public void create(@RequestBody SignUpReq reqForm) {
        userServiceImpl.create(reqForm);
    }
}
