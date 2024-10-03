package com.hhpluslecture.repository.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;

    private String memberId;

    private String password;

    private String memberName;

}
