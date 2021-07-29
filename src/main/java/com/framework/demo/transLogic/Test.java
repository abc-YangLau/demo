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
        TransContext transContext= new TransContext() ;
        transContext.setAmtTr("100");
        transContext.setDateReq("20210719");
        TransTemplate.getInstance().tansExecute(new CompeleteTask(),transContext);
    }


}
