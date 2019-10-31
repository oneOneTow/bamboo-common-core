package com.luzhiqing.common.jooq;

import java.util.List;

/**
 * 数据库单表操作基础接口
 *
 * @param <P>
 */
public interface BaseJooqDao<P> {
    /**
     * 向数据库插入一条记录
     *
     * @param pojo
     * @return
     */
    P insert(P pojo);



    /**
     * 删除满足条件的记录
     *
     * @param pojo
     * @return
     */
    long delete(P pojo);


    /**
     * 根据主键修改记录
     *
     * @param pojo
     * @return
     */
    int update(P pojo);

    /**
     * 同步批量更新
     *
     * @param pojos
     * @return
     */
    int batchUpdate(List<P> pojos);

    /**
     * 异步批量更新
     *
     * @param pojos
     * @return
     */
    void batchUpdateSync(List<P> pojos);

    /**
     * 查询满足条件的记录
     *
     * @param pojo
     * @return
     */
    List<P> select(P pojo);

    /**
     * 查询满足条件的单条记录
     *
     * @param pojo
     * @return
     */
    P selectOne(P pojo);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    P findById(Integer id);

    /**
     * 批量插入数据
     *
     * @param pojos
     * @return
     */
    int bachInsert(List<P> pojos);

    /**
     * 异步批量插入
     *
     * @param pojos
     * @return
     */
    void bachInsertSync(List<P> pojos);
}
