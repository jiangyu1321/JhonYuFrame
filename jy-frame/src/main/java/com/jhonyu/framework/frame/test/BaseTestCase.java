package com.jhonyu.framework.frame.test;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


/**
 * @Description: 单元测试基类
 * @className: BaseTestCase
 * @userName: jiangyu
 * @date: 2016年2月18日 上午11:21:26
 */

@ContextConfiguration(locations = {"/spring-mvc-context.xml", "/spring-mvc-dao.xml",
    "/spring-mvc-datasource.xml", "/spring-mvc-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests
{
    @Autowired
    private WebApplicationContext wac;

    /*
    private MockMvc mockMvc;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    @Before
    public void setup()
    {
        mockMvc = webAppContextSetup(this.wac).build();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }*/

    @Test
    public void test()
    {}
}
