package com.c4c.vue3ProjectServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.c4c.vue3ProjectServer.entity.User;

@Mapper
public interface UserMapper {
    @Select(
            "SELECT\n"
            + "   USER_ID\n"
            + "   ,USER_NAME\n"
            + "   ,USER_NAME_KANA\n"
            + "   ,EMAIL\n"
            + "   ,LOGIN_PW\n"
            + "   ,CREATE_DATE_TIME\n"
            + "   ,UPDATE_DATE_TIME\n"
            + "   ,DELETE_FLG\n"
            + "FROM\n"
            + "   USER\n"
            + "WHERE\n"
            + "   USER_ID = #{userId}"
            + "   AND DELETE_FLG = 0;"
    )
    @Results(value = {
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "USER_NAME", property = "userName"),
            @Result(column = "USER_NAME_KANA", property = "userNameKana"),
            @Result(column = "EMAIL", property = "email"),
            @Result(column = "LOGIN_PW", property = "loginPw"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    User searchUsersByUserId(int userId);

    @Select(
            "SELECT\n"
            + "   USER_ID\n"
            + "   ,USER_NAME\n"
            + "   ,USER_NAME_KANA\n"
            + "   ,EMAIL\n"
            + "   ,LOGIN_PW\n"
            + "   ,CREATE_DATE_TIME\n"
            + "   ,UPDATE_DATE_TIME\n"
            + "   ,DELETE_FLG\n"
            + "FROM\n"
            + "   USER\n"
            + "WHERE\n"
            + "   EMAIL = #{email}"
            + "   AND DELETE_FLG = 0;"
    )
    @Results(value = {
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "USER_NAME", property = "userName"),
            @Result(column = "USER_NAME_KANA", property = "userNameKana"),
            @Result(column = "EMAIL", property = "email"),
            @Result(column = "LOGIN_PW", property = "loginPw"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    User searchUsersByEmail(String email);

    @Select(
            "SELECT\n"
            + "   USER_ID\n"
            + "   ,USER_NAME\n"
            + "   ,USER_NAME_KANA\n"
            + "   ,EMAIL\n"
            + "   ,LOGIN_PW\n"
            + "   ,CREATE_DATE_TIME\n"
            + "   ,UPDATE_DATE_TIME\n"
            + "   ,DELETE_FLG\n"
            + "FROM\n"
            + "   USER\n"
            + "WHERE\n"
            + "   DELETE_FLG = 0;"
    )
    @Results(value = {
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "USER_NAME", property = "userName"),
            @Result(column = "USER_NAME_KANA", property = "userNameKana"),
            @Result(column = "EMAIL", property = "email"),
            @Result(column = "LOGIN_PW", property = "loginPw"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    List<User> selectUsers();

    @Insert(
            "INSERT INTO\n"
            + "USER (\n"
            + "   USER_ID\n"
            + "   ,USER_NAME\n"
            + "   ,USER_NAME_KANA\n"
            + "   ,EMAIL\n"
            + "   ,LOGIN_PW\n"
            + "   ,CREATE_DATE_TIME\n"
            + "   ,UPDATE_DATE_TIME\n"
            + "   ,DELETE_FLG\n"
            + ")\n"
            + "VALUES (\n"
            + "   #{userId}\n"
            + "   ,#{userName}\n"
            + "   ,#{userNameKana}\n"
            + "   ,#{email}\n"
            + "   ,#{loginPw}\n"
            + "   ,#{createDateTime}\n"
            + "   ,#{updateDateTime}\n"
            + "   ,#{deleteFlg}\n"
            + ");"
    )
    @Results(value = {
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "USER_NAME", property = "userName"),
            @Result(column = "USER_NAME_KANA", property = "userNameKana"),
            @Result(column = "EMAIL", property = "email"),
            @Result(column = "LOGIN_PW", property = "loginPw"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    void insertUsers(User user);
}
