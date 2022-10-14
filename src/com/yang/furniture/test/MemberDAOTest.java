package com.yang.furniture.test;

import com.yang.furniture.dao.MemberDAO;
import com.yang.furniture.dao.impl.MemberDAOImpl;
import com.yang.furniture.entity.Member;
import org.junit.jupiter.api.Test;

/**
 * @author 刘洋
 * @date 2022/5/22  7:41 PM
 */
public class MemberDAOTest {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Test
    public void queryMemberByUsername() {

        if (memberDAO.queryMemberByUsername("admin") == null) {
            System.out.println("该用户名不存在...");
        } else {
            System.out.println("该用户名存在...");
        }
    }

    @Test
    public void saveMember() {

        Member member =
                new Member(null, "king", "king", "king@sohu.com", 2);
        if (memberDAO.saveMember(member) == 1) {
            System.out.println("添加OK");
        } else {
            System.out.println("添加失败...");
        }
    }

    @Test
    public void queryMemberByUsernameAndPassword() {

        Member member = memberDAO.queryMemberByUsernameAndPassword("admin", "123");
        System.out.println("member= " + member);
    }
}
