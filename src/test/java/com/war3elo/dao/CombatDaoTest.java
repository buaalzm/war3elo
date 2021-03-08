package com.war3elo.dao;

import com.war3elo.domain.Combat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 12:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CombatDaoTest {
    @Autowired
    private CombatDao combatService;
    @Test
    public void addCombat(){
        Combat combat = new Combat();
        combat.setWinner("牛哥");
        combat.setLoser("函数");
        combat.setCreateTime(new Date());
        System.out.println(combatService.addCombat(combat));
    }
    @Test
    public void deleteCombatById(){
        System.out.println(combatService.deleteCombatById(1));
    }
    @Test
    public void deleteCombatByIds(){
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        System.out.println(combatService.deleteCombatByIds(ids));
    }
    @Test
    public void updateCombatById(){
        Combat combatNew = new Combat();
        combatNew.setId(3);
        combatNew.setWinner("niuge");
        System.out.println(combatService.updateCombatById(combatNew));
    }
    @Test
    public void getCombatById(){
        System.out.println(combatService.getCombatById(3));
    }

}
