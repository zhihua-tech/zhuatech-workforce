/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.controller;
import cn.zhuatech.workforce.common.ApiResponse;import cn.zhuatech.workforce.service.EnterpriseWorkforceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/workforce") public class EnterpriseWorkforceController {
 private final EnterpriseWorkforceService service;public EnterpriseWorkforceController(EnterpriseWorkforceService service){this.service=service;}
 @PostMapping("/settle-month") ApiResponse<EnterpriseWorkforceService.MonthlyResult> settle(@Valid @RequestBody EnterpriseWorkforceService.MonthlyRequest request){return ApiResponse.ok(service.settle(request));}
}
