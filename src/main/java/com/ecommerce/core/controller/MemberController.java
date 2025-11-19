package com.ecommerce.core.controller;

import com.ecommerce.core.common.ResponseEntity;
import com.ecommerce.core.member.Member;
import com.ecommerce.core.member.MemberRequest;
import com.ecommerce.core.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/members")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    MemberService memberService;


    @Operation(summary =" 회원 등록 ", description = "회원 가입")
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody MemberRequest request){
        Member rslt = memberService.save(request);
        return new ResponseEntity<Member>(200, rslt, rslt != null ? 1L : 0L); //내부적으로 잭슨이 동작함
    }
    @Operation(summary =" 회원 조회 ", description = "모든 회원 조회")
    @GetMapping
    public ResponseEntity<List<Member>> findAll() {
        List<Member> members =  memberService.findAll();
        Long count = (long)(members.size());
        return new ResponseEntity<List<Member>>(HttpStatus.OK.value(), members, count);
    }
    @Operation(summary =" 회원 수정 ", description = "회원 수정 메소드")
    @PutMapping("{id}") //수정
    public ResponseEntity<Member> update(@RequestBody MemberRequest request, @PathVariable("id") String id){
        Member rslt = memberService.update(id, request);
        return new ResponseEntity<Member>(200, rslt, rslt != null ? 1L : 0L); //삽입과 업데이트가 같은 함수를 사용

    }
    @Operation(summary =" 회원 탈퇴 ", description = "회원 삭제 메소드")
    @DeleteMapping("{id}")
    public ResponseEntity<Member> delete(@PathVariable("id") String id){
        memberService.deleteById(UUID.fromString(id));
        return new ResponseEntity<Member>(200, null, 0L);
    }
}
