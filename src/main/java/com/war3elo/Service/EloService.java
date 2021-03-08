package com.war3elo.Service;

import com.war3elo.Utils.ELOResult;
import com.war3elo.Utils.EloUtils;
import com.war3elo.dao.EloDao;
import com.war3elo.domain.Combat;
import com.war3elo.domain.Elo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 13:52
 */
@Service
public class EloService {
    @Autowired
    private EloUtils eloUtils;
    @Autowired
    private EloDao eloDao;
    /*
    * 添加完一场战斗，计算elo，并返回新的elo表
    * */
    public List<Elo> addCombat(Combat combat){
        Elo winnerElo = eloDao.getEloByUsername(combat.getWinner());
        if (winnerElo == null){
            // 如果elo库中不存在这个选手，则添加默认elo
            winnerElo = addDefaultElo(combat.getWinner());
        }
        Elo loserElo = eloDao.getEloByUsername(combat.getLoser());
        if (loserElo == null){
            // 如果elo库中不存在这个选手，则添加默认elo
            loserElo = addDefaultElo(combat.getLoser());
        }
        ELOResult eloResult = eloUtils.rating(winnerElo.getElo(), loserElo.getElo(), 1, false);
        winnerElo.setElo(eloResult.getRa()); // 更新胜者elo
        loserElo.setElo(eloResult.getRb()); // 更新败者elo
        eloDao.updateEloByUsername(winnerElo);
        eloDao.updateEloByUsername(loserElo);
        return eloDao.getALLElos();
    }
    public Elo addDefaultElo(String username){
        eloDao.addElo(username);
        Elo elo = new Elo();
        elo.setUsername(username);
        elo.setElo(eloDao.getDefaultElo());
        return elo;
    }
    public List<Elo> getAllElos(){
        return eloDao.getALLElos();
    }
    public int deleteEloByUsername(String username){
        return eloDao.deleteEloByUsername(username);
    }
    public int deleteEloById(int id){
        return eloDao.deleteEloById(id);
    }
}
