package com.esb.esbapp.repository;

import com.esb.esbapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCommunityId(String communityId);

    List<User> findTop10ByCommunityIdOrderByTotalPointsDesc(String communityId);
}