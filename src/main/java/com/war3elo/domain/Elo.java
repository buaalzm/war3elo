package com.war3elo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lzm
 * @create 2021-03-08 9:51
 */
@Data
@Table(name="t_elo")
@NoArgsConstructor
@AllArgsConstructor
public class Elo {
    @Id
    //主键回填
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private Integer elo;
}
