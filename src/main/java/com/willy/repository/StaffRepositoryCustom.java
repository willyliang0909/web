package com.willy.repository;

import com.willy.model.Staff;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StaffRepositoryCustom {
    List<Staff> findByEmails(Set<String> email);

}
