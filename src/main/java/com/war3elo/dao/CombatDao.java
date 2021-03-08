package com.war3elo.dao;

import com.war3elo.domain.Combat;
import com.war3elo.mapper.CombatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 12:56
 */
@Repository
public class CombatDao {
    @Autowired
    private CombatMapper combatMapper;
    public int addCombat(Combat combat){
        return combatMapper.insert(combat);
    }
    public int deleteCombatById(int id){
        return combatMapper.deleteByPrimaryKey(id);
    }
    public int deleteCombatByIds(List<Integer> ids){
        Example example = new Example(Combat.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        example.and(criteria);
        return combatMapper.deleteByExample(example);
    }
    public int updateCombatById(Combat combat){
        Combat combatOld = getCombatById(combat.getId());
        if (combat.getWinner() != null){
            combatOld.setWinner(combat.getWinner());
        }
        if (combat.getLoser() != null){
            combatOld.setLoser(combat.getLoser());
        }
        return combatMapper.updateByPrimaryKey(combatOld);
    }
    public Combat getCombatById(int id){
        return combatMapper.selectByPrimaryKey(id);
    }
    public List<Combat> getAllCombat(){
        return combatMapper.selectAll();
    }
}
