package com.c4c.vue3ProjectServer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.c4c.vue3ProjectServer.entity.User;
import com.c4c.vue3ProjectServer.form.SignUpReq;
import com.c4c.vue3ProjectServer.form.UserRes;
import com.c4c.vue3ProjectServer.mapper.UserMapper;
import com.c4c.vue3ProjectServer.service.UserService;
import com.c4c.vue3ProjectServer.utils.Utils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * ユーザー情報検索
     * @param int
     * @return UserRes
     */
    public UserRes search(int userId) {
        // SELECTを実行し、データを取得する
        User user = userMapper.searchUsersByUserId(userId);
        // Formにデータを詰める
        UserRes resForm = new UserRes();
        resForm.setUserId(user.getUserId());
        resForm.setUserName(user.getUserName());
        resForm.setUserNameKana(user.getUserNameKana());
        resForm.setEmail(user.getEmail());
        resForm.setLoginPw(user.getLoginPw());
        return resForm;
    }

    /**
     * ユーザー情報取得
     * @return List{@literal<UserRes>}
     */
    public List<UserRes> index() {
        // SELECTを実行し、データを取得する
        List<User> userList = userMapper.selectUsers();
        // Formにデータを詰める
        List<UserRes> resFormList = new ArrayList<>();
        for (User user : userList) {
            UserRes resForm = new UserRes();
            resForm.setUserId(user.getUserId());
            resForm.setUserName(user.getUserName());
            resForm.setUserNameKana(user.getUserNameKana());
            resForm.setEmail(user.getEmail());
            resForm.setLoginPw(user.getLoginPw());
            resFormList.add(resForm);
        }
        return resFormList;
    }

    /**
     * ユーザー情報登録
     * @param SignUpReq
     */
    public void create(SignUpReq reqForm) {
        User user = new User();
        // Entityにデータを詰める
        BeanUtils.copyProperties(reqForm, user);
        user.setCreateDateTime(Utils.CURRENT_DATE_TIME);
        user.setUpdateDateTime(Utils.CURRENT_DATE_TIME);
        user.setDeleteFlg(Utils.OFF);
        // パスワードをハッシュ化
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        String encodeedPassword = bcpe.encode(reqForm.getLoginPw());
        user.setLoginPw(encodeedPassword);
        // INSERTを実行し、データを登録する
        userMapper.insertUsers(user);
    }
}
