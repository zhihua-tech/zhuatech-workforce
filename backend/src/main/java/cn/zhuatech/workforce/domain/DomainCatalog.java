/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    public DomainCatalog(){
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交确认", List.of("草稿"), "待审批"));
actions.put("APPROVE", new WorkflowAction("APPROVE", "审批通过", List.of("待审批"), "已确认"));
actions.put("ARCHIVE", new WorkflowAction("ARCHIVE", "归档结算", List.of("已确认"), "已归档"));
    }
    public String systemName(){return "知华科技考勤排班与工时系统";}
    public String scene(){return "组织班次、员工考勤、假勤审批、加班调休和工时异常闭环";}
    public String initialStatus(){return "草稿";}
    public String partyLabel(){return "员工/班组";} public String amountLabel(){return "工时成本";}
    public String quantityLabel(){return "工时";} public String dueLabel(){return "处理日期";}
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("SHIFT","班次与排班","配置班次规则、轮班周期与排班计划"),
    new ModuleDefinition("ATTENDANCE","考勤记录","汇总打卡、迟到、缺卡和出勤结果"),
    new ModuleDefinition("LEAVE","请假与出差","提交假勤申请并完成主管审批"),
    new ModuleDefinition("OVERTIME","加班与调休","登记加班、核定工时并结转调休余额")
    );}
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    public record ModuleDefinition(String code,String name,String description){}
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
