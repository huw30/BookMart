/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.dao.impl;

import edu.pitt.dao.ObjectDAO;
import edu.pitt.domain.User;
import edu.pitt.utils.JdbcUtils;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * User Dao Implementation class
 *
 * @author huanwang
 * @version Apr 3. 2014
 */
public class UserDaoImpl implements ObjectDAO<User> {

    @Override
    public void add(User user) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into Users (userID, userName, password, type) values(?,?,?,?);";
            Object[] params = {user.getUserID(), user.getUserName(), user.getPassword(), user.getType()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(int id) {
        try {

            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from Users where id=?";
            return (User) runner.query(sql, id, new BeanHandler(User.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from Users;";

            return (List<User>) runner.query(sql, new BeanListHandler(User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void delete(User user) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "DELETE FROM Users WHERE userID = ?;";
            Object[] params = {user.getUserID()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void update(User user) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "update Users set userName=?, password=?, type=? where userID=?";
            Object[] params = {user.getUserName(), user.getPassword(), user.getType(),user.getUserID()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
