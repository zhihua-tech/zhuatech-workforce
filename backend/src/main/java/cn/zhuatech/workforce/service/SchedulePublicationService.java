/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SchedulePublicationService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.scheduleId() == null || request.scheduleId().isBlank()) blockers.add("排班计划编号不能为空");
        if (!request.certificationsValid()) blockers.add("关键岗位资质无效");
        if (!request.restRulesMet()) blockers.add("连续工作与休息规则不满足");
        if (!request.unionRulesMet()) blockers.add("集体协议或劳动规则不满足");
        if (!request.payrollMappingValid()) blockers.add("班次与薪资规则映射无效");
        if (!request.auditReady()) blockers.add("排班发布审计证据不完整");
        if (!request.coverageMet()) actions.add("补齐岗位覆盖");
        if (!request.overtimeApproved()) actions.add("审批计划加班");
        if (!request.availabilityConfirmed()) actions.add("确认员工可用性");
        if (!request.conflictsResolved()) actions.add("解决班次冲突");
        if (!request.managerApproved()) actions.add("取得排班经理批准");
        if (!request.employeeNoticePrepared()) actions.add("准备员工排班通知");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.PUBLISH : Decision.REBALANCE;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { PUBLISH, REBALANCE, BLOCKED }
    public record Request(String scheduleId, boolean coverageMet, boolean certificationsValid,
                          boolean restRulesMet, boolean overtimeApproved, boolean availabilityConfirmed,
                          boolean unionRulesMet, boolean conflictsResolved, boolean managerApproved,
                          boolean employeeNoticePrepared, boolean payrollMappingValid, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
