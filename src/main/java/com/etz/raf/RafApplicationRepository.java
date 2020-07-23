package com.etz.raf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RafApplicationRepository extends JpaRepository<RafApplicationModel, Long> {
}
