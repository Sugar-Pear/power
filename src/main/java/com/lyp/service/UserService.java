package com.lyp.service;

import com.lyp.model.Pages;
import com.lyp.model.User;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User getUserBySubmit(String userNumber, String userPassword);

    User getuserByNum(String userNumber);

    int register(User user);

    void updateByNum(User user);

    void showUserByPage(HttpServletRequest request, Model model) throws Exception;

    //分页数据
    List<User>selectPageList(Pages page);
    //分页数据总数
    Integer selectPageCount(Pages page);

    void deleteByuserNumber(String userNumber);

    void updateuserByNum(User user);

    void addHeaderImage(String descPath, String userNumber);

    boolean addUser(User user);
}
