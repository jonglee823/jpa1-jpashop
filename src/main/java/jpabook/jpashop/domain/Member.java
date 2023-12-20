package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.request.MemberRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Member(MemberRequest memberRequest){
        this.name = memberRequest.getName();
        this.address = Address.builder()
                                .city(memberRequest.getCity())
                                .street(memberRequest.getStreet())
                                .zipcode(memberRequest.getZipcode())
                                .build();
    }
}
