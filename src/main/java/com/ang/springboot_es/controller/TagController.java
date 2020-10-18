package com.ang.springboot_es.controller;


import com.ang.springboot_es.entity.Tag;
import com.ang.springboot_es.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/rand")
    @ResponseBody
    public String getRandTags() {
        List<Tag> tags = tagService.findTagRand(4);
        StringBuilder str = new StringBuilder();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                str.append(tags.get(i).getTagName());
                if (i != tags.size() - 1) str.append(",");
            }
        }
        return str.toString();
    }
}
