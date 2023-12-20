package jpabook.jpashop.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest {

    @NotEmpty(message ="회원 이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @Builder
    public MemberRequest(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
