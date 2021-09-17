package com.fastcampus.jpa.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class User {
    @NonNull
    private String name;

    @NonNull
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
