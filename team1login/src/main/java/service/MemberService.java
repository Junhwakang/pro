package service;

import dto.MemberDTO;
import entity.MemberEntity;
import entity.memberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.MemberRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity)

    }

    public MemberDTO login(MemberDTO memberDTO) {
        // DB에서 조회
        // 비밀번호 판단
        Optional<MemberEntity> byMemberID = memberRepository.findByMemberID(memberDTO.getMemberID());
        if (byMemberID.isPresent()) {
            //조회 결과 있음
            MemberEntity memberEntity = byMemberID.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비밀번호 일치
                //entity에서 dto 변환후 리턴
                MemberDTO dto = memberDTO.toMemberDTO(memberEntity);
                return dto;
            }   else {
                //불일치
                return null;
            }
            } else {
            //조회 결과 없다
            return null;
        }
        }
        public List<MemberDTO> findAll() {
            List<MemberEntity> memberEntityList = memberRepository.findAll();
            List<MemberDTO> memberDTOList = new ArrayList<>();
            for (MemberEntity memberEntity : memberEntityList) {
                memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
                MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
                memberDTOList.add(memberDTO);
            }
            return memberDTOList;
    }
        public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }
    public MemberDTO updateForm(String myID) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberID(myID);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
    public void update(MemberDTO memberDTO) { memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO)); }

    public void deleteById(Long id) {memberRepository.deleteById(id); }

    public String IDCheck(String memberID){
        Optional<MemberEntity> bymemberID = memberRepository.findByMemberID(memberID);
        if (bymemberID.isPresent()) {
            return null;
        } else{
            return "OK";
        }
    }
}
