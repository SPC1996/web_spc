package com.keessi.web.mapper;

import com.keessi.web.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("SELECT * FROM news")
    @Results({
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "classId", column = "class_id", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "content", column = "content", javaType = String.class),
            @Result(property = "worker", column = "worker", javaType = String.class),
            @Result(property = "time", column = "time", javaType = Date.class),
            @Result(property = "image", column = "image", javaType = String.class)
    })
    List<News> selectAll() throws Exception;

    @Select("SELECT * FROM news WHERE id=#{id}")
    @Results({
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "classId", column = "class_id", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "content", column = "content", javaType = String.class),
            @Result(property = "worker", column = "worker", javaType = String.class),
            @Result(property = "time", column = "time", javaType = Date.class),
            @Result(property = "image", column = "image", javaType = String.class)
    })
    News selectById(String id) throws Exception;

    @Insert("INSERT INTO news(id, class_id, title, content, worker, time, image) VALUES" +
            "(#{id}, #{classId}, #{title}, #{content}, #{worker}, #{time}, #{image})")
    int insert(News news) throws Exception;

    @Update("UPDATE news SET class_id=#{classId}, title=#{title}, content=#{content}, " +
            "worker=#{worker}, time=#{time}, image=#{image} WHERE id=#{id}")
    int update(News news) throws Exception;

    @Delete("DELETE FROM news WHERE id=#{id}")
    int deleteById(String id) throws Exception;
}
