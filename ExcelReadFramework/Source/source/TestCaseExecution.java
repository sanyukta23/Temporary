package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TestCaseExecution {
	
	
	static HashMap<String,HashMap<String,String>> suite=new HashMap();
	static HashMap<String,HashMap<String,String>> testCaseSteps=new HashMap();
	static ArrayList<String> header=new ArrayList<>();
	static ArrayList<String> values=new ArrayList<>();

	static Workbook workbook=null;
	
	public static void setUpData(Workbook wb,HashMap<String,HashMap<String,String>> testsuite){
		suite=testsuite;
		workbook=wb;
		readTestCasesAndRecordsteps();
	}
	
	public static void readTestCasesAndRecordsteps(){
		String sheetnName=null;
		HashMap<String,String> testCaseDetails=new HashMap<String, String>();
		
		java.util.Iterator<Entry<String, HashMap<String, String>>> it= suite.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            sheetnName=pair.getKey().toString();
            testCaseDetails=(HashMap<String,String>) pair.getValue();
            System.out.println(testCaseDetails);
            readTestCaseSteps(sheetnName);
        }
	}
        public static void readTestCaseSteps(String sheetname){
        	Cell cell=null;
            Sheet sheet  = workbook.getSheet(sheetname);
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
                	createHashMap(header, values);
                	values.clear();
                }
        }		
	}
        public static void createHashMap(ArrayList<String> header, ArrayList<String> values)
        {
            HashMap<String,String> hm2=new HashMap<>();
            hm2.clear();
            
            for (int i = 0; i < values.size(); i++) {
            	hm2.put(header.get(i), values.get(i));
            }
            
            testCaseSteps.put(values.get(0), hm2);
            
            java.util.Iterator<Entry<String, HashMap<String, String>>> it= testCaseSteps.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
        }
	
}
