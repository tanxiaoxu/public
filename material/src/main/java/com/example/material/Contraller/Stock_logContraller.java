package com.example.material.Contraller;

import com.example.material.Services.Stock_logService;
import com.example.material.bean.wm_stock_log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
public class Stock_logContraller {
    @Autowired
    Stock_logService stock_logService;
    @RequestMapping("/selectLog")
    public String selectLog(Model model, @RequestParam(value = "werk") String werks,
                            @RequestParam(value = "material") String material,
                            @RequestParam(value = "batchNum") String batchNum,
                            @RequestParam(value = "shipmentNo") String shipmentNo
    ){
        Map<String, List<wm_stock_log>> stringListMap = stock_logService.SelectStock_Log(werks,material,batchNum,shipmentNo);
        //Map<String, Object> stringListMap = stock_logService.SelectStock_sum_Log(werks, material, batchNum, shipmentNo);
        stringListMap = stringListMap;
        if(stringListMap.isEmpty()){
            return "error";
        }else{
            model.addAttribute("logsList",stringListMap.get("logsList"));
            model.addAttribute("DlogsList",stringListMap.get("DlogsList"));
        }
        return "stocklog";
    }
}
