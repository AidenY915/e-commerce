package com.ecommerce.core.member.application;

import com.ecommerce.core.member.application.dto.MemberCommand;
import com.ecommerce.core.member.application.dto.MemberInfo;
import com.ecommerce.core.member.domain.Member;
import com.ecommerce.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public MemberInfo save(MemberCommand request){
        Member member = Member.create(
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        return MemberInfo.from(memberRepository.save(member));
    }
    public List<MemberInfo> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable).stream().map(MemberInfo::from).toList();
    }
    public boolean deleteById(UUID id) {
        try {
            memberRepository.deleteById(id);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public MemberInfo update(String id, MemberCommand request) {
        UUID uuid = UUID.fromString(id);
        Member member = memberRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Member not found: " + id));
        member.update(request.email(), request.name(), request.password(), request.phone());
        return MemberInfo.from(memberRepository.save(member));
    }
}
