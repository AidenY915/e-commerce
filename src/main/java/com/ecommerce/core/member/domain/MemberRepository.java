package com.ecommerce.core.member.domain;

import com.ecommerce.core.member.infrastructure.MemberJpaRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
        public Page<Member> findAll(Pageable pageable);
        public Optional<Member> findById(UUID id);
        public Member save(Member member);
        public void deleteById(UUID id);

}
