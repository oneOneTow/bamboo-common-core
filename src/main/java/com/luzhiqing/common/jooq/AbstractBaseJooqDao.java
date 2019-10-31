package com.luzhiqing.common.jooq;

import com.luzhiqing.common.web.Opertor;
import org.jooq.*;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 单表操作基础抽象类
 *
 * @param <R>
 * @param <T>
 * @param <P>
 */
public abstract class AbstractBaseJooqDao<R extends TableRecord<R>, T extends Table<R>, P> implements BaseJooqDao<P> {

    @Autowired
    protected DSLContext dsl;

    /**
     * 实体对应的数据库表
     */
    protected T table;
    /**
     * pojo Class
     */
    protected Class<P> clazz;
    /**
     * 主键
     */
    protected Field<Integer> pkField;

    /**---------公共信息-------**/
    protected Field<Timestamp> createTimeField;
    protected Field<UInteger> createUserField;
    protected Field<String> createUserNameField;

    /**
     * 主键名
     */
    private static final String PK_NAME = "ID";

    /**
     * 对应数据库表
     *
     * @return
     */
    protected abstract T getTable();

    /**
     * 对应pojo Class
     *
     * @return
     */
    protected abstract Class<P> getClazz();

    public AbstractBaseJooqDao() {
        this.table = getTable();
        this.clazz = getClazz();
        table.fields();
        for (Field field : table.fields()) {
            boolean isPrimaryKey = PK_NAME.equals(field.getName());
            if (isPrimaryKey) {
                pkField = field;
                break;
            }
        }
        initFields();
    }

    protected void initFields(){
        if(null == createTimeField){
            createTimeField = (Field<Timestamp>)getTable().field(CommonField.create_time);
        }
        if(null == createUserField){
            createUserField = (Field<UInteger>)getTable().field(CommonField.create_user);
        }
        if(null == createUserNameField){
            createUserNameField = (Field<String>)getTable().field(CommonField.create_user_name);
        }
    }

    protected void bindingCreatePublicInfo(Record record){
        Opertor opertor = Opertor.build();
        record.setValue(createTimeField,Timestamp.valueOf(LocalDateTime.now()));
        record.setValue(createUserField, UInteger.valueOf(opertor.getUid()));
        record.setValue(createUserNameField,opertor.getName());
    }


    @Override
    public P insert(P pojo) {
        R record = table.newRecord();
        record.from(pojo);
        bindingCreatePublicInfo(record);
        return dsl.insertInto(table).set(record).returning().fetchOne().into(clazz);
    }

    @Override
    public long delete(P pojo) {
        R record = table.newRecord();
        record.from(pojo);
        List<Condition> conditions = createConditions(record);
        return dsl.delete(table).where(conditions).execute();
    }


    @Override
    public int update(P pojo) {
        R record = table.newRecord();
        record.from(pojo);
        UpdateSetMoreStep<R> updateFromStep = createUpdateMoreStep(record);
        if (null != updateFromStep) {
            return updateFromStep.where(pkField.eq(record.getValue(pkField))).execute();
        }
        return 0;
    }

    @Override
    public int batchUpdate(List<P> pojos) {
        return 0;
    }

    @Override
    public void batchUpdateSync(List<P> pojos) {

    }

    @Override
    public List<P> select(P pojo) {
        R record = table.newRecord();
        record.from(pojo);
        List<Condition> conditions = createConditions(record);
        return dsl.selectFrom(table).where(conditions).fetchInto(clazz);
    }

    @Override
    public P selectOne(P pojo) {
        R record = table.newRecord();
        record.from(pojo);
        List<Condition> conditions = createConditions(record);
        return dsl.selectFrom(table).where(conditions).fetchOneInto(clazz);
    }

    @Override
    public P findById(Integer id) {
        return dsl.selectFrom(table).where(pkField.eq(id)).fetchOneInto(clazz);
    }

    @Override
    public int bachInsert(List<P> pojos) {
        return dsl.insertInto(table).values(pojos).execute();
    }

    @Override
    public void bachInsertSync(List<P> pojos) {
        dsl.insertInto(table).values(pojos).executeAsync();
    }

    /**
     * 根据record创建查询条件
     *
     * @param record
     * @return
     */
    private List<Condition> createConditions(R record) {
        List<Condition> conditions = new ArrayList<>();
        Field<?>[] fields = record.fields();
        for (Field field : fields) {
            if (null != record.get(field)) {
                conditions.add(field.eq(record.get(field)));
            }
        }
        if (CollectionUtils.isEmpty(conditions)) {
            throw new RuntimeException("条件为空");
        }
        return conditions;
    }

    /**
     * 根据record创建需要更新的列
     *
     * @param record
     * @return
     */
    private UpdateSetMoreStep<R> createUpdateMoreStep(R record) {
        UpdateSetMoreStep<R> updateFromMoreStep = null;
        for (Field field : table.fields()) {
            if (!PK_NAME.equals(field.getName())) {
                if (null == updateFromMoreStep) {
                    updateFromMoreStep = dsl.update(table).set(field, record.getValue(field));
                }
                updateFromMoreStep.set(field, record.getValue(field));
            }
        }
        return updateFromMoreStep;
    }
}

