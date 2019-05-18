package com.lyp.controller;

import com.lyp.model.Company;
import com.lyp.service.CompanyService;
import com.lyp.utils.Pages;
import com.lyp.utils.ResultMap;
import com.lyp.model.User;
import com.lyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/logins")
    public String loginView(HttpServletRequest request) {

        request.getSession().setAttribute("login",1);
        return "login";
    }


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView login(ModelAndView modelAndView, HttpSession session, @RequestParam(value = "userNumber") String userNumber,
//                              @RequestParam(value = "userPassword") String userPassword) {
//
//        //登陆成功
//        if (userService.getUserBySubmit(userNumber, userPassword) != null) {
//            User user = userService.getuserByNum(userNumber);
//            session.setAttribute("user", user);
//            //modelAndView.addObject("a",user.getUserName());
//            modelAndView.setViewName("index");
//        } else
//            modelAndView.setViewName("login");
//        return modelAndView;
//    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(@RequestBody HashMap<String,Object> map,HttpSession session){
        String userNumber = (String) map.get("number");
        String userPassword = (String) map.get("pass");
        System.out.println("登陆临沂临沂临沂林忆莲林忆莲原来"+userNumber);



        if (userService.getUserBySubmit(userNumber,userPassword) !=null){
            User user = userService.getuserByNum(userNumber);
            session.setAttribute("user",user);
            return "1";
        }
        return "2";
    }

    @RequestMapping(value = "/toregister")
    public String toregister(HttpServletRequest request) {
        List<Company> list = companyService.selectAllCompany();
        request.setAttribute("companylist",list);
        for (int i=0;i<list.size();i++){
            System.out.println("公司"+list.get(i).toString());
        }
        request.getSession().setAttribute("register",1);
        return "register";
    }


    //注册实现
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(@RequestBody HashMap<String,Object> map){

        String userNumber = (String) map.get("number");
        System.out.println("注册妈妈妈妈"+userNumber);
        String userName = (String) map.get("name");
        String companyNumber = (String) map.get("companyid");
        String userPassword = (String) map.get("psw");
        String companyCheckNumber = (String) map.get("checlnum");
        Integer id = Integer.parseInt(companyNumber);
        String checkNum = companyService.selectCheckNumById(id);
        String img = "user01.png";

        User user = new User();
        user.setUserNumber(userNumber);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setCompanyNumber(id);
        user.setUserImg(img);

        if(companyCheckNumber == checkNum){

            userService.register(user);
            return "1";
        }
        return "2";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("userName");
        return "login";
    }

    @RequestMapping(value = "/touserupdate")
    public String touserupdate(Map map, HttpSession session, HttpServletRequest request) {
        String basepath = request.getContextPath();
        System.out.println("to usermessage");
        map.put("basepath", basepath);
        return "usermessage";
    }

    @RequestMapping(value = "/userupdate", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView modelAndView,
                               @RequestParam(value = "userNumber") String userNumber,
                               @RequestParam(value = "userPassword") String userPassword,
                               @RequestParam(value = "userType") String userType,
                               @RequestParam(value = "userAge") int userAge,
                               @RequestParam(value = "userBirthday") String userBirthday,
                               @RequestParam(value = "userPhone") String userPhone,
                               @RequestParam(value = "province") String province,
                               @RequestParam(value = "city") String city,
                               @RequestParam(value = "area") String area, HttpServletRequest request) {
        User user = new User();
        user.setUserProvince(province);
        user.setUserCity(city);
        user.setUserArea(area);
        user.setUserNumber(userNumber);
        user.setUserPassword(userPassword);
        user.setUserType(userType);
        user.setUserAge(userAge);
        user.setUserBirthday(userBirthday);
        user.setUserPhone(userPhone);
        //user.setUserAddress(userAddress);\
        System.out.println("user==" + user);
        userService.updateByNum(user);  //error
        User user1 = userService.getuserByNum(userNumber);
        request.getSession().setAttribute("user", user1);
        modelAndView.setViewName("usermessage");
        return modelAndView;
    }


    @RequestMapping(value = "/uploadHeadImage", method = RequestMethod.POST )
    @ResponseBody
    public int uploadHeader(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) throws IOException {
        if (file != null){
            //获取文件名
            String fileName = file.getOriginalFilename();
            //截获扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            List list = new ArrayList();
            list.add(".jpg");
            list.add(".png");
            list.add(".jpeg");
            list.add(".gif");
            if(list.contains(extName.toLowerCase())){
                User user =(User) request.getSession().getAttribute("user");
                String realPath =  "D:\\studysoft\\ideaproject\\power\\src\\main\\webapp\\images\\users";
                String photoFileName = user.getUserNumber()+extName;
                String descPath = realPath + "\\" + photoFileName;
                System.out.println(descPath);
                System.out.println(user.getUserNumber());
                userService.addHeaderImage(photoFileName,user.getUserNumber());
                file.transferTo(new File(realPath,photoFileName));
                User user1 = userService.getuserByNum(user.getUserNumber());;
                request.getSession().setAttribute("user",user1);
                return 1;//成功
            }else {
                return -1;//文件类型不正确
            }
        }else {
            return 0;//上传文件为空
        }
    }


    @RequestMapping("/userlist")
    public ModelAndView tolist(ModelAndView modelAndView) {
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultMap<List<User>> backContent(Pages page, @RequestParam("limit") int limit) {
        page.setRows(limit);
        System.out.println("page:" + page.toString());

        List<User> contentList = userService.selectPageList(page);
        int totals = userService.selectPageCount(page);
        page.setTotalRecord(totals);
        ResultMap<List<User>> userResultMap = new ResultMap<List<User>>(0, "", totals, contentList);
        return userResultMap;
    }

    /**
     * 用户信息编辑与删除
     */
    @ResponseBody
    @RequestMapping(value = "/modify",method=RequestMethod.POST)
    public  String modiSens(@RequestBody HashMap<String,String> map1) {
        String userNumber = map1.get("number");
        String userName = map1.get("name");
        String userPhone = map1.get("phone");
        String userType = map1.get("utype");
        String userBirthday = map1.get("birth");
        User user = new User();
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setUserType(userType);
        user.setUserNumber(userNumber);
        user.setUserBirthday(userBirthday);
        System.out.println("------->>>>user====================="+user.toString());
        userService.updateuserByNum(user);
        return "200";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean delectUser(String userNumber) {
        System.out.println("删除");
        System.out.println("userNumber====" + userNumber);
        //删除
        userService.deleteByuserNumber(userNumber);
        return true;
    }

    //添加用户
    @ResponseBody
    @RequestMapping(value = "/adduser1",method = RequestMethod.POST)
    public String addUser(@RequestBody Map<String,Object> map){

        String userPassword = "12";

        String userImg = "user01.png";

        User user = new User();

        String userNumber = (String) map.get("number");

        String userName = (String) map.get("name");

        String userBirthday = (String) map.get("birth");

        String userType = (String) map.get("type");

        String userProvince = (String) map.get("province");

        String userCity = (String) map.get("city");

        String userArea = (String) map.get("area");

        String userPhone = (String) map.get("phone");

        user.setUserNumber(userNumber);

        user.setUserName(userName);

        user.setUserBirthday(userBirthday);

        user.setUserType(userType);

        user.setUserProvince(userProvince);

        user.setUserCity(userCity);

        user.setUserArea(userArea);

        user.setUserImg(userImg);

        user.setUserPhone(userPhone);

        user.setUserPassword(userPassword);

        System.out.println("hdakshdakhsdkahUUUUSSSSEEERRR"+user);

        if (userService.getuserByNum(userNumber)!=null){

            System.out.println("hdajkshdkjashdjska"+userService.getuserByNum(userNumber));
               return "2";
           }else if(userService.addUser(user)){

            return "1";
        }else {
            return "0";
        }
    }
}
