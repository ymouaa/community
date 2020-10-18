package com.ang.springboot_es.concurrent;

import com.ang.springboot_es.SpringbootEsApplication;
import com.ang.springboot_es.entity.DiscussPost;
import com.ang.springboot_es.entity.Tag;
import com.ang.springboot_es.service.DiscussPostService;
import com.ang.springboot_es.service.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringbootEsApplication.class)
public class TestInsert {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private TagService tagService;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    work();
                }
            }).start();

        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tag tag = tagService.findTagByName("111");
        Assert.assertEquals(10,tag.getPostCount());
    }

    public /*synchronized*/ void work() {
        DiscussPost post = new DiscussPost();
        post.setUserId(111);
        post.setContent("concurrent");
        post.setTitle("concurrent");
        post.setTags("111");
        discussPostService.addDiscussPost(post);
    }


}
