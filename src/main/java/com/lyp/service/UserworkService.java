package com.lyp.service;

import com.lyp.model.Userwork;

public interface UserworkService {

    boolean addUserWork(Userwork userwork);
    boolean updateRepair(String userNumber,Integer userRepairAmount);
    boolean updateCancle(String userNumber,Integer userCancleAmount);
    Userwork selectRepairAndCancleByuserNumber(String userNumber);
}
