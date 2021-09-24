package net.futureorigin.architecture.sample.cola.command.credit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.futureorigin.architecture.sample.cola.dto.clientobject.credit.CreditRecordListCO;
import net.futureorigin.architecture.sample.cola.dto.command.credit.CreditRecordListQry;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.dataobject.credit.CreditRecordDO;
import net.futureorigin.architecture.sample.cola.gatewayimpl.database.mapper.CreditRecordMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CreditRecordListQryCmdExe
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Component
public class CreditRecordListQryExe {

    private final CreditRecordMapper creditRecordMapper;

    public CreditRecordListQryExe(CreditRecordMapper creditRecordMapper) {
        this.creditRecordMapper = creditRecordMapper;
    }

    public PageResponse execute(CreditRecordListQry cmd) {
        PageHelper.startPage(cmd.getPageIndex(), cmd.getPageSize(), true);
        Long userId = cmd.getUserId();
        Page<CreditRecordDO> recordDOPage = null;
        if (Objects.isNull(userId)) {
            // find all user
            if (StrUtil.isNotBlank(cmd.getUserMobile())) {
                recordDOPage = creditRecordMapper.findByUserMobile(cmd.getUserMobile());
            } else {
                recordDOPage = creditRecordMapper.findAll();
            }
        } else {
            // find this user
            recordDOPage = creditRecordMapper.findByUserId(userId);
        }
        if (CollectionUtil.isEmpty(recordDOPage)) {
            return PageResponse.buildSuccess();
        }
        List<CreditRecordDO> creditRecordDOS = recordDOPage.getResult();
        List<CreditRecordListCO> recordListCOS = new ArrayList<>();
        creditRecordDOS.forEach(creditRecordDO -> {
            CreditRecordListCO listCO = new CreditRecordListCO();
            BeanUtil.copyProperties(creditRecordDO, listCO);
            recordListCOS.add(listCO);
        });
        return PageResponse.of(recordListCOS,
                Math.toIntExact(recordDOPage.getTotal()), recordDOPage.getPageSize(), recordDOPage.getPageNum());
    }
}
