package com.ang.springboot_es.controller;

import com.ang.springboot_es.entity.DiscussPost;
import com.ang.springboot_es.entity.Page;
import com.ang.springboot_es.entity.Tag;
import com.ang.springboot_es.service.DiscussPostService;
import com.ang.springboot_es.service.ElasticsearchService;
import com.ang.springboot_es.service.LikeService;
import com.ang.springboot_es.service.TagService;
import com.ang.springboot_es.service.UserService;
import com.ang.springboot_es.util.DemoConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController implements DemoConstant {

	@Autowired
	private ElasticsearchService elasticsearchService;

	@Autowired
	private LikeService likeService;

	@Autowired
	private UserService userService;

	@Autowired
	private DiscussPostService discussPostService;

	@Autowired
	private TagService tagService;

	// search?keyword=dsfs&&
	// 搜索
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(String keyword, Page page, Model model) {
		// 搜索帖子
		org.springframework.data.domain.Page<DiscussPost> result = elasticsearchService.search(keyword,
				page.getCurrent() - 1, page.getLimit());
		List<Map<String, Object>> discussPosts = new ArrayList<>();
		if (result != null) {
			for (DiscussPost post : result) {
				Map<String, Object> map = new HashMap<>();
				String tags = post.getTags();
				List<String> tagList = new ArrayList<>();
				if (tags != null) {
					String[] tagString = tags.split(",");
					for (int i = 0; i < tagString.length; i++) {
						tagList.add(tagString[i]);
					}
				}
				post.setTags("");
				map.put("tags", tagList);
				map.put("post", post);
				map.put("user", userService.findUserById(post.getUserId()));
				map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_DISCUSSPOST, post.getId()));
				discussPosts.add(map);
			}
		}
		// 分页信息
		page.setPath("/search?keyword=" + keyword);
		page.setRows(result == null ? 0 : (int) result.getTotalElements());

		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", discussPosts);

		return "/site/search";
	}

	// /search/tag?tagname=xxxx
	@RequestMapping(value = "/search/tag", method = RequestMethod.GET)
	public String searchByTag(String tagname, Page page, Model model) {
		// 搜索帖子
		Tag tag = tagService.findTagByName(tagname);
		if (tag == null) {
			throw new RuntimeException("查询的标签不存在");
		}
		List<DiscussPost> result = discussPostService.findDiscussPostsByTag(tag.getId(), page.getOffset(),
				page.getLimit(), 0);
		List<Map<String, Object>> discussPosts = new ArrayList<>();
		if (result != null) {
			for (DiscussPost post : result) {
				Map<String, Object> map = new HashMap<>();
				String tags = post.getTags();
				List<String> tagList = new ArrayList<>();
				if (tags != null) {
					String[] tagString = tags.split(",");
					for (int i = 0; i < tagString.length; i++) {
						tagList.add(tagString[i]);
					}
				}
				post.setTags("");
				map.put("tags", tagList);
				map.put("post", post);
				map.put("user", userService.findUserById(post.getUserId()));
				map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_DISCUSSPOST, post.getId()));
				discussPosts.add(map);
			}
		}
		// 分页信息
		page.setPath("/search/tag?tagname=" + tagname);
		page.setRows(discussPosts == null ? 0 : (int) result.size());

		model.addAttribute("tagname", tagname);
		model.addAttribute("posts", discussPosts);

		return "/site/tag";
	}
}
