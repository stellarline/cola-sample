package net.futureorigin.architecture.sample.cola.web.controller.member;

import com.alibaba.cola.dto.PageResponse;
import net.futureorigin.architecture.sample.cola.api.CreditRecordService;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordListQry;
import net.futureorigin.architecture.sample.cola.web.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreditController
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@RestController
@RequestMapping("member/credit")
@Api(tags = "用户积分")
public class CreditController {

    private final CreditRecordService creditRecordService;

    public CreditController(CreditRecordService creditRecordService) {
        this.creditRecordService = creditRecordService;
    }

    @GetMapping("listRecords")
    @ApiOperation("查询积分记录")
    public PageResponse listRecords() {
        CreditRecordListQry qry = new CreditRecordListQry();
        qry.setUserId(SecurityUtils.getUserContext().getLoggedInUserId());
        return creditRecordService.listAll(qry);
    }


}
