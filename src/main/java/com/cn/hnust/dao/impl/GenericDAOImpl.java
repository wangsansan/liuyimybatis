package com.cn.hnust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.cn.hnust.dao.GenericDAO;
import com.cn.hnust.dao.TableDesc;
import com.cn.hnust.pojo.BaseTableEntity;
import com.cn.hnust.pojo.Criteria;
import com.cn.hnust.pojo.PageBean;

/**
 * 通用DAO实现
 * User: wuqingming
 * Date: 14-10-23
 * Time: 上午9:28
 * To change this template use File | Settings | File Templates.
 */
public class GenericDAOImpl<E, PK> extends SqlSessionDaoSupport implements GenericDAO<E, PK> {

    public static final String SQL_SELECT = "get";
    public static final String SQL_SELECT_BY_PRIMARY_KEY = "get%sById";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_REMOVE = "remove";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_COUNT = "get%sCount";
    /**
     * 分页查询 内容
     */
//    public static final String SQL_PAGE_QUERY = "get%sPageQuery";
    public static final String SQL_PAGE_QUERY = "pageQuery%sList";
    /**
     * 分页查询 内容
     */
    public static final String SQL_PAGE_QUERY_COUNT = "get%sPageQueryCount";
    
    private final String nameSpace;
    private final String tableName;
    private final String sqlSelect;
    private final String sqlSelectByPrimaryKey;
    private final String sqlCount;
    private final String sqlInsert;
    private final String sqlUpdate;
    private final String sqlRemove;
    private final String sqlDelete;
    private final String sqlPageQuery;
    private final String sqlPageQueryCount;
    
    
    @Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    } 

    public GenericDAOImpl() {
        TableDesc myTable = (TableDesc) getClass().getAnnotation(TableDesc.class);
        Assert.notNull(myTable);
        tableName = myTable.tableName() == null ? "" : myTable.tableName();
        //Assert.notNull(tableName);
        String nameSpaceTmp = myTable.nameSpace();
        nameSpace = StringUtils.isEmpty(nameSpaceTmp) ? "" : nameSpaceTmp + ".";
        sqlSelect = new StringBuffer().append(SQL_SELECT).append(tableName).toString();
        sqlSelectByPrimaryKey = String.format(SQL_SELECT_BY_PRIMARY_KEY, tableName);
        sqlInsert = new StringBuffer().append(SQL_INSERT).append(tableName).toString();
        sqlUpdate = new StringBuffer().append(SQL_UPDATE).append(tableName).toString();
        sqlRemove = new StringBuffer().append(SQL_REMOVE).append(tableName).toString();
        sqlDelete = new StringBuffer().append(SQL_DELETE).append(tableName).toString();
        sqlCount =  String.format(SQL_COUNT, tableName);
        //
        sqlPageQuery = String.format(SQL_PAGE_QUERY,tableName);
        sqlPageQueryCount = String.format(SQL_PAGE_QUERY_COUNT,tableName);
    }

    public int insert(E entity) {
        return insert(sqlInsert, entity);
    }

    public int update(E entity) {
        return update(sqlUpdate, entity);
    }

    @Override
    public int remove(E entity) {
        return update(sqlRemove, entity);
    }

    public int delete(PK id) {
        return delete(sqlDelete, id);
    }

    @SuppressWarnings("unchecked")
	public E get(PK id) {
        Object entity = get(sqlSelectByPrimaryKey, id);
        return entity == null ? null : (E)entity;
    }

    public long getCount(Criteria criteria) {
        return getCountBy(sqlCount, criteria);
    }

    @SuppressWarnings("unchecked")
	public List<E> queryForList(Criteria criteria) {
        return (List<E>)queryForList(sqlPageQuery, criteria);
    }

    @Override
    public List<E> queryForPageList(Criteria criteria, PageBean<?> pageCriteria) {
        Assert.notNull(criteria, "查询条件不能为空.");
        Assert.notNull(pageCriteria, "分页条件不能为空.");
        criteria.addExtField("start", (pageCriteria.getPageNo() - 1) * pageCriteria.getPageSize());
        criteria.addExtField("limit", pageCriteria.getPageSize());
        List<E> resultList = this.queryForList(criteria);
        return resultList;
    }

    public PageBean<E> pageQuery(Criteria criteria, PageBean<E> pageBean) {
        Assert.notNull(criteria, "查询条件不能为空.");
        Assert.notNull(pageBean, "分页条件不能为空.");
        long count = getCount(criteria);
        pageBean.setTotalCount(count);
        if (count == 0) {
            return pageBean;
        }
        criteria.addExtField("start", (pageBean.getPageNo() - 1) * pageBean.getPageSize());
        criteria.addExtField("limit", pageBean.getPageSize());
        List<E> resultList = this.queryForList(criteria);
        
        pageBean.setResultList(resultList);
        return pageBean;
    }

    public boolean exists(PK id) {
        return exists(sqlSelect, id);
    }

    /**
     * 插入记录
     * @param statement sql实例ID
     * @param entity    插入信息
     * @return  成功插入记录数
     */
    protected int insert(String statement, Object entity) {
        return getSqlSession().insert(addNameSpace(statement), entity);
    }

    /**
     * 插入记录
     * @param statement sql实例ID
     * @param entity    插入信息
     * @return  成功插入记录数
     */
    protected int update(String statement, Object entity) {
        return getSqlSession().update(addNameSpace(statement), entity);
    }

    /**
     * 删除记录
     * @param statement sql实例ID
     * @param id    删除条件
     * @return  成功删除记录数
     */
    protected int delete(String statement, Object id) {
        return getSqlSession().delete(addNameSpace(statement), id);
    }

    /**
     * 获取单条记录
     * @param statement sql实例ID
     * @param criteria    查询条件
     * @return
     */
	@SuppressWarnings("unchecked")
	protected <T> T get(String statement, Object criteria) {
        Object object = getSqlSession().selectOne(addNameSpace(statement), criteria);
        return object == null ? null : (T)object;
    }

    /**
     * 查询列表
     * @param statement sql实例ID
     * @param criteria    查询条件
     * @return  结果列表
     */
    protected <T> List<T> queryForList(String statement, Object criteria) {
    	@SuppressWarnings("unchecked")
		List<T> result = (List<T>)getSqlSession().selectList(addNameSpace(statement), criteria);
        return result;
    }

    /**
     * 判断记录是否存在
     * @param statement sql实例ID
     * @param id    记录存在条件
     * @return  true-存在，false-不存在
     */
    protected boolean exists(String statement, Object id) {
        return get(statement, id) == null ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * 添加命名空间前缀
     * @param statement sql实例ID
     * @return
     */
    protected String addNameSpace(String statement){
        
    	String str = new StringBuffer().append(nameSpace).append(statement).toString();
    	return str;
    }

    /**
     * 获取满足条件的记录数
     * @param statement  sql实例，不需要添加前缀
     * @param criteria   查询条件
     * @return   满足条件的记录数
     */
    protected long getCountBy(String statement, Object criteria) {
        Object result = getSqlSession().selectOne(addNameSpace(statement), criteria);
        long count;
        try {
            count = (null == result) ? 0 : (result instanceof Integer ? ((Integer)result).intValue() : (Long)result) ;
        } catch (ClassCastException e) {
            logger.error("sql语句：" + statement + "查询结果非数字类型，无法用于查询数量");
            throw e;
        }
        return count;
    }

    /**
     * 分页查询
     * @param countStatement    记录数sql实例ID，不需要添加前缀
     * @param listStatement     列表sql实例ID，不需要添加前缀
     * @param criteria  查询条件
     * @param pageBean  分页参数
     * @param <T>   返回记录类型
     * @return  分页结果
     */
	protected <T> PageBean<T> pageQueryBy(String countStatement, String listStatement, Criteria criteria, PageBean<T> pageBean) {
        Assert.notNull(criteria, "查询条件不能为空.");
        Assert.notNull(pageBean, "分页条件不能为空.");
        long count = getCountBy(countStatement, criteria);
        pageBean.setTotalCount(count);
        if (count == 0) {
            return pageBean;
        }
        criteria.addExtField("start", (pageBean.getPageNo() - 1) * pageBean.getPageSize());
        criteria.addExtField("limit", pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<T> resultList = (List<T>)this.queryForList(listStatement, criteria);
        pageBean.setResultList(resultList);
        return pageBean;
    }
	
	@Override
	public <Q extends BaseTableEntity, R> PageBean<R> pageQuery(Q qryData, Long pageNo, Integer pageSize) {
		return pageQueryBy(this.sqlPageQueryCount, this.sqlPageQuery, qryData, pageNo, pageSize);
	}
    
    /**
     * 分页查询
     * @param countStatement    记录数sql实例ID，不需要添加前缀
     * @param listStatement     列表sql实例ID，不需要添加前缀
     * @param criteria  查询条件
     * @param pageBean  分页参数
     * @param <T>   返回记录类型
     * @return  分页结果
     */
    protected <Q extends BaseTableEntity,R> PageBean<R> pageQueryBy(String countStatement, String listStatement, Q qryData, Long pageNo, Integer pageSize) {
        Assert.notNull(qryData, "查询条件不能为空.");
        PageBean<R> pageBean = new PageBean<R>();
        pageBean.setPageNo(pageNo==null ? 1 : pageNo);
        pageBean.setPageSize(pageSize==null ? PageBean.DEFAULT_PAGE_SIZE : pageSize);
        long count = getCountBy(countStatement, qryData);
        pageBean.setTotalCount(count);
        if (count == 0) {
            return pageBean;
        }
        qryData.setQryStart((pageBean.getPageNo() - 1) * pageBean.getPageSize());
        qryData.setQryCount(pageBean.getPageSize());
        @SuppressWarnings("unchecked")
		List<R> resultList = (List<R>)this.queryForList(listStatement, qryData);
        pageBean.setResultList(resultList);
        return pageBean;
    }
}
