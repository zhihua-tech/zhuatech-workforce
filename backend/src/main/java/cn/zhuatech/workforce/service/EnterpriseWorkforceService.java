/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.HttpStatus;import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;import java.time.*;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseWorkforceService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public MonthlyResult settle(@Valid MonthlyRequest req){
   int scheduled=0,worked=0,late=0,early=0,overtime=0,leave=0;List<DayResult> days=new ArrayList<>();
   for(var day:req.days()){
     if(day.shiftEnd().isBefore(day.shiftStart())||day.shiftEnd().equals(day.shiftStart()))throw bad("班次结束必须晚于开始，跨夜班请传入次日日期时间");
     int planned=(int)Duration.between(day.shiftStart(),day.shiftEnd()).toMinutes()-day.breakMinutes();
     int actual=day.clockIn()==null||day.clockOut()==null?0:(int)Duration.between(day.clockIn(),day.clockOut()).toMinutes()-day.breakMinutes();
     if(actual<0)throw bad("打卡时间或休息时长不合法");
     int dayLate=day.clockIn()==null?planned:(int)Math.max(0,Duration.between(day.shiftStart().plusMinutes(req.graceMinutes()),day.clockIn()).toMinutes());
     int dayEarly=day.clockOut()==null?planned:(int)Math.max(0,Duration.between(day.clockOut(),day.shiftEnd().minusMinutes(req.graceMinutes())).toMinutes());
     String decision=day.clockIn()==null||day.clockOut()==null?"MISSING_PUNCH":dayLate>0||dayEarly>0?"EXCEPTION":"CONFIRMED";
     scheduled+=planned;worked+=actual;late+=dayLate;early+=dayEarly;overtime+=day.approvedOvertimeMinutes();leave+=day.leaveMinutes();
     days.add(new DayResult(day.workDate(),planned,actual,dayLate,dayEarly,decision));
   }
   int payable=Math.max(0,worked+overtime+leave);return new MonthlyResult(req.employeeNo(),req.period(),scheduled,worked,leave,overtime,late,early,payable,days,
     days.stream().anyMatch(d->!"CONFIRMED".equals(d.decision()))?"REVIEW_REQUIRED":"CONFIRMED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private ResponseStatusException bad(String m){return new ResponseStatusException(HttpStatus.BAD_REQUEST,m);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record MonthlyRequest(@NotBlank String employeeNo,@NotBlank @Pattern(regexp="\\d{4}-(0[1-9]|1[0-2])") String period,
   @PositiveOrZero int graceMinutes,@NotEmpty List<@Valid ShiftDay> days){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ShiftDay(@NotNull LocalDate workDate,@NotNull LocalDateTime shiftStart,@NotNull LocalDateTime shiftEnd,
   LocalDateTime clockIn,LocalDateTime clockOut,@PositiveOrZero int breakMinutes,@PositiveOrZero int leaveMinutes,@PositiveOrZero int approvedOvertimeMinutes){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record DayResult(LocalDate workDate,int scheduledMinutes,int actualMinutes,int lateMinutes,int earlyLeaveMinutes,String decision){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record MonthlyResult(String employeeNo,String period,int scheduledMinutes,int workedMinutes,int leaveMinutes,int overtimeMinutes,
   int lateMinutes,int earlyLeaveMinutes,int payableMinutes,List<DayResult> days,String decision){}
}
