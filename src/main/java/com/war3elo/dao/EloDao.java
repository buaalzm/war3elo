package com.war3elo.dao;

import com.war3elo.domain.Elo;
import com.war3elo.mapper.EloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 12:35
 */
@Repository
public class EloDao {
    private Integer defaultElo = 500;
    @Autowired
    private EloMapper eloMapper;
    public List<Elo> getALLElos(){
        List<Elo> elos = eloMapper.selectAll();
        return elos;
    }

    public Integer getDefaultElo() {
        return defaultElo;
    }

    public Elo getEloByUsername(String username){
        Example example = new Example(Elo.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<Elo> elos = eloMapper.selectByExample(example);
        if (elos.isEmpty())
            return null;
        else
            return elos.get(0);
    }
    public int addElo(String username){
        Elo elo = new Elo();
        elo.setUsername(username);
        elo.setElo(defaultElo);
        return eloMapper.insert(elo);
    }
    public int updateEloByUsername(Elo elo){
        Example example = new Example(Elo.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",elo.getUsername());
        return eloMapper.updateByExample(elo,example);
    }
    public int deleteEloByUsername(String username){
        Example example = new Example(Elo.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        return eloMapper.deleteByExample(example);
    }
    public int deleteEloById(int id){
        return eloMapper.deleteByPrimaryKey(id);
    }
}
