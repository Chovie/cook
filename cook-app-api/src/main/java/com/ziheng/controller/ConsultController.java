package com.ziheng.controller;

import com.cook.response.ApiResponse;
import com.ziheng.service.ConsultPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 资讯api
 * @author: ziHeng
 * @create: 2018-05-20 15:44
 **/

@RestController
@RequestMapping("/consultApi")
@Api(value = "/consultApi",description = "资讯发布Api")
public class ConsultController {

    private ConsultPostService consultPostService;

    public ConsultController(ConsultPostService consultPostService) {
        this.consultPostService = consultPostService;
    }

    /**
      * @Description: 用户发布图文资讯
      * @Author: ziHeng
      * @Date: 2018/5/20 下午4:45
      * @Param: [consultType, userId, publisherName, title, content, imageName]
      * @return: com.cook.response.ApiResponse
      */
    @PostMapping("/releaseImageTextConsult")
    @ApiOperation(value = "用户发布图文资讯")
    public ApiResponse releaseImageTextConsult(@RequestParam Short consultType,
                                               @RequestParam String userId,
                                               @RequestParam String publisherName,
                                               @RequestParam String title,
                                               @RequestParam String content,
                                               @RequestParam String imageName){

        return ApiResponse.ofSuccess(consultPostService.releaseImageTextConsult(consultType,userId,publisherName,title,content,imageName));
    }

    /**
      * @Description: 用户发布视频资讯
      * @Author: ziHeng
      * @Date: 2018/5/20 下午4:46
      * @Param: [consultType, userId, publisherName, title, imageName, videoImgName, content]
      * @return: com.cook.response.ApiResponse
      */
    @PostMapping("releaseVideoConsult")
    @ApiOperation(value = "用户发布视频资讯")
    public ApiResponse releaseVideoConsult(@RequestParam Short consultType,
                                           @RequestParam String userId,
                                           @RequestParam String publisherName,
                                           @RequestParam String title,
                                           @RequestParam String imageName,
                                           @RequestParam String videoImgName,
                                           @RequestParam String content){

        return ApiResponse.ofSuccess(consultPostService.releaseVideoConsult(consultType,userId,publisherName,title,imageName,videoImgName,content));

    }
}
