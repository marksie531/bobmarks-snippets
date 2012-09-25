package org.bobmarks.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Connection factory class.
 */
public class ConnectionFactory {

    private static SqlSessionFactory sqlMapper;

    static{
        try {
            Reader reader = Resources.getResourceAsReader("configuration.xml");
            sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlMapper;
    }
}