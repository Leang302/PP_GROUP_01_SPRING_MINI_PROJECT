package com.leang.springminiproject.repository;

import com.leang.springminiproject.config.UUIDTypeHandler;
import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import com.leang.springminiproject.model.request.ProfileRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = UUIDTypeHandler.class),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
                SELECT * FROM app_users
                WHERE email = #{email} or username=#{identifier};
            """)
    AppUser getUserByIdentifier(String identifier);

    @Results(id = "profileMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = UUIDTypeHandler.class),
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

    @Select("""
                UPDATE  app_users set is_verified =true where email=#{email};
            """)
    void verifyUser(String email);

    @ResultMap("profileMapper")
    @Select("SELECT * FROM app_users WHERE app_user_id = #{userId}")
    Profile getUserById(@Param("userId") UUID userId);

    @ResultMap("profileMapper")
    @Select("""
                UPDATE  app_users set username=#{request.username},profile_image=#{request.profileImageUrl} where app_user_id=#{userId} returning *;
            """)
    Profile updateAppUser(@Param("userId") UUID userId,@Param("request") ProfileRequest request);


    @Delete("""
                delete from  app_users where app_user_id=#{userId};
            """)
    void deleteAppUser(@Param("userId") UUID userId);
}
