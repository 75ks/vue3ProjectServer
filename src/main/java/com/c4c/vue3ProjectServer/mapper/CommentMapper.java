package com.c4c.vue3ProjectServer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.c4c.vue3ProjectServer.entity.Comment;
import com.c4c.vue3ProjectServer.entity.CommentEx01;

@Mapper
public interface CommentMapper {
    @Select(
            "SELECT\n"
          + "    COMMENT.COMMENT_ID\n"
          + "    ,COMMENT.USER_ID\n"
          + "    ,COMMENT.COMMENT_DETAIL\n"
          + "    ,COMMENT.CREATE_DATE_TIME\n"
          + "    ,COMMENT.UPDATE_DATE_TIME\n"
          + "    ,COMMENT.DELETE_FLG\n"
          + "    ,USER.USER_NAME\n"
          + "FROM\n"
          + "    COMMENT\n"
          + "LEFT JOIN\n"
          + "    USER\n"
          + "    ON COMMENT.USER_ID = USER.USER_ID\n"
          + "WHERE\n"
          + "    COMMENT.DELETE_FLG = 0;"
    )
    @Results(value = {
            @Result(column = "COMMENT_ID", property = "commentId"),
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "COMMENT_DETAIL", property = "commentDetail"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg"),
            @Result(column = "USER_NAME", property = "userName")
    })
    List<CommentEx01> selectComments();

    @Select(
            "SELECT\n"
                    + "    COMMENT.COMMENT_ID\n"
                    + "    ,COMMENT.USER_ID\n"
                    + "    ,COMMENT.COMMENT_DETAIL\n"
                    + "    ,COMMENT.CREATE_DATE_TIME\n"
                    + "    ,COMMENT.UPDATE_DATE_TIME\n"
                    + "    ,COMMENT.DELETE_FLG\n"
                    + "    ,USER.USER_NAME\n"
                    + "FROM\n"
                    + "    COMMENT\n"
                    + "LEFT JOIN\n"
                    + "    USER\n"
                    + "    ON COMMENT.USER_ID = USER.USER_ID\n"
                    + "WHERE\n"
                    + "    COMMENT.DELETE_FLG = 0\n"
                    + "    AND COMMENT.USER_ID = #{userId};"
            )
    @Results(value = {
            @Result(column = "COMMENT_ID", property = "commentId"),
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "COMMENT_DETAIL", property = "commentDetail"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg"),
            @Result(column = "USER_NAME", property = "userName")
    })
    List<CommentEx01> findComments(int userId);

    @Insert(
            "INSERT INTO\n"
          + "    COMMENT (\n"
          + "    COMMENT_ID\n"
          + "    ,USER_ID\n"
          + "    ,COMMENT_DETAIL\n"
          + "    ,CREATE_DATE_TIME\n"
          + "    ,UPDATE_DATE_TIME\n"
          + "    ,DELETE_FLG\n"
          + ")\n"
          + "VALUES (\n"
          + "    #{commentId}\n"
          + "    ,#{userId}\n"
          + "    ,#{commentDetail}\n"
          + "    ,#{createDateTime}\n"
          + "    ,#{updateDateTime}\n"
          + "    ,#{deleteFlg}\n"
          + ");"
    )
    @Results(value = {
            @Result(column = "COMMENT_ID", property = "commentId"),
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "COMMENT_DETAIL", property = "commentDetail"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    void insertComments(Comment comment);

    @Update(
            "UPDATE\n"
          + "    COMMENT\n"
          + "SET\n"
          + "    UPDATE_DATE_TIME = #{updateDateTime}\n"
          + "    ,DELETE_FLG = 1\n"
          + "WHERE\n"
          + "    COMMENT_ID = #{commentId};"
    )
    @Results(value = {
            @Result(column = "COMMENT_ID", property = "commentId"),
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "COMMENT_DETAIL", property = "commentDetail"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    void deleteComments(Comment comment);

    @Update(
            "UPDATE\n"
          + "    COMMENT\n"
          + "SET\n"
          + "    UPDATE_DATE_TIME = #{updateDateTime}\n"
          + "    ,COMMENT_DETAIL = #{commentDetail}\n"
          + "WHERE\n"
          + "    COMMENT_ID = #{commentId};"
    )
    @Results(value = {
            @Result(column = "COMMENT_ID", property = "commentId"),
            @Result(column = "USER_ID", property = "userId"),
            @Result(column = "COMMENT_DETAIL", property = "commentDetail"),
            @Result(column = "CREATE_DATE_TIME", property = "createDateTime"),
            @Result(column = "UPDATE_DATE_TIME", property = "updateDateTime"),
            @Result(column = "DELETE_FLG", property = "deleteFlg")
    })
    void updateComments(Comment comment);
}
