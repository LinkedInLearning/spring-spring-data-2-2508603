package com.example.university.domain;

import org.springframework.beans.factory.annotation.Value;
// invoke from /departments/{id}?projection=showChair
//@Projection(name="showChair", types = {Department.class})
public interface ShowChair {
    String getName();

    @Value("#{target.chair.member.firstName} #{target.chair.member.lastName}")
    String getChairName();

}
