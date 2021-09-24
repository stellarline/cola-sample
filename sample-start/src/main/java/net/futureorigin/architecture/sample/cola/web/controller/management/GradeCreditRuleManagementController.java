package net.futureorigin.architecture.sample.cola.web.controller.management;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import net.futureorigin.architecture.sample.cola.api.GradeCreditRuleService;
import net.futureorigin.architecture.sample.cola.dto.clientobject.gradecreditrule.GradeCreditRuleListCO;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.gradecreditrule.GradeCreditRuleListQry;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * GradeCreditRuleManagementController
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@RestController
@RequestMapping("management/gradeCreditRule")
@Api(tags = "等级积分规则")
@ApiIgnore
public class GradeCreditRuleManagementController {

    private final GradeCreditRuleService gradeCreditRuleService;

    public GradeCreditRuleManagementController(GradeCreditRuleService gradeCreditRuleService) {
        this.gradeCreditRuleService = gradeCreditRuleService;
    }

    @PostMapping("add")
    @ApiOperation("添加等级积分规则")
    public Response add(@Valid @RequestBody GradeCreditRuleAddCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return gradeCreditRuleService.add(cmd);
    }

    @PostMapping("update")
    @ApiOperation("更新等级积分规则")
    public Response update() {
        return null;
    }

    @PostMapping("list")
    @ApiOperation("查询等级积分规则列表")
    public PageResponse<GradeCreditRuleListCO> list(@RequestBody GradeCreditRuleListQry cmd) {
        return gradeCreditRuleService.list(cmd);
    }

    @DeleteMapping("delete")
    @ApiOperation("删除等级积分规则")
    public Response delete() {
        return null;
    }

}
