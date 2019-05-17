package com.lyp.dao;

import com.lyp.model.Userwork;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserworkDao {

    //添加员工工作记录
    @Insert("insert into userwork(userNumber,userRepairAmount,userCancleAmount) values(#{userNumber},#{userRepairAmount},#{userCancleAmount})")
    boolean addUserWork(Userwork userwork);

    //更新员维修次数
    @Update("update userwork set userRepairAmount=#{userRepairAmount} where userNumber=#{userNumber}")
    boolean updateRepair(String userNumber,Integer userRepairAmount);

    //更新员工取消次数
    @Update("update userwork set userCancleAmount=#{userCancleAmount} where userNumber=#{userNumber}")
    boolean updateCancle(String userNumber,Integer userCancleAmount);

    //筛选员工维修、取消次数
    @Select("select * from userwork where userNumber=#{userNumber}")
    Userwork selectRepairAndCancleByuserNumber(String userNumber);

}
