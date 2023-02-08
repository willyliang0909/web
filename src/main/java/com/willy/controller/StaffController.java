package com.willy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willy.model.Staff;
import com.willy.repository.StaffRepository;
import com.willy.repository.StaffRestRepository;
import com.willy.repository.WillyTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Api(tags="員工管理")
@RestController
@RequestMapping(value = "/api/staffs")
public class StaffController {

    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    WillyTest willyTest;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StaffRestRepository staffRestRepository;

    @ApiOperation("員工列表")
    @GetMapping
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @GetMapping(value = "/willy")
    public List<Map<String, Object>> findTest() {
        return willyTest.findOther();
    }

    @GetMapping(value = "/idpass")
    public List<Staff> getIdPass() {
        var a = staffRestRepository.all();
        return a;
    }

    @GetMapping(value = "/bydate")
    public List<Staff> findByDate(@RequestParam @DateTimeFormat(pattern = "yyyyMMdd")  LocalDate time) {
        return staffRestRepository.findByCreateDateAfter(time);
    }

    @PostMapping(value = "/byemails")
    public List<Staff> findByEmails(@RequestBody Set<String> emails) {
        System.out.println(emails);
        return staffRestRepository.findByEmails(emails);
    }

    @GetMapping(value = "/ids")
    public List<Staff> findByIds(@RequestParam List<Integer> ids) {
        return staffRestRepository.list(ids);
    }

    @GetMapping(value = "/idsort")
    public List<Staff> sortIds(@RequestParam List<Integer> ids, Sort sort) {
        return staffRestRepository.sortList(ids, sort);
    }

    @GetMapping(value = "/idpage")
    public List<Staff> pageIds(@RequestParam List<Integer> ids, Pageable pageable) {
        logger.info(pageable);
        return staffRestRepository.pageList(ids, pageable);
    }

    @GetMapping(value = "/{id}")
    public Optional<Staff> getStaff(@PathVariable Integer id) {
        return staffRepository.findById(id);
    }

    @Transactional
    @PatchMapping(value = "/email/{email}")
    public int updateEamil(@PathVariable String email) {
        return staffRestRepository.updateEmail(email);
    }

    @Transactional
    @PostMapping(value = "/insert")
    public void insert(@RequestBody Staff staff) {
        //staffRestRepository.insert(staff.getId(), staff.getEmail(), staff.getPassword());
    }

    @ApiOperation(value = "新增員工", notes = "新增員工 note")
    @PostMapping()
    public Staff addStaff(@RequestBody Staff s) {
        return staffRepository.save(s);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStaff(@PathVariable Integer id) {
        staffRepository.deleteById(id);
    }

//    @PutMapping(value = "/{id}")
//    public Staff updateStaff(@RequestBody Staff staff, @PathVariable Integer id) {
//        Optional<Staff> staffOptional = staffRepository.findById(id);
//        if (staffOptional.isPresent()) {
//            staff.setId(id);
//            return staffRepository.save(staff);
//        } else {
//            return null;
//        }
//    }

    @PutMapping(value = "/{id}")
    public Staff updateStaff(@RequestBody Staff staff, @PathVariable Integer id) {
        staff.setId(id);
        return staffRepository.save(staff);
    }

    @PatchMapping(value = "/{id}")
    public Staff patchStaff(@RequestBody String map, @PathVariable Integer id) throws JsonProcessingException {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            Staff updatedUser = objectMapper.readerForUpdating(staff).readValue(map);
            return staffRepository.save(updatedUser);
        } else {
            return null;
        }
    }
}
