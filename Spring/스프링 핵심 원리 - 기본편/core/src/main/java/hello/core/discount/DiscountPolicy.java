package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    public int discount(Member member, int price);
}
