package com.atf.mappers;

import java.util.List;

/**
 * 针对单个Entity对象的操作定义.
 * 
 * @param <T>
 */

public interface BaseDAO<T> {

	/**
	 * 根据序列号获取单条记录.
	 * 序列号一般为ID，不支持联合主键
	 *
	 * @param t
	 * @return
	 * @default statement name is 'find'
	 */
	public T selectByPrimaryKey(T t);

	/**
	 * 根据id主键获取单条记录.
	 * 序列号一般为ID，不支持联合主键
	 *
	 */
	public T selectByPrimaryKeyId(Integer id);

	/**
	 * 获取所有记录.
	 *
	 * @return
	 * @default statement name is 'queryAll'
	 */
	public List<T> selectAll();

	/**
	 * 分页查询
	 *
	 * @param t
	 * @return
	 * @default statement name is 'queryPage'
	 */
	public List<T> selectPageByConditionSelective(T t);


	/**
	 * 获取符合条件的记录.
	 *
	 * @param t
	 * @return
	 * @default statement name is 'query'
	 */
	public List<T> selectByConditionSelective(T t);

	/**
	 * 智能获取符合条件的记录.
	 */
	public List<T> selectByConditionMultiFunctionSelective(T t);

	/**
	 * 分页智能获取符合条件的记录.
	 *
	 * @param t
	 * @return
	 * @default statement name is 'query'
	 */
	public List<T> selectByConditionMultiFunctionSelectiveByPage(T t);

	/**
	 * 分页智能获取符合模糊条件的记录.
	 *
	 * @param t
	 * @return
	 * @default statement name is 'query'
	 */
	public List<T> selectFuzzyByConditionMultiFunctionSelectiveByPage(T t);

	/**
	 * 取符合查询条件的记录总数
	 *
	 * @param t
	 * @return
	 * @default statement name is 'count'
	 */
	public int count(T t);

//	/**
//	 * 删除符合查询条件的记录
//	 */
//	public int setDeleteByConditionMultiFunctionSelective(T t);

	/**
	 * 动态更新Entity记录
	 */
	public int updateByPrimaryKeySelective(T t);

	/**
	 * 智能动态更新Entity记录
	 */
	public int updateByPrimaryKeyMultiFunctionSelective(T t);


	/**
	 * 全部更新记录
	 *
	 * @default statement name is 'update'
	 * @param t
	 * @return
	 */
	public int updateByPrimaryKey(T t);

	/**
	 * 按主键删除记录
	 *
	 * @default statement name is 'deleteByCondition'
	 * @param t
	 * @return
	 */
	public int deleteByPrimaryKey(T t);

	/**
	 * 插入新记录.
	 * <p>
	 * 
     * @param t
	 * @default statement name is 'insert'
	 * @return id
	 */
	public int insert(T t);

	/**
	 * 动态插入新记录.
	 * 
     * @param t
	 * @default statement name is 'insertSelective'
	 * @return id
	 */
	public int insertSelective(T t);

}
