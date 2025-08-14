package com.back.domain.member.member.controller;


import com.back.domain.member.member.dto.MemberWithUsernameDto;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adm/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1AdmMemberController", description = "관리자용 API 회원 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
public class ApiV1AdmMemberController {
    private final MemberService memberService;

    @GetMapping
    @Transactional(readOnly = true)
    @Operation(summary = "다건 조회")
    public List<MemberWithUsernameDto> getItems() {
        List<Member> members = memberService.findAll();

        return members.stream()
                .map(MemberWithUsernameDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    @Operation(summary = "단건 조회")
    public MemberWithUsernameDto getItem(
            @PathVariable int id
    ) {
        Member member = memberService.findById(id).get();

        return new MemberWithUsernameDto(member);
    }
}
