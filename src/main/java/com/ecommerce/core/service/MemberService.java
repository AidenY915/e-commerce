package com.ecommerce.core.service;

import com.ecommerce.core.member.Member;
import com.ecommerce.core.member.MemberRepository;
import com.ecommerce.core.member.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public Member save(MemberRequest request){
        Member member = new Member(
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
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

    public Member update(String id, MemberRequest request) {
        Member member = new Member(
                UUID.fromString(id),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                UUID.fromString(id),
                LocalDateTime.now(),
                UUID.fromString(id),
                LocalDateTime.now(),
                request.saltKey(),
                request.flag());
        return memberRepository.save(member);
    }
}
