package com.war3elo.Service;

import com.war3elo.dao.CombatDao;
import com.war3elo.domain.Combat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 14:47
 */
@Service
public class CombatService {
    @Autowired
    private CombatDao combatDao;
    public int addCombat(Combat combat){
        return combatDao.addCombat(combat);
    }
    public List<Combat> getAllCombats(){
        return combatDao.getAllCombat();
    }
    public Combat getCombatById(int id){
        return combatDao.getCombatById(id);
    }
    public int deleteCombatById(int id){
        return combatDao.deleteCombatById(id);
    }
}
