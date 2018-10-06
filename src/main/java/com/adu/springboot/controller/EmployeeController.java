package com.adu.springboot.controller;

import com.adu.springboot.dao.DepartmentDao;
import com.adu.springboot.dao.EmployeeDao;
import com.adu.springboot.entities.Department;
import com.adu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 员工列表
     *
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {

        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "emp/list";
    }
    /**
     * 员工添加
     *
     * @return
     */
    @GetMapping("/emp")
    public String add(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /**
     * 员工添加
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 来到修改页面，查出当前员工，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }

    /**
     * 修改
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String editEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
