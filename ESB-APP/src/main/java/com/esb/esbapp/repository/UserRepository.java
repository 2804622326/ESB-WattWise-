package com.esb.esbapp.repository;

import com.esb.esbapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 查找单个用户：用于登录、注册校验等
    Optional<User> findByEmail(String email);

    // 检查是否存在：注册时可用于重复校验
    boolean existsByEmail(String email);

    // 根据 meter number 查找
    Optional<User> findByMeterNumber(String meterNumber);

    // 按 communityId 获取某个社区的所有用户（用于排行榜等）
    List<User> findByCommunityId(Long communityId);

    // 获取某个社区积分最高的前 N 个用户（排行榜用）
    List<User> findTop10ByCommunityIdOrderByTotalPointsDesc(Long communityId);
}