/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class DomainInsightApiTests {
    @Autowired MockMvc mvc;
    @Test void domainInsightProducesAuditableDecision() throws Exception {
        mvc.perform(post("/api/insights/workforce").with(httpBasic("operator","operator123"))
            .contentType(MediaType.APPLICATION_JSON).content("{\"scheduledMinutes\":480,\"actualMinutes\":450,\"lateMinutes\":15,\"overtimeMinutes\":60,\"leaveMinutes\":0}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.payableMinutes").value(510));
    }
}
