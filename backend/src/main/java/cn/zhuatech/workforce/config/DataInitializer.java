/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.config;
import cn.zhuatech.workforce.model.*;
import cn.zhuatech.workforce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("workWeek","周一至周五"));
    settings.save(new SystemSetting("standardHours","8"));
    settings.save(new SystemSetting("overtimeRule","审批后生效"));
    settings.save(new SystemSetting("attendanceCycle","自然月"));
            records.save(new BusinessRecord("WFM-20260826-001","SHIFT","仓储中心九月轮班计划","仓储中心","排班主管","已确认",new BigDecimal("64000"),720,LocalDate.now().plusDays(6),"正常","早中晚三班已覆盖岗位需求"));
    records.save(new BusinessRecord("WFM-20260826-002","ATTENDANCE","八月考勤异常复核","上海总部","人事专员","待审批",new BigDecimal("9200"),18,LocalDate.now().plusDays(2),"关注","缺卡9条、跨夜班次4条待复核"));
    records.save(new BusinessRecord("WFM-20260826-003","LEAVE","研发中心集中休假计划","研发中心","部门主管","草稿",new BigDecimal("18000"),96,LocalDate.now().plusDays(12),"正常","项目交付窗口已避让"));
    records.save(new BusinessRecord("WFM-20260826-004","OVERTIME","实施团队八月加班结转","实施交付部","HRBP","已归档",new BigDecimal("36000"),184,LocalDate.now().plusDays(-2),"正常","已同步薪酬核算"));
    };}
}
