package com.machinelearning.employeemanagement.utility;

import com.machinelearning.employeemanagement.model.Address;
import com.machinelearning.employeemanagement.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtility {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Employee> excelToEmployees(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("Employees List");
            Iterator<Row> rows = sheet.iterator();
            List<Employee> employees = new ArrayList<Employee>();

            for (int rowNumber = 1; rows.hasNext(); rowNumber++) {
                Row currentRow = rows.next();
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();
                Address permanentAddress = new Address();
                Address currentAddress = new Address();
                for (int cellIdx = 0; cellsInRow.hasNext() && cellIdx <= 14; cellIdx++) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {

                        //First Name
                        case 0:
                            employee.setFirstName(currentCell.getStringCellValue());
                            break;

                        //Last Name
                        case 1:
                            employee.setLastName(currentCell.getStringCellValue());
                            break;

                        //email
                        case 2:
                            employee.setEmail(currentCell.getStringCellValue());
                            break;

                        //current address country
                        case 3:
                            currentAddress.setCountry(currentCell.getStringCellValue());
                            break;

                        //current address first line
                        case 4:
                            currentAddress.setFirstLine(currentCell.getStringCellValue());
                            break;

                        //current address second line
                        case 5:
                            currentAddress.setSecondLine(currentCell.getStringCellValue());
                            break;

                        //current address city
                        case 6:
                            currentAddress.setCity(currentCell.getStringCellValue());
                            break;

                        //current address state
                        case 7:
                            currentAddress.setState(currentCell.getStringCellValue());
                            break;

                        //current address zipcode
                        case 8:
                            currentAddress.setZipcode(currentCell.getStringCellValue());
                            employee.setCurrentAddress(currentAddress);
                            currentAddress.setEmployee(employee);
                            break;


                        //permanent address country
                        case 9:
                            permanentAddress.setCountry(currentCell.getStringCellValue());
                            break;

                        //permanent address first line
                        case 10:
                            permanentAddress.setFirstLine(currentCell.getStringCellValue());
                            break;

                        //permanent address second line
                        case 11:
                            permanentAddress.setSecondLine(currentCell.getStringCellValue());
                            break;

                        //permanent address city
                        case 12:
                            permanentAddress.setCity(currentCell.getStringCellValue());
                            break;

                        //permanent address state
                        case 13:
                            permanentAddress.setState(currentCell.getStringCellValue());
                            break;

                        //permanent address zipcode
                        case 14:
                            permanentAddress.setZipcode(currentCell.getStringCellValue());
                            employee.setPermanentAddress(permanentAddress);
                            permanentAddress.setEmployee(employee);
                            break;

                        default:
                            break;
                    }
                }
                employees.add(employee);
            }
            workbook.close();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
