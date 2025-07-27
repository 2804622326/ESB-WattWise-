package com.esb.esbapp.repository;

import com.esb.esbapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 获取排行榜前10用户
    List<User> findTop10ByOrderByDailyPointsDesc();
    List<User> findTop10ByOrderByWeeklyPointsDesc();
    List<User> findTop10ByOrderByTotalPointsDesc();
}