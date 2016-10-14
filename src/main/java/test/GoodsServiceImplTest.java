package test;


import com.quxin.freshfun.controller.goods.GoodsSortController;
import com.quxin.freshfun.service.goods.GoodsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodsServiceImplTest extends TestBase{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private GoodsService goodsService;

    private GoodsSortController goodsSortController;

    
    @Before
    public void setUp() throws Exception {
        goodsService = getContext().getBean("goodsService", GoodsService.class);
        goodsSortController = getContext().getBean("goodsSortController", GoodsSortController.class);
    }

    
    @After
    public void tearDown() throws Exception {
        getContext().close();
    }

    @Test
    public void queryGoodsById() {
        System.out.println(goodsSortController.queryGoodsById(56));
    }
    @Test
    public void queryGoodsSortList() {
        System.out.println(goodsSortController.queryGoodsSortList());
    }




}

