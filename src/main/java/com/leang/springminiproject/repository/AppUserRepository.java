package com.leang.springminiproject.repository;

import com.leang.springminiproject.config.UUIDTypeHandler;
import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.model.request.AppUserRequest;
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
                WHERE email = #{email};
            """)
    AppUser getUserByEmail(String email);

    //    @Select("""
//                SELECT name FROM roles ar
//                INNER JOIN user_role ur
//                ON ar.role_id = ur.role_id
//                WHERE user_id = #{userId};
//            """)
//    List<String> getAllRolesByUserId(UUID userId);
    @ResultMap("appUserMapper")
    @Select("""
                INSERT INTO app_users
                VALUES (default, #{request.username}, #{request.email}, #{request.password},default,default,#{request.profileImageUrl},default,default)
                RETURNING *
            """)
    AppUser register(@Param("request") AppUserRequest request);

    //    @Insert("""
//                INSERT INTO user_role
//                VALUES (#{userId}, #{roleId})
//            """)
//    void insertUserIdAndRoleId(UUID roleId, UUID userId);
    @ResultMap("appUserMapper")
    @Select("""
                SELECT * FROM app_users
                WHERE app_user_id = #{userId}
            """)
    AppUser getUserById(@Param("userId") UUID userId);
}
