package com.quxin.freshfun.test;

import com.quxin.freshfun.controller.goods.GoodsInfoController;
import com.quxin.freshfun.controller.withdraw.WithdrawController;
import com.quxin.freshfun.model.withdraw.QueryContion;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;

/**
 * Created by qucheng on 2016/9/28.
 */
public class WithDrawControllerTest{

    private WithdrawController withdrawController;

    private GoodsInfoController goodsInfoController;

    @Before
    public void setUp() throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:applicationContext.xml"	,"classpath:config/spring-mybatis.xml"});
        withdrawController = (WithdrawController) context.getBean("withdrawController");
        goodsInfoController = (GoodsInfoController) context.getBean("goodsInfoController");

    }

    @Test
    public void queryWithdrawList() throws ParseException {
        QueryContion qc = new QueryContion();
        qc.setState("2");
        qc.setCurrentPage("1");
        System.out.println(withdrawController.queryWithdrawList(null));
    }
    @Test
    public void toHandled() {
//        System.out.println(withdrawController.toHandled("10" , "25"));
    }

    @Test
    public void toReject() {
//        System.out.println(goodsInfoController.goodsList(null));
        System.out.println(withdrawController.toReject("20" ,"我不管111" ,"13"));
    }

}