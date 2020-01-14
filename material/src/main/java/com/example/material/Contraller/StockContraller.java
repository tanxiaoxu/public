package com.example.material.Contraller;

import com.example.material.Services.MaterialServices;
import com.example.material.Services.StockServices;
import com.example.material.Services.Stock_DetailServices;
import com.example.material.bean.MyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@RestController
@Controller
public class StockContraller {
    @Autowired
    MaterialServices MaterialServices;
    @Autowired
    StockServices stockServices;
    @Autowired
    Stock_DetailServices selectStock_DetailByWerks;

//    @ResponseBody
    @RequestMapping("/test")
    String test(){
        //String s=MaterialServices.test();
        return "selectmaterial";
    }
    @RequestMapping("/index")
    String index(){
        return "input";
    }
    //查询差异信息
    @RequestMapping("/selectDefByWerks")
    String selectByWerks(Model model, @RequestParam(value = "name", defaultValue = "") String name){
        Map<String, List<MyStock>> list =MaterialServices.selectDefByWerks(name);
        if (list.get("collect2").size() > 0 && list.get("collect3").size() > 0) {
            model.addAttribute("DDMyStock", list.get("collect2"));
            model.addAttribute("DMyStock", list.get("collect3"));
            return "test";
        } else if (list.get("collect2").size() > 0 && list.get("collect3").size() == 0) {
            model.addAttribute("ALLMyStock", list.get("list1"));
            return "kuwei";
        } else if (list.get("collect2").size() == 0 && list.get("collect3").size() > 0) {
            model.addAttribute("ALLMyStock", list.get("list2"));
            return "chuwei";
        }else{
            return "input";
        }
/*        if(list.size()>0){
            model.addAttribute("DDMyStock",list.get(0));
            model.addAttribute("DMyStock",list.get(1));
            return "test";
        }else{
            return "index";
        }*/
    }
    //库存查询重复值
    @RequestMapping("/selectWerks")
    String selectWerk(Model model,
                      @RequestParam(value = "name",
                              defaultValue = "springboot-thymeleaf") String name){
        List<MyStock> list =MaterialServices.selectWerk(name);
        if(list.size()>0){
            model.addAttribute("MyStock",list);
            return "stock";
        }else{
            return "index";
        }

    }

    //通过门店编码查询库存
    @RequestMapping("/selectStockByWerks")
    String selectStockByWerks(Model model,
                      @RequestParam(value = "name",
                              defaultValue = "") String name){
        List<MyStock> list =stockServices.selectStockByWerks(name);
        if(list.size()>0){
            model.addAttribute("MyStock",list);
            return "kuwei";
        }else{
            return "index";
        }

    }

    //通过门店编码查询储位
    @RequestMapping("/selectStockDetailByWerks")
    String selectStock_DetailByWerks(Model model,
                              @RequestParam(value = "name",
                                      defaultValue = "") String name){
        List<MyStock> list =selectStock_DetailByWerks.selectStock_DetailByWerks(name);
        if(list.size()>0){
            model.addAttribute("MyStock",list);
            return "chuwei";
        }else{
            return "chuwei";
        }

    }
    //通过门店编码查询储位
    @RequestMapping("/selectAllByWerks")
    String selectAllByWerks(Model model,
                                     @RequestParam(value = "name",
                                             defaultValue = "") String name) {
        Map<String, List<MyStock>> stringListMap = MaterialServices.selectAllByWerks(name);
        if (stringListMap.get("list1").size() > 0 && stringListMap.get("list2").size() > 0) {
            model.addAttribute("ALLMyStock", stringListMap.get("list1"));
            model.addAttribute("DALLMyStock", stringListMap.get("list2"));
            return "alldelite";
        } else if (stringListMap.get("list1").size() > 0 && stringListMap.get("list2").size() == 0) {
            model.addAttribute("ALLMyStock", stringListMap.get("list1"));
            return "kuwei";
        } else if (stringListMap.get("list1").size() == 0 && stringListMap.get("list2").size() > 0) {
            model.addAttribute("ALLMyStock", stringListMap.get("list2"));
            return "chuwei";
        }else{
            return "input";
        }
    }

    //通过门店编码查询总差异
    @RequestMapping("/selectSumDefByWerks")
    String selectSumDefByWerks(Model model,
                            @RequestParam(value = "name",
                                    defaultValue = "") String name) {
        Map<String, List<MyStock>> stringListMap = MaterialServices.selectSumDefByWerks(name);
        if (stringListMap.get("collect1").size() > 0 && stringListMap.get("collect3").size() > 0) {
            model.addAttribute("SUMMyStock", stringListMap.get("collect1"));
            model.addAttribute("SUMCMyStock", stringListMap.get("collect2"));
            model.addAttribute("SUMDMyStock", stringListMap.get("collect3"));
            return "sumdefdelite";
        } else if (stringListMap.get("collect1").size() > 0 && stringListMap.get("collect3").size() == 0) {
            model.addAttribute("ALLMyStock", stringListMap.get("collect1"));
            return "kuwei";
        } else if (stringListMap.get("collect1").size() == 0 && stringListMap.get("collect3").size() > 0) {
            model.addAttribute("ALLMyStock", stringListMap.get("collect3"));
            return "chuwei";
        }else{
            return "input";
        }
    }

/*    @RequestMapping("/test3")
    String selectByDWerks(Model model, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name){
        List s =
                MaterialServices.selectDetailByWerks(name);
        model.addAttribute("MyStock",s);
        System.out.println(s);
        return s.toString();
    }*/


/*    @RequestMapping("/selectAllWerk")
    String selectAllWerk(){
        List<MyStock> s=MaterialServices.selectAllWerk("");
        return "index";
    }*/
}
