/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.workforce.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class SchedulePublicationServiceTest {
    private final SchedulePublicationService service = new SchedulePublicationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void publishesCompliantSchedule() {
        var result = service.assess(new SchedulePublicationService.Request("SCH-100", true, true, true,
                true, true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(SchedulePublicationService.Decision.PUBLISH);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesCoverageAndApprovalGapsToRebalance() {
        var result = service.assess(new SchedulePublicationService.Request("SCH-101", false, true, true,
                false, false, true, false, false, false, true, true));
        assertThat(result.actions()).hasSize(6);
        assertThat(result.decision()).isEqualTo(SchedulePublicationService.Decision.REBALANCE);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksIllegalOrUncontrolledSchedule() {
        var result = service.assess(new SchedulePublicationService.Request("", false, false, false,
                false, false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(6);
        assertThat(result.decision()).isEqualTo(SchedulePublicationService.Decision.BLOCKED);
    }
}
