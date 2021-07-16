package com.framework.demo.transLogic;


import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/MyTrans")
public class Test {
    @RequestMapping("/myTest")
    public void myTest() {
        TransTemplate.getInstance().tansExecute(new CompeleteTask(),new TransContext());
    }

    public static void main(String[] args) {
        TransTemplate.getInstance().tansExecute(new CompeleteTask(),new TransContext());
    }
}
