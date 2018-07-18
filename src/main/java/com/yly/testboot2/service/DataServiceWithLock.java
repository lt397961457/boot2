package com.yly.testboot2.service;

import com.yly.testboot2.utils.distributelock.LockWithZk.DistributedLock4ZK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataServiceWithLock {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public Integer getDataWithZK(Integer value){
        DistributedLock4ZK zkLock = new DistributedLock4ZK("192.168.31.120:2181","mylock");
        try {

            logger.info("进入到获取所的位置："+value);
            zkLock.lock();
            logger.info("获取到锁："+value);
            Thread.sleep(5000);
            Integer result = 100/value;
            logger.info("释放锁==："+value);
            return result;
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }finally {
            if(zkLock!=null){
                zkLock.unlock();
            }
        }
        return 0;
    }
}
