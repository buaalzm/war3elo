package com.war3elo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lzm
 * @create 2021-03-08 9:44
 */
@Data
@Table(name="t_combat")
@NoArgsConstructor
@AllArgsConstructor
public class Combat {
    @Id
    //主键回填
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String winner;
    private String loser;
    @Column(name="winner_race")
    private String winnerRace;
    @Column(name="loser_race")
    private String loserRace;
    private String map;
    @Column(name="create_time")
    private Date createTime;
}
