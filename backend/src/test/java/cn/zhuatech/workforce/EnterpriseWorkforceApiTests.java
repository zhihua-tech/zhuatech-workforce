/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseWorkforceApiTests {@Autowired MockMvc mvc;
 @Test void overnightShiftAndGracePeriodAreSettled() throws Exception {mvc.perform(post("/api/enterprise/workforce/settle-month").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"employeeNo":"E001","period":"2026-08","graceMinutes":5,"days":[{"workDate":"2026-08-01","shiftStart":"2026-08-01T22:00:00","shiftEnd":"2026-08-02T06:00:00","clockIn":"2026-08-01T22:04:00","clockOut":"2026-08-02T06:01:00","breakMinutes":60,"leaveMinutes":0,"approvedOvertimeMinutes":30}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.scheduledMinutes").value(420)).andExpect(jsonPath("$.data.overtimeMinutes").value(30)).andExpect(jsonPath("$.data.decision").value("CONFIRMED"));}
 @Test void invalidShiftIsRejected() throws Exception {mvc.perform(post("/api/enterprise/workforce/settle-month").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"employeeNo":"E001","period":"2026-08","graceMinutes":0,"days":[{"workDate":"2026-08-01","shiftStart":"2026-08-01T22:00:00","shiftEnd":"2026-08-01T06:00:00","breakMinutes":0,"leaveMinutes":0,"approvedOvertimeMinutes":0}]}
 """)).andExpect(status().isBadRequest());}
}
