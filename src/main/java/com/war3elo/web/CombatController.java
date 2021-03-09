package com.war3elo.web;

import com.war3elo.Service.CombatService;
import com.war3elo.Service.EloService;
import com.war3elo.Service.UserService;
import com.war3elo.Utils.JsonResult;
import com.war3elo.domain.Combat;
import com.war3elo.domain.Elo;
import com.war3elo.domain.User;
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
    @Autowired
    private UserService userService;

    @GetMapping(value = "/addCombat",produces = "application/json;charset=UTF-8")
    public JsonResult<List<Elo>> addCombat(@RequestParam("winner") String winner,
                                           @RequestParam("loser") String loser,
                                           @RequestParam(name = "winner_race",required = false) String winner_race,
                                           @RequestParam(name = "loser_race",required = false) String loser_race,
                                           @RequestParam(name = "map",required = false) String map){
        User userWinner = userService.getUserByUsername(winner);
        User userLoser = userService.getUserByUsername(loser);
        if (userWinner==null||userLoser==null){
            return new JsonResult<List<Elo>>(null,"1","添加失败，需要先添加选手");
        }

        Combat combat = new Combat();
        combat.setWinner(winner);
        combat.setLoser(loser);
        if (winner_race != null){
            combat.setWinnerRace(winner_race.toLowerCase());
        }
        if (loser_race != null){
            combat.setLoserRace(loser_race.toLowerCase());
        }
        if (map != null){
            combat.setMap(map.toLowerCase());
        }
        combat.setCreateTime(new Date());
        combatService.addCombat(combat);
        List<Elo> elos = eloService.addCombat(combat);
        return new JsonResult<>(elos);
    }
}
