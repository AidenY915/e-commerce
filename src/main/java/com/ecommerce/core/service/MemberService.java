package com.ecommerce.core.service;

import com.ecommerce.core.member.Member;
import com.ecommerce.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public boolean deleteById(UUID id) {
        try {
            memberRepository.deleteById(id);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
