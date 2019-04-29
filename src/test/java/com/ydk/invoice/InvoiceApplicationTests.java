package com.ydk.invoice;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydk.invoice.model.BillInfo;
import com.ydk.invoice.repository.BillRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceApplicationTests {
    @Autowired
    private BillRepository billRepository;

    @Test
    public void easypoi() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "static/temp.xls");
        ObjectMapper mapper = new ObjectMapper();
        BillInfo bill = billRepository.findById(1).get();
        Map<String, Object> map = mapper.convertValue(bill, Map.class);
        System.out.println("map = " + map);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/map.xls");
        workbook.write(fos);
        fos.close();
    }

}
