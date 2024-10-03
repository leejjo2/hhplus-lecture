package com.hhpluslecture.repository.orm;

import com.hhpluslecture.repository.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
    List<MemberEntity> findAll();
}
