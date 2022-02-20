package cn.bookstore.dbpool;

import com.db.durid.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用dautils操作数据库
    private QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
//QueryRunner的execute方法，查询数据，只需要三个参数：
// sql语句，   ResultSetHandler接口的实现类的实例化对象以及       params。
    /**
     * update()方法用来执行 insert/update/deleta语句
     * @return 如果返回-1说明执行失败，其他表示正常，影响的行数
     */
    public int update(String sql,Object ... args){

        Connection connection = DBUtils.getConnection();
        try {
            int update = queryRunner.update(connection, sql, args);
            return  update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtils.close(connection);
        }

        return  -1;
    }

    /**
     * 查询返回一个javaBean的
     * @param sql 执行的sql 语句
     * @param type 返回的对象类型
     * @param args sql对应的参数
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(String sql,Class<T> type,Object ... args){
        Connection connection = DBUtils.getConnection();

        try {
            return  queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtils.close(connection);
        }

        return null;
    }

    /**
     * 查询返回多个javaBean的
     * @param sql 执行的sql 语句
     * @param type 返回的对象类型
     * @param args sql对应的参数
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(String sql, Class<T> type, Object ... args){
        Connection connection = DBUtils.getConnection();
        try {
            return  queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询返回一行一列的情况
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args){

        Connection connection = DBUtils.getConnection();

        try {
            return  queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtils.close(connection);
        }
        return  null;
    }

}
