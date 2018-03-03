package source;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
    static HashMap<String, HashMap<String, String>> hm = new HashMap<>();
    static ArrayList<String> header = new ArrayList<>();
    static ArrayList<String> values = new ArrayList<>();
    
    public static void main(String[] args) throws BiffException, IOException 
    {
        Cell cell;
        File file = new File("E:\\temp\\Excel.xls");
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = Workbook.getWorkbook(fis);
        Sheet sheet  = wb.getSheet(0);
        int rows = sheet.getRows();
        int columns = sheet.getColumns();
        
        for(int r=0;r<rows;r++)
        {
            if(r==0)
            {
                for(int c=0;c<columns;c++)
                {
                    cell = sheet.getCell(c, 0);
                    header.add(((Cell) cell).getContents());
                }
            }
            if(r>0)
            {
                for(int c=0;c<columns;c++)
                {
                    cell = sheet.getCell(c, r);
                    values.add(cell.getContents());
                }
            }
            
            if(r>0)
            {
            	HashMap<String, HashMap<String,String>> localhm=new HashMap<>();
            	localhm= createHashMap(header, values);
                if(localhm.size()>0)
                	hm=localhm;
                	values.clear();
            }
        }
        	TestCaseExecution.setUpData(wb,hm);
    }
    
    public static HashMap<String,HashMap<String,String>> createHashMap(ArrayList<String> header, ArrayList<String> values)
    {
        HashMap<String,HashMap<String,String>> localhm=new HashMap<>();
        HashMap<String, String> hm2 = new HashMap<>();
        hm2.clear();
        for(int i=0; i<values.size(); i++)
        {
            hm2.put(header.get(i),values.get(i));
        }
        
        if(hm2.get("RunFlag")!=null && hm2.get("RunFlag").toString().trim().equalsIgnoreCase("y")){
        	localhm.put(values.get(0), hm2);
        }
        
        System.out.println(hm);
        
        java.util.Iterator<Entry<String, HashMap<String, String>>> it= hm.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
		return localhm;
    }

}
