package com.example.controller;

import com.example.model.dto.MemberDto;
import com.example.resp.Resp;
import com.example.resp.RespCode;
import com.example.service.MemberService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author garyssu
 */
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public Resp addMember(@RequestBody MemberDto memberDto) {
        memberService.addMember(memberDto.getUsername());
        return new Resp(RespCode.SUCCESS);
    }

    @GetMapping("/members")
    public Resp getMembers(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size) {
        return new Resp(RespCode.SUCCESS, memberService.getMemberPage(page, size));
    }

    @GetMapping("/member/{id}")
    public Resp getMember(@NotNull @PathVariable("id") Long id) {
        return new Resp(RespCode.SUCCESS, memberService.getMember(id));
    }

    @DeleteMapping("/member/{id}")
    public Resp deleteMember(@NotNull @PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new Resp(RespCode.SUCCESS);
    }

    @PutMapping
    public Resp updateMember(@RequestBody MemberDto memberDto) {
        memberService.updateMember(memberDto.getId(), memberDto.getUsername());
        return new Resp(RespCode.SUCCESS);
    }

}
