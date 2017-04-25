package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.BaseTableEntity;
import com.cn.hnust.pojo.Criteria;
import com.cn.hnust.pojo.PageBean;

/**
 * DAO层公共接口
 * User: liuyi
 * Date: 2016年7月21日
 * Time: 上午10:19
 * 
 * Remark:增加pageQuery重载接口
 */
public interface GenericDAO<E, PK> {
    /**
     * 插入记录
     * @param entity 记录
     * @return
     */
    int insert(E entity);

    /**
     * 插入记录
     * @param entity 记录
     * @return
     */
    int update(E entity);

    /**
     * 逻辑删除记录
     * @param entity  记录
     * @return
     */
    int remove(E entity);

    /**
     * 物理删除
     * @param id 记录ID
     * @return
     */
    int delete(PK id);

    /**
     * 获取记录
     * @param id 记录ID
     * @return
     */
    E get(PK id);

    /**
     * 获取记录数
     * @param criteria 查询条件
     * @return
     */
    long getCount(Criteria criteria);

    /**
     * 查询记录列表
     * @param criteria  查询条件
     * @return
     */
    List<E> queryForList(Criteria criteria);

    /**
     * 查询分页后的记录列表
     * @param criteria  查询条件
     * @param pageCriteria  分页条件
     * @return
     */
    List<E> queryForPageList(Criteria criteria, PageBean<?> pageCriteria);

    /**
     * 分页查询记录信息
     * @param criteria 查询条件
     * @param  pageBean     分页信息
     * @return
     */
    PageBean<E> pageQuery(Criteria criteria, PageBean<E> pageBean);
    
    /**
     * 分页查询记录信息
     * @param qryData  查询条件
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return
     */
    <Q extends BaseTableEntity,R> PageBean<R> pageQuery(Q qryData, Long pageNo, Integer pageSize);

    /**
     * 判断记录是否存在
     * @param id  记录ID
     * @return
     */
    boolean exists(PK id);
}