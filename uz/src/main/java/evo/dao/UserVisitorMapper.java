package evo.dao;

import evo.model.UserVisitor;

public interface UserVisitorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVisitor record);

    int insertSelective(UserVisitor record);

    UserVisitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVisitor record);

    int updateByPrimaryKey(UserVisitor record);
}