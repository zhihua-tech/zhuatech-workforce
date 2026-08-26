# ZhuaTech Workforce｜知华科技考勤排班与工时系统

        > 让班次、考勤、请假、加班与工时核算形成统一事实。

        [![Java 21](https://img.shields.io/badge/Java-21-315a70)](backend/pom.xml) [![Vue 3](https://img.shields.io/badge/Vue-3-42b883)](frontend/package.json) [![MySQL 8](https://img.shields.io/badge/MySQL-8-4479a1)](compose.yaml) [![个人非商用](https://img.shields.io/badge/license-personal%20non--commercial-b47b3a)](LICENSE)

        ZhuaTech Workforce 是知华科技（上海如静知华信息科技有限公司）发布的前后端分离企业应用社区源码版，面向组织班次、员工考勤、假勤审批、加班调休和工时异常闭环。官网：[https://www.zhuatech.cn/](https://www.zhuatech.cn/)。

        ## 企业版 V2.0

        在原有四大业务模块基础上，新增企业控制中心：支持组织与账期维度、幂等防重、经办/管理员职责分离、审批闭环、附件 SHA-256 元数据、办结凭证门槛、外部适配器回执、乐观锁和全程审计；并实现跨夜班次、宽限时间、缺卡/迟到/早退、月度应付工时汇总。详见[企业版能力说明](docs/ENTERPRISE.md)。

        ## 业务闭环

        ```text
        提交确认 → 审批通过 → 归档结算
        ```

        ## 主要模块

        | 模块 | 已实现能力 |
        | --- | --- |
        | 班次与排班 | 配置班次规则、轮班周期与排班计划 |
| 考勤记录 | 汇总打卡、迟到、缺卡和出勤结果 |
| 请假与出差 | 提交假勤申请并完成主管审批 |
| 加班与调休 | 登记加班、核定工时并结转调休余额 |
        | 运营总览 | 状态结构、模块负荷、金额指标、风险关注和最近业务 |
        | 领域计算 | 考勤与应付工时核算，提供可解释计算结果和处理建议 |
| 操作审计 | 创建、修改、删除、流程动作和设置变更均保留操作人及时间 |
        | 系统设置 | 核心业务参数持久化，管理员权限隔离 |

        管理端支持业务记录查询、新增、修改、删除、状态流转、越级操作拦截和审计追踪；响应式界面可在电脑和移动浏览器使用。演示数据全部为虚构数据。

        ## 技术架构

        - 后端：Java 21、Spring Boot 4、Spring Security、Spring Data JPA、MySQL 8
        - 前端：Vue 3、Vite，管理端与业务工作台响应式布局
        - 测试：H2 隔离数据库、MockMvc 接口与权限集成测试
        - 部署：Docker Compose、Nginx 反向代理、健康检查和环境变量
        - Java 工程包：`cn.zhuatech.workforce`

        ## 快速启动

        ```bash
        cp .env.example .env
        docker compose up --build
        ```

        浏览器打开 `http://localhost:8102`。演示账号：`admin / admin123`、`operator / operator123`。默认密码只能用于本地演示，上线前必须修改。

        本地开发：

        ```bash
        cd backend && mvn test
        cd ../frontend && npm install && npm run build
        ```

        更多资料参见 [API 文档](docs/API.md)、[架构说明](docs/ARCHITECTURE.md)、[安全政策](SECURITY.md)和[贡献指南](CONTRIBUTING.md)。

        ## 使用范围

        本工程仅允许个人非商业性的学习、研究和技术交流，**不得商用**。商用、二次销售、SaaS 服务、企业部署及深度定制须取得上海如静知华信息科技有限公司书面授权。

        商业授权、企业信息化、AI 转型、软件外包、项目实施和深度定制请访问[知华科技官网](https://www.zhuatech.cn/)，或扫描微信二维码咨询。

        <p align="center"><img src="docs/images/zhuatech-wechat-consulting.png" alt="知华科技微信咨询二维码一" width="230"><img src="docs/images/zhuatech-wechat-consulting-2.png" alt="知华科技微信咨询二维码二" width="230"></p>

        SEO 关键词：考勤系统、排班管理、工时系统、请假审批、加班调休、知华科技、上海软件开发、企业信息化、软件项目外包。
