package com.shubhada.productservice.inheritanceexamples.singleclass;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SCMentorRepository extends JpaRepository<Mentor,Long> {
     Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}
