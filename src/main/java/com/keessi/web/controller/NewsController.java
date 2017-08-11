package com.keessi.web.controller;

import com.keessi.web.entity.News;
import com.keessi.web.mapper.NewsMapper;
import com.keessi.web.message.RequestMsg;
import com.keessi.web.message.ResponseMsg;
import com.keessi.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseMsg findAll() {
        return newsService.findAll();
    }

    @RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
    public ResponseMsg findOne(@PathVariable(value = "id") String id) {
        return newsService.findOne(id);
    }

    @RequestMapping(value = "/addOne", method = RequestMethod.POST)
    public ResponseMsg addOne(@RequestBody RequestMsg msg) {
        return newsService.addOne(msg);
    }

    @RequestMapping(value = "/saveOne", method = RequestMethod.POST)
    public ResponseMsg saveOne(@RequestBody RequestMsg msg) {
        return newsService.saveOne(msg);
    }

    @RequestMapping(value = "/removeOne/{id}", method = RequestMethod.DELETE)
    public ResponseMsg removeOne(@PathVariable("id") String id) {
        return newsService.deleteOne(id);
    }
}
