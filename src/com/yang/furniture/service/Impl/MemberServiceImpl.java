package com.yang.furniture.service.Impl;

import com.yang.furniture.dao.MemberDAO;
import com.yang.furniture.dao.impl.MemberDAOImpl;
import com.yang.furniture.entity.Member;
import com.yang.furniture.service.MemberService;

/**
 * @author 刘洋
 * @date 2022/5/22  3:19 PM
 */
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO = new MemberDAOImpl();

    public boolean checkUserIfExists(String username) {
        return memberDAO.queryMemberByUsername(username) != null;
    }

    public boolean registerMember(Member member) {
        return memberDAO.saveMember(member) == 1;
    }

    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());
    }

    public boolean adminMemberLogin(Member member) {
        Member memberRecord = memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());
        return memberRecord != null && memberRecord.getRole() == 1;
    }
}
