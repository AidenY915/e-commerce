package com.ecommerce.core.controller;

import com.ecommerce.core.member.Member;
import com.ecommerce.core.member.MemberRepository;
import com.ecommerce.core.member.MemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/members")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;


    @GetMapping
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    @PostMapping
    public Member create(@RequestBody MemberRequest request){
        UUID uuid =  UUID.randomUUID();
        Member member = new Member(
                uuid,
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                uuid,
                LocalDateTime.now(),
                uuid,
                LocalDateTime.now(),
                request.saltKey(),
                request.flag()
        );
        memberRepository.save(member);
        return member; //내부적으로 잭슨이 동작함
    }
    @Operation(summary =" 회원 수정 ", description = "회원 수정 메소드")
    @PutMapping("{id}") //수정
    public Member update(@RequestBody MemberRequest request, @PathVariable("id") String id){
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
        return memberRepository.save(member); //삽입과 업데이트가 같은 함수를 사용

    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id){
        memberRepository.deleteById(UUID.fromString(id));
    }
}
