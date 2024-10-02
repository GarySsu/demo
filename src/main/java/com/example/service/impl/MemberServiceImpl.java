package com.example.service.impl;

import com.example.handler.exception.CustomException;
import com.example.model.entity.MemberEntity;
import com.example.model.vo.MemberVo;
import com.example.resp.RespCode;
import com.example.service.MemberService;
import com.example.utils.PageVo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author garyssu
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private final Map<Long, MemberEntity> memberMap = new ConcurrentHashMap<>();

    @Override
    public void addMember(String username) {
        if (checkUsernameExist(username)) {
            throw new CustomException(RespCode.USERNAME_NOT_ALLOWED.getCode(), RespCode.USERNAME_NOT_ALLOWED.getMessage());
        }

        MemberEntity member = new MemberEntity();
        member.setId(ID_GENERATOR.getAndIncrement());
        member.setUsername(username);
        member.setCreateTime(LocalDateTime.now());

        memberMap.put(member.getId(), member);
    }

    @Override
    public MemberVo getMember(Long id) {
        return memberMap.values().stream()
            .filter(memberEntity -> id.equals(memberEntity.getId()))
            .findFirst()
            .map(memberEntity -> {
                MemberVo vo = new MemberVo();
                vo.setId(memberEntity.getId());
                vo.setUsername(memberEntity.getUsername());
                vo.setCreateTime(memberEntity.getCreateTime());
                return vo;
            })
            .orElse(null);
    }

    @Override
    public void deleteMember(Long id) {
        memberMap.remove(id);
    }

    @Override
    public PageVo<MemberVo> getMemberPage(int page, int size) {
        List<MemberEntity> allMembers = new ArrayList<>(memberMap.values());
        long totalRecords = allMembers.size();

        int start = Math.min(page * size, allMembers.size());
        int end = Math.min(start + size, allMembers.size());

        List<MemberVo> content = allMembers.subList(start, end).stream()
            .map(memberEntity -> {
                MemberVo vo = new MemberVo();
                vo.setId(memberEntity.getId());
                vo.setUsername(memberEntity.getUsername());
                vo.setCreateTime(memberEntity.getCreateTime());
                return vo;
            })
            .collect(Collectors.toList());

        return new PageVo<>(page, size, totalRecords, content);
    }

    @Override
    public void updateMember(Long id, String username) {
        if (checkUsernameExist(username)) {
            throw new CustomException(RespCode.USERNAME_NOT_ALLOWED.getCode(), RespCode.USERNAME_NOT_ALLOWED.getMessage());
        }

        MemberEntity member = new MemberEntity();
        member.setUsername(username);

        memberMap.put(id, member);
    }

    @Override
    public Map<Long, MemberEntity> getAllmemberMap() {
        return this.memberMap;
    }

    private boolean checkUsernameExist(String username) {
        return memberMap.values().stream()
            .anyMatch(member -> username.equals(member.getUsername()));
    }

}
