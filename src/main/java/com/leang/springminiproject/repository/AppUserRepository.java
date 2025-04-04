package com.leang.springminiproject.repository;

import com.leang.springminiproject.config.UUIDTypeHandler;
import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "appUserId", column = "app_user_id",javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = UUIDTypeHandler.class),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
                SELECT * FROM app_users
                WHERE email = #{email};
            """)
    AppUser getUserByEmail(String email);

    @Results(id = "profileMapper", value = {
            @Result(property = "appUserId", column = "app_user_id",javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = UUIDTypeHandler.class),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
               INSERT INTO app_users
                           VALUES (default, #{request.username}, #{request.email}, #{request.password}, default, default, #{request.profileImageUrl}, default, default)
                           RETURNING *;
            """)
    Profile register(@Param("request") AppUserRequest request);


    @ResultMap("appUserMapper")
    @Select("""
                SELECT * FROM app_users
                WHERE app_user_id = #{userId}
            """)
    Profile getUserById(UUID userId);
}
