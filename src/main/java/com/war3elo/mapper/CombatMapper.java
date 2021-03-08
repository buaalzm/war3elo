package com.war3elo.mapper;

import com.war3elo.domain.Combat;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lzm
 * @create 2021-03-08 9:59
 */
@Repository
public interface CombatMapper  extends Mapper<Combat> {
}
