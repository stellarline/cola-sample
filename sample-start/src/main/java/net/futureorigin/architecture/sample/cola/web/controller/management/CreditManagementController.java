package net.futureorigin.architecture.sample.cola.web.controller.management;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.futureorigin.architecture.sample.cola.api.CreditRecordService;
import net.futureorigin.architecture.sample.cola.api.CreditService;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditAddCmd;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordListQry;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * CreditManagementController
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@RestController
@RequestMapping("management/credit")
@Api(tags = "积分管理")
public class CreditManagementController {

    private final CreditService creditService;
    private final CreditRecordService creditRecordService;

    public CreditManagementController(CreditService creditService,
                                      CreditRecordService creditRecordService) {
        this.creditService = creditService;
        this.creditRecordService = creditRecordService;
    }

    @PostMapping("rechargeCredit")
    @ApiOperation("用户充值积分")
    public Response rechargeCredit(@Valid @RequestBody CreditAddCmd cmd) {
        SecurityUtils.bindUserContext(cmd);
        return creditService.add(cmd);
    }

    @PostMapping("listAll")
    @ApiOperation("查询用户积分记录")
    public PageResponse listAll(@RequestBody CreditRecordListQry cmd) {
        return creditRecordService.listAll(cmd);
    }

}
