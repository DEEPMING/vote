package com.example.vote.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Author: Jerry
 * Time: 3. 26
 * Detail:
 */
@Component
public interface ParticipatorMapper {

    @Update("update selector set sum = sum+1 where id=#{id}")
    Integer toupiao(String id);

}
