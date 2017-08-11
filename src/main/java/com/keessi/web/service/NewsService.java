package com.keessi.web.service;


import com.keessi.web.message.RequestMsg;
import com.keessi.web.message.ResponseMsg;

public interface NewsService {
    ResponseMsg findAll();

    ResponseMsg findOne(String id);

    ResponseMsg addOne(RequestMsg msg);

    ResponseMsg saveOne(RequestMsg msg);

    ResponseMsg deleteOne(String id);
}
