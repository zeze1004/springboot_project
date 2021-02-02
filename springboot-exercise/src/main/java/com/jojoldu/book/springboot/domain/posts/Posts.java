// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity 클래스라고도 한다
package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// 시간 저장
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter // 롬북 어노테이션
@NoArgsConstructor // 롬북 어노테이션
@Entity // JPA 어노테이션, 테이블과 링크될 클래스임을 나타낸다

public class Posts {
    @Id // 해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타냄
    private Long id;

    // 생성, 수정 시간
    private LocalDateTime createdTimeAt;
    private LocalDateTime updateTimeAt;

    // @Colunm: 테이블 컬럼을 뜻하며 굳이 나타내지 않아도 해당 클래스 필드는 모두 컬럼이 된다
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public Posts(String content) {
        this.content = content;

        final LocalDateTime now = LocalDateTime.now();
        createdTimeAt = now;
        updateTimeAt = now;
    }

}
