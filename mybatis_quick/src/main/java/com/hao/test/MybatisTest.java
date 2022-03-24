package com.hao.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.dom.User;
import com.hao.dom.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    @Test
    public void mybatisQuickStart() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("userMapper.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 新增用户
     */
    @Test
    public void testSava() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setUsername("tom");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("美丽国");
        sqlSession.insert("userMapper.savaUser",user);
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    /**
     * 更新操作
     */
    @Test
    public void testuUpdate() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setId(5);
        user.setUsername("jerry");
        sqlSession.update("userMapper.updateUser",user);
        // 提交事物
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    /**
     * 删除操作
     */
    @Test
    public void testDelete() throws IOException {

        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        sqlSession.delete("userMapper.deleteUser",2);
        // 提交事物
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByIdAndUsername1(4, "tom");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();

    }
    @Test
    public void test2() throws IOException{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByUsername("%tom%");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();

    }


    /**
     * 返回主键，添加用户
     * @throws IOException
     */
    @Test
    public void test3() throws IOException{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("郝伟");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("太原");
        System.out.println(user);
        mapper.saveUser2(user);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void test4() throws IOException{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer[] arr = {4,7,8};
        List<User> users = mapper.foreachUser(arr);
        for (User user : users) {
            System.out.println(user);
        }


        sqlSession.close();

    }

    @Test
    public void test5() throws IOException{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(1,1);
        List<User> users = mapper.findByUsername("郝伟");
        for (User user : users) {
            System.out.println(user);
        }

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println("总条数：" + userPageInfo.getTotal());
        System.out.println("总页数：" + userPageInfo.getPages());
        sqlSession.close();

    }
}
