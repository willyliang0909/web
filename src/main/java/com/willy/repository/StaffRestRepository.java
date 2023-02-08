package com.willy.repository;

import com.willy.model.Staff;
import com.willy.model.StaffProjection;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface StaffRestRepository extends JpaRepository<Staff, Integer>, StaffRepositoryCustom {


    //@Query(value = "select s from Staff s")
    @Query(value = "select * from staffinfo", nativeQuery = true)
    public List<Staff> all();

    List<Staff> findByCreateDateAfter(LocalDate date);
    @Query(value = "select s from Staff s where s.createDate >= :date")
    //@Query(value = "select * from staffinfo s where s.create_date > :date", nativeQuery = true)
    public List<Staff> findByDate(LocalDate date);

    //@Query(value = "select s from Staff s where s.id in :ids")
    @Query(value = "select * from staffinfo s where s.id in :ids", nativeQuery = true)
    public List<Staff> list(List<Integer> ids);


    @Query(value = "select s from Staff s where s.id in :ids order by id")
    public List<Staff> sortList(List<Integer> ids, Sort sort);

    //@Query(value = "select s from Staff s where s.id in :ids")
    @Query(
            value = "select * from staffinfo s where s.id in :ids",
            countQuery = "SELECT count(*) FROM staffinfo s where s.id in :ids",
            nativeQuery = true)
    public List<Staff> pageList(List<Integer> ids, Pageable pageable);

    @Modifying
    //@Query(value = "update Staff set email = :email")
    @Query(value = "update staffinfo set email = :email", nativeQuery = true)
    public int updateEmail(String email);

    @Modifying
    @Query(value = "insert into staffInfo (id, email, password) values (:id, :email, :password)", nativeQuery = true)
    public void insert(Integer id, String email, String password);
}
