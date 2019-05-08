package com.lyp.service.impl;

import com.lyp.dao.UserDao;
import com.lyp.model.Page;
import com.lyp.model.Pages;
import com.lyp.model.User;
import com.lyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getuserByNum(String userNumber) {
        return userDao.getUserByNum(userNumber);
    }

    @Override
    public int register(User user) {
        return userDao.register(user);
    }

    @Override
    public User getUserBySubmit(String userNumber, String userPassword) {
        return userDao.getUserBySubmit(userNumber, userPassword);
    }

    @Override
    public void updateByNum(User user) {
         userDao.updateByNum(user);
    }

    @Override
    public void showUserByPage(HttpServletRequest request, Model model) throws Exception {
        String pageNow = request.getParameter("pageNow");
        System.out.println("-->pageNow:"+pageNow);
        Page page = null;

        List<User> userList = new ArrayList<User>();

        int totalCount = userDao.getUserCount();

       // System.out.println(totalCount);

        if(pageNow != null){
            page = new Page(totalCount,Integer.parseInt(pageNow));
            System.out.println(page);

            userList = userDao.selectUserByPage(page.getStartPos(),page.getPageSize());
            System.out.println("userList");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i));
            }
        }else {
            page = new Page(totalCount,1);
            System.out.println("userList");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i));
            }
            userList = userDao.selectUserByPage(page.getStartPos(),page.getPageSize());
        }
        model.addAttribute("userList",userList);
        model.addAttribute("page",page);
    }


    @Override
    public List<User> selectPageList(Pages pages) {
         List<User>list=userDao.selectPageList(pages);
        return list;
    }

    @Override
    public Integer selectPageCount(Pages pages) {
        return userDao.selectPageCount(pages);
    }

    @Override
    public void deleteByuserNumber(String userNumber) {
        userDao.deleteByuserNumber(userNumber);
    }

    @Override
    public void updateuserByNum(User user) {
        userDao.updateuserByNum(user);
    }

    @Override
    public void addHeaderImage(String descPath, String userNumber) {
        userDao.addHeaderImage(descPath,userNumber);
    }

}
