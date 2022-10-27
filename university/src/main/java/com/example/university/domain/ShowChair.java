package com.example.university.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="showChair", types = {Department.class})
public interface ShowChair {
    String getName();

    @Value("#{target.chair.member.firstName} #{target.chair.member.lastName}")
    String getChairName();

}
