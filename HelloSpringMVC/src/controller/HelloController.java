package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
public class HelloController implements Controller {

    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("index.jsp");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }
}*/
@Controller
public class HelloController{

    @RequestMapping("/hello")
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        //ModelAndView mav = new ModelAndView("index.jsp");
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }


    @RequestMapping("/test")
    public ModelAndView testRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("test");
        return mav;
    }

   /* @RequestMapping("/param")
    public ModelAndView getParam(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName);
        System.out.println(password);
        return null;
    }*/

    /*@RequestMapping("/param")
    public ModelAndView getParam(String userName,
                                 String password) {
        System.out.println(userName);
        System.out.println(password);
        return null;
    }*/

    @RequestMapping("/param")
    public ModelAndView getParam(User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        return null;
    }

 /*   //使用 Spring MVC 所提供的 ModelAndView 对象
    @RequestMapping("/value")
    public ModelAndView returnRequest(HttpServletRequest request, HttpServletResponse response) {
//        request.setAttribute("message","成功！");
//        return new ModelAndView("test2");
        ModelAndView mav = new ModelAndView("test2");
        mav.addObject("message","成功");
        return mav;
    }*/

    /*//使用 Model 对象
    @RequestMapping("/value")
    public String returnRequest(Model model) {
        model.addAttribute("message","成功！");
        return "test2";
    }*/

    //使用 @ModelAttribute 注解：
    @ModelAttribute
    public void model(Model model){
        model.addAttribute("message","注解成功");
    }

    @RequestMapping("/value")
    public String returnRequest(){
        return "test2";
    }


    //客户端跳转
    /*@RequestMapping("/jump")
    public ModelAndView jump() {
        ModelAndView mav = new ModelAndView("redirect:/hello");
        return mav;
    }*/
    // 另一种写法
    @RequestMapping("/jump")
    public String jump() {
        return "redirect: ./hello";
    }

}

