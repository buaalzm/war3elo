package com.war3elo.web;

import com.war3elo.Service.CombatService;
import com.war3elo.Service.EloService;
import com.war3elo.Utils.JsonResult;
import com.war3elo.domain.Combat;
import com.war3elo.domain.Elo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 14:43
 */
@RestController
public class CombatController {
    @Autowired
    private CombatService combatService;
    @Autowired
    private EloService eloService;

    @GetMapping(value = "/addCombat",produces= MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<List<Elo>> addCombat(@RequestParam("winner") String winner,@RequestParam("loser") String loser){
        Combat combat = new Combat();
        combat.setWinner(winner);
        combat.setLoser(loser);
        combat.setCreateTime(new Date());
        combatService.addCombat(combat);
        List<Elo> elos = eloService.addCombat(combat);
        return new JsonResult<>(elos);
    }
}
