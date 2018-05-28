package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.mapper.BusinessStatisticsMapper;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
import com.zhyxcs.xxzz.service.BusinessStatisticsService;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.OvertimeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessStatisticsServiceImpl implements BusinessStatisticsService {
    @Autowired
    private BusinessStatisticsMapper businessStatisticsMapper;
    @Autowired
    private OrgaMapper orgaMapper;

    @Override
    public int insert(WorkIndex workIndex, AuditStatus auditStatus, OvertimeStatus overtimeStatus, GroundsForReturn groundsForReturn) {
        if (workIndex == null) {
            return 0;
        }
        String transactionNum = workIndex.getStransactionnum();
        if (businessExist(transactionNum)) {
            return 0;
        }
        Orga orga = getOrgaByBankCode(workIndex.getSbankcode());
        BusinessStatistics businessStatistics = new BusinessStatistics();
        businessStatistics.setStransactionnum(transactionNum);
        businessStatistics.setSpbcode(workIndex.getSpbcbankcode());
        businessStatistics.setSbankareacode(orga.getSbankareacode());
        businessStatistics.setSbankcitycode(orga.getSbankcitycode());
        businessStatistics.setSbankkind(CommonUtils.getBankKindCode(transactionNum));
        businessStatistics.setSbanktypecode(CommonUtils.getBankTypeCode(transactionNum));
        businessStatistics.setSbusinesscategory(workIndex.getSbusinesscategory());
        businessStatistics.setSaccounttype(workIndex.getSaccounttype());
        if(auditStatus==AuditStatus.APPROVAL){
            businessStatistics.setSpass(auditStatus.getValue());
            businessStatistics.setSerrortype(null);
        }else{
            businessStatistics.setSpass(auditStatus.getValue());
            businessStatistics.setSerrortype(groundsForReturn.getSgrounds());
        }
        businessStatistics.setSovertime(overtimeStatus.getValue());
        businessStatistics.setShappentimes(null);
        return businessStatisticsMapper.insert(businessStatistics);
    }

    private Orga getOrgaByBankCode(String bankCode) {
        return orgaMapper.selectByPrimaryKey(bankCode);
    }

    private boolean businessExist(String transactionNum) {
        int count = businessStatisticsMapper.getCountByTransactionNum(transactionNum);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
