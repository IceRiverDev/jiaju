package com.yang.furniture.test;

/**
 * @author 刘洋
 * @date 2022/5/22  7:38 PM
 */

import com.yang.furniture.entity.Member;
import com.yang.furniture.service.Impl.MemberServiceImpl;
import com.yang.furniture.service.MemberService;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    private final MemberService memberService = new MemberServiceImpl();

    @Test
    public void login() {
        Member member = new Member(null, "admin", "admin", null, null);
        System.out.println(memberService.login(member));
    }

    @Test
    public void isExistsUsername() {
        if (memberService.checkUserIfExists("king")) {
            System.out.println("用户名存在...");
        } else {
            System.out.println("用户名不存在...");
        }
    }

    @Test
    public void registerMember() {
        //构建一个Member对象
        Member member = new Member(null, "mary", "mary", "mary@qq.com", 2);
        if (memberService.registerMember(member)) {
            System.out.println("注册用户成功...");
        } else {
            System.out.println("注册用户失败...");
        }
    }
}
