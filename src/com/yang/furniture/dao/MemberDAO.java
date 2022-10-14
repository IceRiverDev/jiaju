package com.yang.furniture.dao;

import com.yang.furniture.entity.Member;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/22  3:37 PM
 */
public interface MemberDAO {
    Member queryMemberByUsername(String username);

    int saveMember(Member member);

    Member queryMemberByUsernameAndPassword(String username, String password);
}
