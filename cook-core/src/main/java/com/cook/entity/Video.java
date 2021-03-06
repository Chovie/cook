package com.cook.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Video {

    private String id;

    //标题
    private String title;

    //视频随机名 用于删除
    @ApiModelProperty(value = "视频随机名 用于上传第三方后删除")
    private String randomName;

    //播放次数
    private Integer playCount;

    //点赞数量
    private Integer admireCount;

    //图片名
    private String imgName;

    //视频路径
    private String videoUrl;

    //视频的图片名
    private String videoImgName;

    //视频内容描述
    private String content;


    public Video() {
    }

    public Video(String id, String title, String randomName, Integer playCount, Integer admireCount, String imgName, String videoUrl, String videoImgName, String content) {
        this.id = id;
        this.title = title;
        this.randomName = randomName;
        this.playCount = playCount;
        this.admireCount = admireCount;
        this.imgName = imgName;
        this.videoUrl = videoUrl;
        this.videoImgName = videoImgName;
        this.content = content;
    }
}