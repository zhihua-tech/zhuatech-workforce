/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.controller;

import cn.zhuatech.workforce.common.ApiResponse;
import cn.zhuatech.workforce.service.SchedulePublicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/workforce")
public class SchedulePublicationController {
    private final SchedulePublicationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public SchedulePublicationController(SchedulePublicationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/schedule-publication")
    public ApiResponse<?> assess(@RequestBody SchedulePublicationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
