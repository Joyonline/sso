package com.me.top.service;

import com.me.top.mapper.MeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 使用MeService来分装service调用mapper层
 *
 * @param <T>
 */
@Transactional
public abstract class MeService<T> {

    @Autowired
    private MeMapper<T> meMapper;

    /**
     * 根据主键进行查询
     *
     * @param id
     * @return
     */
    public T queryById(Object id) {
        return meMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<T> queryAll() {
        return meMapper.selectAll();
    }

    /**
     * 根据id删除
     *
     * @return
     */
    public Integer deleteById(Object id) {
        return meMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入不为null的字段
     *
     * @param t
     */
    public void saveSelective(T t) {
        meMapper.insertSelective(t);
    }

    /**
     * 更新不为null的字段
     * @param t
     * @return
     */
    public Integer updateSelective(T t) {
        return meMapper.updateByPrimaryKeySelective(t);
    }


    /**
     * 根据对象来查询
     * @param t
     * @return
     */
    public T queryByT(T t){
        List<T> list = meMapper.select(t);
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }


}
