package com.keessi.web.service.imp;

import com.keessi.web.entity.News;
import com.keessi.web.mapper.NewsMapper;
import com.keessi.web.message.RequestMsg;
import com.keessi.web.message.ResponseMsg;
import com.keessi.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImp implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ResponseMsg findAll() {
        ResponseMsg response = new ResponseMsg();
        try {
            List<News> news = newsMapper.selectAll();
            response.setStatus(ResponseMsg.Status.SUCCESS);
            response.setContent(news);
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseMsg.Status.FAILED);
            response.setContent(e.getCause());
            return response;
        }
    }

    @Override
    public ResponseMsg findOne(String id) {
        ResponseMsg response = new ResponseMsg();
        try {
            News news = newsMapper.selectById(id);
            response.setStatus(ResponseMsg.Status.SUCCESS);
            response.setContent(news);
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseMsg.Status.FAILED);
            response.setContent(e.getCause());
            return response;
        }
    }

    @Override
    public ResponseMsg addOne(RequestMsg msg) {
        ResponseMsg response = new ResponseMsg();
        try {
            int res = newsMapper.insert(News.transfer((Map<String, Object>) msg.getContent()));
            if (res > 0) {
                response.setStatus(ResponseMsg.Status.SUCCESS);
                response.setContent(res + " row add");
            } else {
                response.setStatus(ResponseMsg.Status.FAILED);
                response.setContent(res + " row add");
            }
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseMsg.Status.FAILED);
            response.setContent(e.getMessage());
            return response;
        }
    }

    @Override
    public ResponseMsg saveOne(RequestMsg msg) {
        ResponseMsg response = new ResponseMsg();
        try {
            int res = newsMapper.update( News.transfer((Map<String, Object>) msg.getContent()));
            if (res > 0) {
                response.setStatus(ResponseMsg.Status.SUCCESS);
                response.setContent(res + " row save");
            } else {
                response.setStatus(ResponseMsg.Status.FAILED);
                response.setContent(res + " row save");
            }
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseMsg.Status.FAILED);
            response.setContent(e.getCause());
            return response;
        }
    }

    @Override
    public ResponseMsg deleteOne(String id) {
        ResponseMsg response = new ResponseMsg();
        try {
            int res = newsMapper.deleteById(id);
            if (res > 0) {
                response.setStatus(ResponseMsg.Status.SUCCESS);
                response.setContent(res + " row delete");
            } else {
                response.setStatus(ResponseMsg.Status.FAILED);
                response.setContent(res + " row delete");
            }
            return response;
        } catch (Exception e) {
            response.setStatus(ResponseMsg.Status.FAILED);
            response.setContent(e.getCause());
            return response;
        }
    }
}
