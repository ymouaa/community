package com.ang.springboot_es.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ang.springboot_es.entity.DiscussPost;
import com.ang.springboot_es.service.DiscussPostService;
import com.ang.springboot_es.service.ElasticsearchService;
import com.ang.springboot_es.service.LikeService;
import com.ang.springboot_es.util.DemoConstant;
import com.ang.springboot_es.util.RedisKeyUtil;

public class PostScoreRefreshJob implements Job, DemoConstant {


    private static final Logger logger = LoggerFactory.getLogger(PostScoreRefreshJob.class);


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    private static final Date epoch;

    static {
        try {
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("初始化纪元时间失败", e);
        }
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String redisKey = RedisKeyUtil.getPostKey();
        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);

        if (operations.size() == 0) {
            logger.info("[任务取消] 没有需要刷新的帖子");
            return;
        }

        logger.info("[任务开始] 正在刷新帖子分数:" + operations.size());

        while (operations.size() > 0) {

            refresh((Integer) operations.pop());

        }
        logger.info("[任务结束] 帖子分数刷新完毕");
    }


    private void refresh(int postId) {
        DiscussPost post = discussPostService.findDiscussPostById(postId);
        if (post == null) {
            logger.error("[该帖子不存在] id = " + postId);
            return;
        }

        // 是否精华
        boolean wonderful = post.getStatus() == 1 ? true : false;
        // 评论数量
        int commentCount = post.getCommentCount();
        // 点赞的数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_DISCUSSPOST, postId);

        // 权重
        double w = (wonderful ? 75 : 0) + commentCount * 10 + likeCount * 2;
        // 分数 = 权重 + 距离天数
        double score = Math.log10(Math.max(w, 1)) + (post.getCreateTime().getTime() - epoch.getTime()) / (1000 * 3600 * 24);

        // 更新帖子分数
        discussPostService.updateScore(postId, score);

        // 同步ElasticSearch 因为它那里也用到了score排序
        post.setScore(score);
        elasticsearchService.save(post);

    }

}
