package com.ang.springboot_es.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ang.springboot_es.entity.Authority;

@Mapper
public interface AuthorityMapper {
    String Authority = "authority";
    String User_Authority = "user_authority";
    String SELECT_FIELDS = "id,name";

    @Select("select " + SELECT_FIELDS + " from " + Authority + "," + User_Authority + " where user_id=#{userId} "
            + " and id=authority_id")
    List<Authority> selectAuthoritysByUserId(@Param("userId") int userId);

    @Select("select " + SELECT_FIELDS + " from " + Authority)
    List<Authority> selectAllAuthority();

    @Insert("insert into " + User_Authority + " values(#{uid},#{aid})")
    int insertAuthorityToUser(@Param("uid") int uid, @Param("aid") int aid);

    @Delete("delete from " + User_Authority + " where uid=#{uid} and aid=#{aid}")
    int delelteAuthorityOnUser(@Param("uid") int uid, @Param("aid") int aid);

	/*
     * select * from authority where id not in ( select authority_id where
	 * user_id=#{uid} )
	 * 
	 */

    @Select("select " + SELECT_FIELDS + " from " + Authority + " where id not in (" + " select authority_id from "
            + User_Authority + " where user_id=#{uid} " + " ) ")
    List<Authority> selectOtherAuthority(@Param("uid") int uid);

}
