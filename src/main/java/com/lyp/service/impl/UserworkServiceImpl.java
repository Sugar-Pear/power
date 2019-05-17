package com.lyp.service.impl;

import com.lyp.dao.UserworkDao;
import com.lyp.model.Userwork;
import com.lyp.service.UserworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserworkServiceImpl implements UserworkService {

    @Autowired
    UserworkDao userworkDao;

    @Override
    public boolean addUserWork(Userwork userwork) {
        return userworkDao.addUserWork(userwork);
    }

    @Override
    public boolean updateRepair(String userNumber, Integer userRepairAmount) {
        return userworkDao.updateRepair(userNumber,userRepairAmount);
    }

    @Override
    public boolean updateCancle(String userNumber, Integer userCancleAmount) {
        return userworkDao.updateCancle(userNumber,userCancleAmount);
    }

    @Override
    public Userwork selectRepairAndCancleByuserNumber(String userNumber) {
        return userworkDao.selectRepairAndCancleByuserNumber(userNumber);
    }
}
