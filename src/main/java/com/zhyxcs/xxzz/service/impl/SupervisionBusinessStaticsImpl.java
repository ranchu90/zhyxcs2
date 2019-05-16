package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
import com.zhyxcs.xxzz.mapper.SupervisionBusinessStatisticsMapper;
import com.zhyxcs.xxzz.service.SupervisionBusinessStaticsService;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.OvertimeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisionBusinessStaticsImpl implements SupervisionBusinessStaticsService {
    @Autowired
    SupervisionBusinessStatisticsMapper supervisionBusinessStatisticsMapper;

    @Autowired
    OrgaMapper orgaMapper;

    @Override
    public int insert(Supervision supervision, AuditStatus auditStatus, OvertimeStatus overtimeStatus, GroundsForReturn groundsForReturn) {
        if (supervision == null) {
            return 0;
        }
        String transactionNum = supervision.getStransactionnum();
        if (businessExist(transactionNum)) {
            return 0;
        }
        Orga orga = getOrgaByBankCode(supervision.getSbankcode());
        SupervisionBusinessStatistics supervisionBusinessStatistics = new SupervisionBusinessStatistics();
        supervisionBusinessStatistics.setStransactionnum(transactionNum);
        supervisionBusinessStatistics.setSpbcode(supervision.getSpbcbankcode());
        supervisionBusinessStatistics.setSbankareacode(orga.getSbankareacode());
        supervisionBusinessStatistics.setSbankcitycode(orga.getSbankcitycode());
        supervisionBusinessStatistics.setSbankkind(CommonUtils.getBankKindCode(transactionNum));
        supervisionBusinessStatistics.setSbanktypecode(CommonUtils.getBankTypeCode(transactionNum));
        supervisionBusinessStatistics.setSbusinesscategory(supervision.getSbusinesscategory());
        supervisionBusinessStatistics.setSaccounttype(supervision.getSaccounttype());
        if (auditStatus == AuditStatus.APPROVAL) {
            supervisionBusinessStatistics.setSpass(auditStatus.getValue());
            supervisionBusinessStatistics.setSerrortype(null);
        } else {
            supervisionBusinessStatistics.setSpass(auditStatus.getValue());
            supervisionBusinessStatistics.setSerrortype(groundsForReturn.getSgrounds());
        }
        supervisionBusinessStatistics.setSovertime(overtimeStatus.getValue());
        supervisionBusinessStatistics.setShappentimes(CommonUtils.newDate());
        return supervisionBusinessStatisticsMapper.insert(supervisionBusinessStatistics);
    }

    private Orga getOrgaByBankCode(String bankCode) {
        return orgaMapper.selectByPrimaryKey(bankCode);
    }

    private boolean businessExist(String transactionNum) {
        int count = supervisionBusinessStatisticsMapper.getCountByTransactionNum(transactionNum);
        return count > 0;
    }
}
