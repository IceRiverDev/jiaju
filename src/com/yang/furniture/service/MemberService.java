package com.yang.furniture.service;

import com.yang.furniture.entity.Member;

/**
 * @author 刘洋
 * @date 2022/5/22  7:33 PM
 */
public interface MemberService {
    Member login(Member member);

    boolean registerMember(Member member);

    boolean checkUserIfExists(String username);

    boolean adminMemberLogin(Member member);
}
