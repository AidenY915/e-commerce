package com.ecommerce.core.member.infrastructure;

import com.ecommerce.core.member.domain.Member;
import com.ecommerce.core.member.domain.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MemberRepositoryAdapter implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    public MemberRepositoryAdapter(MemberJpaRepository memberJpaRepository){
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return this.memberJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Member> findById(UUID id) {
        return this.memberJpaRepository.findById(id);
    }

    @Override
    public Member save(Member member) {
        return this.memberJpaRepository.save(member);
    }

    @Override
    public void deleteById(UUID id) {
        this.memberJpaRepository.deleteById(id);

    }
}
