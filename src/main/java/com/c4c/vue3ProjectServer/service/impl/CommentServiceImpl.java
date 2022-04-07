package com.c4c.vue3ProjectServer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c4c.vue3ProjectServer.entity.Comment;
import com.c4c.vue3ProjectServer.entity.CommentEx01;
import com.c4c.vue3ProjectServer.form.CommentCreateReq;
import com.c4c.vue3ProjectServer.form.CommentRes;
import com.c4c.vue3ProjectServer.form.CommentUpdateReq;
import com.c4c.vue3ProjectServer.mapper.CommentMapper;
import com.c4c.vue3ProjectServer.service.CommentService;
import com.c4c.vue3ProjectServer.utils.Utils;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * コメント情報取得
     * @return List{@literal<CommentRes>}
     */
    public List<CommentRes> index() {
        // SELECTを実行し、データを取得する
        List<CommentEx01> commentList = commentMapper.selectComments();
        // Formにデータを詰める
        List<CommentRes> resFormList = new ArrayList<>();
        for (CommentEx01 comment : commentList) {
            CommentRes resForm = new CommentRes();
            resForm.setCommentId(comment.getCommentId());
            resForm.setUserId(comment.getUserId());
            resForm.setCommentDetail(comment.getCommentDetail());
            resForm.setUserName(comment.getUserName());
            resFormList.add(resForm);
        }
        return resFormList;
    }

    /**
     * コメント情報サーチ
     * @param CommentCreateReq
     */
    public List<CommentRes> find(int userId) {
        // SELECTを実行し、データを取得する
        List<CommentEx01> commentList = commentMapper.findComments(userId);
        // Formにデータを詰める
        List<CommentRes> resFormList = new ArrayList<>();
        for (CommentEx01 comment : commentList) {
            CommentRes resForm = new CommentRes();
            resForm.setCommentId(comment.getCommentId());
            resForm.setUserId(comment.getUserId());
            resForm.setCommentDetail(comment.getCommentDetail());
            resForm.setUserName(comment.getUserName());
            resFormList.add(resForm);
        }
        return resFormList;
    }

    /**
     * コメント情報登録
     * @param CommentCreateReq
     */
    public void create(CommentCreateReq reqForm) {
        Comment comment = new Comment();
        // Entityにデータを詰める
        BeanUtils.copyProperties(reqForm, comment);
        comment.setCreateDateTime(Utils.CURRENT_DATE_TIME);
        comment.setUpdateDateTime(Utils.CURRENT_DATE_TIME);
        comment.setDeleteFlg(Utils.OFF);
        commentMapper.insertComments(comment);
    }

    /**
     * コメント情報削除
     * @param commentId
     */
    public void delete(int commentId) {
        Comment comment = new Comment();
        // Entityにデータを詰める
        comment.setCommentId(commentId);
        comment.setUpdateDateTime(Utils.CURRENT_DATE_TIME);
        commentMapper.deleteComments(comment);
    }

    /**
     * コメント情報更新
     * @param CommentCreateReq
     */
    public void update(CommentUpdateReq reqForm) {
        Comment comment = new Comment();
        // Entityにデータを詰める
        BeanUtils.copyProperties(reqForm, comment);
        comment.setUpdateDateTime(Utils.CURRENT_DATE_TIME);
        commentMapper.updateComments(comment);
    }
}
