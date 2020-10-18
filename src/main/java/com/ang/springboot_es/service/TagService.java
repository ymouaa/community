package com.ang.springboot_es.service;

import com.ang.springboot_es.dao.PostTagMapper;
import com.ang.springboot_es.dao.TagMapper;
import com.ang.springboot_es.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;


    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    // 随机获取count个标签
    public List<Tag> findTagRand(Integer count) {
        if (count == null || count < 3) {
            return tagMapper.selectTagRand(3);
        }
        return tagMapper.selectTagRand(count);
    }

    public Tag findTagByName(String name) {
        return tagMapper.selectTagByName(name);
    }

    // 添加帖子与标签的关联记录 更新标签对应帖子数量 如不存在 就添加新标签
    public synchronized Tag addTagIfNotExist(String name, int postId) {
        // 手动控制事务
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        Tag tag = null;
        try {
            tag = findTagByName(name);
            if (tag == null) {
                tag = new Tag();
                tag.setTagName(name);
                tag.setCreateTime(new Date());
                tag.setPostCount(0);
                addTag(tag);
            }
            postTagMapper.insertPostTag(tag.getId(), postId);
            int count = postTagMapper.selectPostCountByTag(tag.getId());
            tagMapper.updatePostCount(tag.getId(), count);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        platformTransactionManager.commit(transactionStatus);
        return tag;
    }


    public int addTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    public int updateTagStatus(int id, int status) {
        return tagMapper.updateStatus(id, status);
    }

    public int updateTagScore(int id, double score) {
        return tagMapper.updateScore(id, score);
    }

    public int updateTagCount(int id, int count) {
        return tagMapper.updatePostCount(id, count);
    }
}
