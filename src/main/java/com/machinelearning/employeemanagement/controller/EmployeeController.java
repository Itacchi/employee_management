package com.machinelearning.employeemanagement.controller;

import com.machinelearning.employeemanagement.exception.InvalidFileFormatException;
import com.machinelearning.employeemanagement.utility.ExcelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.machinelearning.employeemanagement.model.Employee;
import com.machinelearning.employeemanagement.service.EmployeeService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/list")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        // save employee to database
        if (bindingResult.hasErrors()){
            return "showNewEmployeeForm";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/uploadExcelForm")
    public String uploadFileForm() {
        return "upload_excel";
    }


    @PostMapping("/uploadViaExcel")
    public String uploadViaExcel(@Valid @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws InvalidFileFormatException {
        if (ExcelUtility.hasExcelFormat(file))
            throw new InvalidFileFormatException("InValid File Format!!!");

        try {
            employeeService.saveAllEmployees(file);
            redirectAttributes.addFlashAttribute("message",
                    "You have successfully uploaded data of excel file '" + file.getOriginalFilename() + "' !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                    "Could not upload the file: " + file.getOriginalFilename() + "!");
        }
        return "redirect:/employee/list";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@Valid @PathVariable long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@Valid @PathVariable long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@Valid @PathVariable int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
