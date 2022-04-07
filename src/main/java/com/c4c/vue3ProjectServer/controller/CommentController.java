package com.c4c.vue3ProjectServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c4c.vue3ProjectServer.form.CommentCreateReq;
import com.c4c.vue3ProjectServer.form.CommentRes;
import com.c4c.vue3ProjectServer.form.CommentUpdateReq;
import com.c4c.vue3ProjectServer.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentServiceImpl commentServiceImpl;

    /**
     * コメント情報取得
     * @return List{@literal<CommentRes>}
     */
    @GetMapping("/")
    public ResponseEntity<List<CommentRes>> index() {
        List<CommentRes> resFromList = commentServiceImpl.index();
        return ResponseEntity.ok(resFromList);
    }

    /**
     * コメント情報サーチ
     * @return List{@literal<CommentRes>}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentRes>> find(@PathVariable("userId") int userId) {
        List<CommentRes> resFromList = commentServiceImpl.find(userId);
        return ResponseEntity.ok(resFromList);
    }

    /**
     * コメント情報登録
     * @param CommentCreateReq
     */
    @PostMapping("/")
    public void create(@RequestBody CommentCreateReq reqForm) {
        commentServiceImpl.create(reqForm);
    }

    /**
     * コメント情報削除
     * @param commnetId
     */
    @PutMapping("/{commnetId}")
    public void delete(@PathVariable("commnetId") int commentId) {
        commentServiceImpl.delete(commentId);
    }

    /**
     * コメント情報更新
     * @param commnetId
     */
    @PostMapping("/{commnetId}")
    public void update(@RequestBody CommentUpdateReq reqForm) {
        commentServiceImpl.update(reqForm);
    }
}
