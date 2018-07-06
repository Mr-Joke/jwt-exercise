package com.example.demo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 9:46
 **/
public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static Object lock = new Object();
    private DBUtils(){}
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (sqlSessionFactory == null){
            synchronized (lock){
                if (sqlSessionFactory == null) {
                    InputStream is = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                }
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() throws IOException {
        return getSqlSessionFactory().openSession();
    }

    public static void closeSession(SqlSession sqlSession){
        if (sqlSession != null){
            sqlSession.close();
        }
    }
}
