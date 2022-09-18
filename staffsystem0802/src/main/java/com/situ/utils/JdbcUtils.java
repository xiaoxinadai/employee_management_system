package com.situ.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc工具类
 * 
 */
public final class JdbcUtils {
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    private JdbcUtils() {
        // ignore
    }

    /**
     * 获取数据库连接
     * 
     * @return
     */
    public static Connection getConnection(String driverName, String url, String user, String password) {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败", e);
        }
    }

    public static Connection getConnection(String url, String user, String password) {
        return getConnection(MYSQL_DRIVER, url, user, password);
    }

    /**
     * 数据库查询返回一个结果集
     * 
     * @return
     */
    public static ResultSet executeQuery(Connection conn, String sql, Object... args) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询结果集失败", e);
        }
    }

    /**
     * 查询表，并返回一个对象的集合
     * 
     * @return
     */
    public static <T> T query(Connection conn, ResultSetHandler<T> handler, String sql, Object... args) {
        ResultSet rs = executeQuery(conn, sql, args);
        return handler.handle(rs);
    }

    /**
     * 增删改
     * 
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public static int update(Connection conn, String sql, Object... args) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            return ps.executeUpdate();// 返回爱影响的行数

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("对数据库进行增删改操作失败", e);
        }
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一个下划线命名字符串转换成驼峰式
     * 
     * 强制约束：首字符不能是下划线
     * 
     * @param str
     * @return
     */
    public static String underlineToCamel(String str) {
        // a_student_name_is_low 2 aStudentNameIsLow
        StringBuilder sb = new StringBuilder();

        String[] tokens = str.split("_");
        sb.append(tokens[0]);

        for (int i = 1; i < tokens.length; i++) {
            String firstLetter = tokens[i].substring(0, 1);
            String remain = tokens[i].substring(1);
            sb.append(firstLetter.toUpperCase() + remain);
        }

        return sb.toString();
    }

    /**
     * 将obj类转换成正确的类型
     * 
     * @param obj
     * @param pd
     * @return
     */
    private static Object cast(Object obj, PropertyDescriptor pd) {
        if (obj == null) {
            return obj;
        }

        if (pd.getPropertyType() == Integer.class) {
            return ((Number)obj).intValue();
        }

        if (pd.getPropertyType() == Long.class) {
            return ((Number)obj).longValue();
        }

        if (pd.getPropertyType() == Float.class) {
            return ((Number)obj).floatValue();
        }

        if (pd.getPropertyType() == Double.class) {
            return ((Number)obj).doubleValue();
        }

        if (pd.getPropertyType() == LocalDate.class) {
            Instant is = Instant.ofEpochMilli(((Date)obj).getTime());
            return LocalDate.ofInstant(is, ZoneId.systemDefault());
        }

        if (pd.getPropertyType() == LocalTime.class) {
            Instant is = Instant.ofEpochMilli(((Date)obj).getTime());
            return LocalTime.ofInstant(is, ZoneId.systemDefault());
        }

        if (pd.getPropertyType() == LocalDateTime.class) {
            Instant is = Instant.ofEpochMilli(((Date)obj).getTime());
            return LocalDateTime.ofInstant(is, ZoneId.systemDefault());
        }

        return obj;
    }

    /**
     * 静态内部接口，可以省略static关键字。用于将结果集转换成目标类型
     * 
     * 结果集转换器
     * 
     * @author snow1k
     * @date 2022/07/22
     */
    public interface ResultSetHandler<T> {
        /**
         * 就是把结果集转换成一个目标类型
         * 
         * @param rs
         * @return
         */
        public T handle(ResultSet rs);
    }

    /**
     * 自动将结果集转换成Bean的转换器
     * 
     * @author snow1k
     * @date 2022/07/22
     */
    public static class BeanListHandler<T> implements ResultSetHandler<List<T>> {
        private final Constructor<T> constructor;// 构造函数
        // 存储所有属性描述符，以属性名为key
        private final Map<String, PropertyDescriptor> descriptors = new HashMap<>();

        public BeanListHandler(Class<T> clazz) {
            try {
                // 获取一个javabean的信息
                BeanInfo bi = Introspector.getBeanInfo(clazz);
                // 获取模型类（javabean）的所有属性描述符
                PropertyDescriptor[] pds = bi.getPropertyDescriptors();

                // 以属性名为key，存储所有的属性描述符
                for (PropertyDescriptor pd : pds) {
                    descriptors.put(pd.getName(), pd);
                }

                // 强制要求模型类必须有一个公有的无参的构造函数
                this.constructor = clazz.getDeclaredConstructor();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("无法调用模型类的无参的构造函数", e);
            }
        }

        // 把一个结果集转换成一个目标对象的集合
        @Override
        public List<T> handle(ResultSet rs) {
            List<T> result = new ArrayList<>();

            List<String> columns = new ArrayList<>();// 存储结果集的所有列名（别名）
            try {
                // 元数据
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 0; i < count; i++) {
                    columns.add(rsmd.getColumnLabel(i + 1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // 从结果集取数据转换成目标集合
            try {
                while (rs.next()) {
                    T t = this.constructor.newInstance();

                    for (String column : columns) {
                        Object obj = rs.getObject(column);
                        // 把列名转换成模型类的属性名
                        String property = underlineToCamel(column);
                        // 获取描述某属性的属性描述符对象
                        PropertyDescriptor pd = descriptors.get(property);
                        Method method = pd.getWriteMethod();

                        // 通过反射给目标对象设置值
                        // 转换成正确的数据类型
                        method.invoke(t, cast(obj, pd));

                    }
                    result.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    /**
     * 返回单个数字的结果集转换器
     * 
     * @author snow1k
     * @date 2022/08/03
     */
    public static class LongResultSetHandler implements ResultSetHandler<Long> {
        @Override
        public Long handle(ResultSet rs) {
            try {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("结果集转换异常", e);
            }

            return 0l;
        }
    }
}
