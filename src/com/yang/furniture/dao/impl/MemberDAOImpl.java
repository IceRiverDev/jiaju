package com.yang.furniture.dao.impl;

import com.yang.furniture.dao.BasicDAO;
import com.yang.furniture.dao.MemberDAO;
import com.yang.furniture.entity.Member;

/**
 * @author 刘洋
 * @date 2022/5/22  3:14 PM
 */
public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {

    @Override
    public Member queryMemberByUsername(String username) {
        return querySingle("select * from member where username = ?", Member.class, username);
    }

    @Override
    public int saveMember(Member member) {
        return update("insert into member (username, password, email) values (?, md5(?), ?)", member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        return querySingle("select * from member where username = ? and password = md5(?)", Member.class, username, password);
    }
}
