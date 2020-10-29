package com.xiao.xbcp.service.analysis;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class AnalyzerPropertiesService {
    public AnalyzerProperties getAnalyzerProperties(String analyzerId){
         AnalyzerProperties analyzerProperties = new AnalyzerProperties();
         /*//analyzerProperties.setDataScript("select c.contract_status contractStatus,c.paid_status contractPaidStatus,cp.status contractProductStatus from contract c join contract_product cp on c.id = cp.contract_id");
        analyzerProperties.setDataScript("select c.contract_status contractStatus,c.paid_status contractPaidStatus from contract c ");
        analyzerProperties.setRows(10000);
         analyzerProperties.setType("mysql");
        List<Classify> classifys = new ArrayList<>();
        Classify classify = new Classify("contractStatus","NORMAL");
        classifys.add(classify);
        classify = new Classify("contractStatus","RETURNS");
        classifys.add(classify);
        classify = new Classify("contractStatus","FINISHED");
        classifys.add(classify);
        classify = new Classify("contractStatus","UNVALID");
        classifys.add(classify);
        classify = new Classify("contractStatus","UNVALID");
        classifys.add(classify);
        *//*classify = new Classify("contractProductStatus","NORMAL");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","STARTED");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","FROZEN");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","REFUNDED");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","ENDED");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","CLOSE_PRODUCT");
        classifys.add(classify);
        classify = new Classify("contractProductStatus","UNVALID");*//*
        //classifys.add(classify);
        classify = new Classify("contractPaidStatus","UNPAY");
        classifys.add(classify);
        classify = new Classify("contractPaidStatus","PAYING");
        classifys.add(classify);
        classify = new Classify("contractPaidStatus","PAID");
        classifys.add(classify);
        classify = new Classify("contractPaidStatus","CANCELED");
        classifys.add(classify);
        classify = new Classify("contractPaidStatus","PAIDMORE");
        classifys.add(classify);
        analyzerProperties.setClassifys(classifys);*/
         return analyzerProperties;
    }
}
