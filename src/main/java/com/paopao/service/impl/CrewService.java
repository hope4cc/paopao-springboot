package com.paopao.service.impl;

import com.paopao.cache.GlobalCache;
import com.paopao.dao.ICrewDao;
import com.paopao.exception.GlobalException;
import com.paopao.logger.SysLogger;
import com.paopao.model.pojo.Crew;
import com.paopao.model.pojo.Pager;
import com.paopao.service.ICrewService;

import com.paopao.util.DateUtil;
import com.paopao.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CrewService implements ICrewService {

    @Autowired
    private ICrewDao crewDao;
    @Autowired
    private GlobalCache globalCache;

    /**
     * 验证账号密码是否正确
     * @param crew
     * @return
     */
    public boolean auth(Crew crew){
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crew == null || crews.size() <= 0){
            return false;
        }
        return true;
    }

    /**
     * Token登录处理
     */
    public String tokenResolve(Crew crew, boolean doForce) {
        String key = crew.getCrewName();

        String token = TokenUtil.getToken(key);

        if(globalCache.getCacheValue(key) != null){
            if(doForce){
                globalCache.removeCache(key);
            }else{
                return globalCache.getCacheValue(key);
            }
        }

        globalCache.addCache(crew.getCrewName(),token);

        return token;
    }

    public boolean isCrewAlreadyExist(String crewName,String phoneNum,String mail){
        Crew crew = new Crew();
        crew.setCrewName(crewName);
        if(auth(crew)){
            return true;
        }
        crew = new Crew();
        crew.setPhoneNum(phoneNum);
        if(auth(crew)){
            return true;
        }
        crew = new Crew();
        crew.setMail(mail);
        if(auth(crew)){
            return true;
        }
        return false;
    }

    public int register(Crew crew){
        crew.setAvatarUrl(" ");
        crew.setCreateTime(DateUtil.getTimeString(new Date()));

        List<Crew> crews = new ArrayList<>();
        crews.add(crew);
        inserts(crews);
        return crews.get(0).getId();
    }

    @Override
    public Crew getByPhone(String phone) throws GlobalException {
        Crew crew = new Crew();
        crew.setPhoneNum(phone);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crews == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            SysLogger.info("检测到数据库数据关系异常,同一个手机号查询到了多个用户,此手机号为:" + phone);
            throw new GlobalException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public Crew getByMail(String mail) throws GlobalException {
        Crew crew = new Crew();
        crew.setMail(mail);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crews == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            SysLogger.info("检测到数据库数据关系异常,同一个邮箱查询到了多个用户,此邮箱为:" + mail);
            throw new GlobalException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public Crew getByCrewName(String crewName) throws GlobalException {
        Crew crew = new Crew();
        crew.setCrewName(crewName);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crews == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            SysLogger.info("检测到数据库数据关系异常,同一个用户名查询到了多个用户,此用户名为:" + crewName);
            throw new GlobalException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public Crew getById(int id) throws GlobalException {
        Crew crew = new Crew();
        crew.setId(id);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crews == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            SysLogger.info("检测到数据库数据关系异常,同一个用户id查询到了多个用户,此用户id为:" + id);
            throw new GlobalException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public void updateAvatar(String crewName,String fileName) throws GlobalException {
        Crew crew = getByCrewName(crewName);
        crew.setAvatarUrl(fileName);
        update(crew);
    }

    @Override
    public void inserts(List<Crew> items) {
        crewDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        crewDao.delete(id);

    }

    @Override
    public void deletes(List<Integer> ids) {
        crewDao.deletes(ids);
    }

    @Override
    public void update(Crew newCrew) throws GlobalException {
        //验证要修改的数据
        if(newCrew.getId() <= 0){
            throw new GlobalException("修改异常: id违规");
        }

        Crew oldCrew = getByCrewName(newCrew.getCrewName());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new GlobalException("昵称已注册");
        }

        oldCrew = getByPhone(newCrew.getPhoneNum());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new GlobalException("手机号已注册");
        }

        oldCrew = getByMail(newCrew.getMail());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new GlobalException("邮箱已注册");
        }

        crewDao.update(newCrew);
    }

    @Override
    public void updates(List<Crew> items) {
        crewDao.updates(items);
    }

    @Override
    public List<Crew> selects(Map<String, Object> condition) {
        return crewDao.selects(condition);
    }

    @Override
    public List<Crew> selects(Map<String, Object> condition, Pager pager) throws GlobalException {
        return  crewDao.selects(condition,pager);
    }
}
