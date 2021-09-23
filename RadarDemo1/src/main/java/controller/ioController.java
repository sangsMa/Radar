package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ioController {
    public static void read(String url) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(url);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getRichStringCellValue().getString();
                System.out.println(value);
            }
        }
    }
    public static List<student> getBean(String url) throws IOException {
        List<student> studentList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(url);
        XSSFSheet worksheet = workbook.getSheetAt(0);
        int lastRowNum = worksheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow workrow = worksheet.getRow(i);
            if (workrow != null) {
                List<String> list = new ArrayList<>();
                for (Cell cell : workrow) {
                    if (cell != null) {
                        cell.setCellType(1);
                        String value = cell.getStringCellValue();
                        if (value != null && !"".equals(value)) {
                            list.add(value);
                        }
                    }
                }
                if (list.size() != 0){
                    student student = new student(Integer.parseInt(list.get(0)),list.get(1),Integer.parseInt(list.get(2)),Integer.parseInt(list.get(3)),Integer.parseInt(list.get(4)),
                            Integer.parseInt(list.get(5)),Integer.parseInt(list.get(6)),Integer.parseInt(list.get(7)));
                    studentList.add(student);
                }
            }
        }
        return studentList;
    }
    public static void main(String[] args) throws IOException {
    }
}
