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
 * @create 2021-03-08 9:35
 */
@Data
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    //主键回填
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private String password;
}
