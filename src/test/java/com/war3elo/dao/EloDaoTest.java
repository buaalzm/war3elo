package com.war3elo.dao;

import com.war3elo.domain.Elo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 12:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EloDaoTest {
    @Autowired
    private EloDao eloService;
    @Test
    public void getAllElo(){
        List<Elo> elos = eloService.getALLElos();
        System.out.println(Arrays.asList(elos));
    }
    @Test
    public void addElo(){
        System.out.println(eloService.addElo("牛哥"));
    }
    @Test
    public void updateEloByUsername(){
        Elo elo = new Elo();
        elo.setUsername("牛哥");
        elo.setElo(600);
        eloService.updateEloByUsername(elo);
    }
    @Test
    public void deleteEloByUsername(){
        System.out.println(eloService.deleteEloByUsername("牛哥"));
    }
    @Test
    public void getEloByUsername(){
        System.out.println(eloService.getEloByUsername("牛哥"));
    }
}
