package com.zhyxcs.xxzz.systemlog;

import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.service.SystemLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SytemLogPagerTest {
    private Logger logger = LoggerFactory.getLogger(SytemLogPagerTest.class);

    @Autowired
    private SystemLogService systemLogService;

    @Test
    public void testFindByPage() {
        System.out.println(systemLogService);
        List<SystemLog> systemLogs = systemLogService.querySystemLogByPageWithConditions(null,null,null,null,null,null,null,null);
        PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>();
        Assert.assertNotNull(systemLogs);
        logger.debug(pageInfo.toString());
    }

}
