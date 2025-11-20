package com.ecommerce.core.member.presentation;

import com.ecommerce.core.common.ResponseEntity;
import com.ecommerce.core.member.application.dto.MemberInfo;
import com.ecommerce.core.member.domain.Member;
import com.ecommerce.core.member.presentation.dto.MemberRequest;
import com.ecommerce.core.member.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MemberInfo> create(@RequestBody MemberRequest request){
        MemberInfo rslt = memberService.save(request.toCommand());
        return new ResponseEntity<MemberInfo>(200, rslt, rslt != null ? 1L : 0L); //내부적으로 잭슨이 동작함
    }
    @Operation(summary =" 회원 조회 ", description = "모든 회원 조회")
    @GetMapping
    public ResponseEntity<List<MemberInfo>> findAll(Pageable pageable) {
        List<MemberInfo> members =  memberService.findAll(pageable);
        Long count = (long)(members.size());
        return new ResponseEntity<List<MemberInfo>>(HttpStatus.OK.value(), members, count);
    }
    @Operation(summary =" 회원 수정 ", description = "회원 수정 메소드")
    @PutMapping("{id}") //수정
    public ResponseEntity<MemberInfo> update(@RequestBody MemberRequest request, @PathVariable("id") String id){
        MemberInfo rslt = memberService.update(id, request.toCommand());
        return new ResponseEntity<MemberInfo>(200, rslt, rslt != null ? 1L : 0L); //삽입과 업데이트가 같은 함수를 사용

    }
    @Operation(summary =" 회원 탈퇴 ", description = "회원 삭제 메소드")
    @DeleteMapping("{id}")
    public ResponseEntity<MemberInfo> delete(@PathVariable("id") String id){
        memberService.deleteById(UUID.fromString(id));
        return new ResponseEntity<MemberInfo>(200, null, 0L);
    }
}
