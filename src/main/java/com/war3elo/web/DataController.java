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

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 15:34
 */
@RestController
public class DataController {
    @Autowired
    private EloService eloService;
    @Autowired
    private CombatService combatService;

    @GetMapping(value="/getAllElos",produces = "application/json;charset=UTF-8")
    public JsonResult<List<Elo>> getAllElos(){
        return new JsonResult<List<Elo>>(eloService.getAllElos());
    }

    @GetMapping(value="/getAllCombats",produces = "application/json;charset=UTF-8")
    public JsonResult<List<Combat>> getAllCombats(){
        return new JsonResult<List<Combat>>(combatService.getAllCombats());
    }
    /*
    * 删除一个对战，成功删除后添加一局相反的对局更新elo
    * */
    @GetMapping(value="/deleteCombatById",produces = "application/json;charset=UTF-8")
    public JsonResult<Integer> deleteCombatById(@RequestParam("id") int id){
        Combat combat = combatService.getCombatById(id);
        if (combat == null){
            // id 没有对应的局
            return new JsonResult<Integer>(0,"1","操作失败");
        }
        int i = combatService.deleteCombatById(id);
        JsonResult<Integer> ret = new JsonResult(i);
        if (i == 0){
            // sql 未能成功删除
            return new JsonResult<Integer>(0,"1","操作失败");
        }
        else {
            String winner = combat.getWinner();
            String loser = combat.getLoser();
            Combat combat1 = new Combat();
            combat1.setWinner(loser);
            combat1.setLoser(winner);
            eloService.addCombat(combat1);
        }
        return ret;
    }
    @GetMapping(value="/deleteEloByUsername",produces = "application/json;charset=UTF-8")
    public JsonResult<Integer> deleteEloByUsername(@RequestParam("username") String username){
        int i = eloService.deleteEloByUsername(username);
        if (i == 0){
            // sql 未能成功删除
            return new JsonResult<Integer>(0,"1","操作失败");
        }
        return new JsonResult(0);
    }
    @GetMapping(value="/deleteEloById",produces = "application/json;charset=UTF-8")
    public JsonResult<Integer> deleteEloById(@RequestParam("id") int id){
        int i = eloService.deleteEloById(id);
        if (i == 0){
            // sql 未能成功删除
            return new JsonResult<Integer>(0,"1","操作失败");
        }
        return new JsonResult(0);
    }
}
