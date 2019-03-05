package get_excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class exel {
	public List<userNo> get() throws BiffException, Exception {
        Workbook workbook=Workbook.getWorkbook(new File("补考.xls")); 
        List<userNo> list = new ArrayList<userNo>();
        Sheet sheet = workbook.getSheet(0);
        //String username = "201635020426";
        //System.out.println(username.substring(4, 9));
        //获取数据
        //System.out.println("行："+sheet.getRows());
        userNo userNos;
        for(int i=0;i<sheet.getRows();i++){
        	Cell cell = sheet.getCell(0,i);
        	userNos = new userNo();
        	userNos.setUserno(cell.getContents());
        	list.add(userNos);
        }
        //关闭资源
        workbook.close();
        return list;
    }
}