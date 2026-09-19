/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class DomainInsightService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        int payable=Math.max(0,req.actualMinutes()+req.overtimeMinutes()+req.leaveMinutes());
int variance=payable-req.scheduledMinutes();
result.put("payableMinutes",payable);result.put("varianceMinutes",variance);result.put("lateMinutes",req.lateMinutes());
result.put("decision",req.lateMinutes()>0||variance<0?"REVIEW":"CONFIRMED");
        return result;
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record InsightRequest(@PositiveOrZero int scheduledMinutes, @PositiveOrZero int actualMinutes, @PositiveOrZero int lateMinutes, @PositiveOrZero int overtimeMinutes, @PositiveOrZero int leaveMinutes){}
}
