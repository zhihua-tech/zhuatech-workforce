/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.controller;

import cn.zhuatech.workforce.common.ApiResponse;
import cn.zhuatech.workforce.service.SchedulePublicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/workforce")
public class SchedulePublicationController {
    private final SchedulePublicationService service;
    public SchedulePublicationController(SchedulePublicationService service) { this.service = service; }

    @PostMapping("/schedule-publication")
    public ApiResponse<?> assess(@RequestBody SchedulePublicationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
