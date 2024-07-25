package entity;


import dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberName;

    @Column
    private String memberPassword;

    @Column
    private String memberPhone;

    @Column
    private String memberID;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberID(String.valueOf(memberDTO.getMemberID()));
        memberEntity.setMemberName(memberEntity.getMemberName());
        memberEntity.setMemberPassword(memberEntity.getMemberPassword());
        memberEntity.setMemberPhone(memberEntity.getMemberPhone());
        return memberEntity;
    }
}
