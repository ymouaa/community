package com.ang.springboot_es.dao;

import com.ang.springboot_es.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {

    Tag selectTagById(@Param("id") int id);

    Tag selectTagByName(@Param("name") String name);

    int insertTag(Tag tag);

    int updateStatus(@Param("id") int id, @Param("status") int status);

    int updatePostCount(@Param("id") int id, @Param("count") int count);

    int updateScore(@Param("id") int id, @Param("score") double score);

    List<Tag> selectTagRand(@Param("count") int count);

}
