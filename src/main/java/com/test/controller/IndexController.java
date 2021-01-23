package com.test.controller;

import com.test.domain.Student;
import com.test.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private StudentService service;

    @RequestMapping("/add.do")
    public ModelAndView add(Student stu) {
        ModelAndView mv = new ModelAndView();
        int res = service.add(stu);
        String tips = "失败！！！";
        if (res == 1) {
            tips = "成功~~~";
        }
        mv.addObject("tips", tips);
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping("/showAll.do")
    @ResponseBody
    public Map<String, Object> showAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("num", service.showAll().size());
        map.put("stu", service.showAll());
        return map;
    }

    @RequestMapping(value = "/delete.do")
    @ResponseBody
//    public String delete(@RequestParam(value = "data") String[] data){
    //当前台发送的参数名称与接收参数名称相同时,可以省略 @RequestParam(value = "data")
    public String delete(String[] data) {
        int res = service.delete(data);
        String tips = "失败！！！";
        if (res != 0) {
            tips = "成功~~~";
        }
        return tips;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit(String id) {
        ModelAndView mv = new ModelAndView();
        Student student = service.edit(id);
        mv.addObject("stu", student);
        mv.setViewName("edit");
        return mv;
    }

    @RequestMapping("/update.do")
    public @ResponseBody
    String update(Student stu) {
        return String.valueOf(service.update(stu));
    }

    //    @RequestMapping("/test.do")
//    public @ResponseBody String test(int[] data) {
//        for (int j : data) {
//            System.out.println(j);
//        }
//        return "success";
//    }
    @RequestMapping("/test.do")
    @ResponseBody
    public Map<String, Object> one(String name, Integer age) {
        //前台什么都不输入的时候,后台接收为  name="" age=null 所以要注意动态sql的test条件!!!!!
        Map<String, Object> map = new HashMap<>();
        map.put("num", service.one(name, age).size());
        map.put("stu", service.one(name, age));
        return map;
    }

    @RequestMapping("/limit.do")
    @ResponseBody
    public Map<String, Object> two(@RequestParam(name = "pageNumber", defaultValue = "1" ,required = false) Integer pageNumber,
                                   @RequestParam(name = "pageSize", defaultValue = "5",required = false) Integer pageSize,
                                   String name, Integer age) {
        int total = 0;
        List<Student> list = null;
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        list = service.limit((pageNumber-1)*pageSize, pageSize, name, age);
        if ("".equals(name) && age == null) {
            total = service.showAll().size();
        }else {
//            total = service.one(name, age).size();
//            传递null会造成空指针异常,上面的required设置为false即可,但是必须用包装类接受,防止空指针异常报错终止程序
            total = service.limit(null, null, name, age).size();
        }
        int totalPage = (int)Math.ceil(total / (double)pageSize);
        if (pageNumber > totalPage) {
            pageNumber = totalPage;
            list = service.limit((pageNumber-1)*pageSize, pageSize, name, age);
        }
        int currentPage = pageNumber;

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("currentPage", currentPage);
        map.put("totalPage", totalPage);
        map.put("list", list);
        return map;

    }

}
