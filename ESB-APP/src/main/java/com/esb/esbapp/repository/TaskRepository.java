package com.esb.esbapp.repository;

import com.esb.esbapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Task entities.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // 查找按类型分类的任务
    List<Task> findByCategory(String category);

    // 查找是否存在指定任务名（用于校验）
    boolean existsByTitle(String title);

    // 获取启用中的任务
    List<Task> findByEnabledTrue();
}
