package com.ang.springboot_es.dao;

import com.ang.springboot_es.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTagMapper {


    // 根据tag_id查关联帖子
    List<DiscussPost> selectPostsByTagId(@Param("tagId") int tagId,@Param("offset") int offset,@Param("limit") int limit,@Param("mode") int orderMode);

    int insertPostTag(@Param("tagId") int tagId, @Param("postId") int postId);
    // 查询某个标签有多少个帖子
    int selectPostCountByTag(@Param("tagId")int tagId);

}
