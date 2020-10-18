package com.ang.springboot_es;

import com.ang.springboot_es.dao.DiscussPostMapper;
import com.ang.springboot_es.dao.PostTagMapper;
import com.ang.springboot_es.dao.TagMapper;

import com.ang.springboot_es.entity.DiscussPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringbootEsApplication.class)
public class TestPostTag {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;

//    @Test
//    public void testInsertPostWithTags() {
//        DiscussPost discussPost = new DiscussPost();
//        discussPost.setTitle("test_tag");
//        discussPost.setUserId(111); // aaa
//        discussPost.setContent("test tag");
//        discussPost.setCreateTime(new Date());
//        discussPost.setTags("电影,游戏,体育"); // 1 2 4
//
//        int postId = discussPostMapper.insertDiscussPost(discussPost);
//
//        postTagMapper.insertPostTag(1, postId);
//        postTagMapper.insertPostTag(2, postId);
//        postTagMapper.insertPostTag(4, postId);
//    }

    @Test
    public void testSelect() {
//        List<DiscussPost> postList = postTagMapper.selectPostsByTagId(1);
//        if (postList != null) {
//            for (DiscussPost post : postList) {
//                System.out.println(post);
//            }
//        }
    }

}
