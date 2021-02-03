// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity 클래스라고도 한다
package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
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

// Posts가 BaseTimeEntity 클래스 상속 받음
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타냄
    private Long id;    // 일기 하나의 id
    private Long emotion;

    // 생성, 수정 시간
    private LocalDateTime createdTimeAt;
    private LocalDateTime updateTimeAt;

    // @Colunm: 테이블 컬럼을 뜻하며 굳이 나타내지 않아도 해당 클래스 필드는 모두 컬럼이 된다
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public Posts(String content, Long emotion) {
        this.content = content;
        this.emotion = emotion;

        final LocalDateTime now = LocalDateTime.now();
        createdTimeAt = now;
        updateTimeAt = now;
    }
    // update 기능에서 DB에 쿼리를 날리는 부분X, JPA 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
    // 트랜잭션 안에서 DB에서 데이터를 가져오면 데이터는 영속성 컨텍스트가 유지
    // 이 상태에서 해당 데이터 값 변경시 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영
    // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날리 필요 X => 이 개념을 '더티 체킹'이라 한다
    public void update(String content, Long emotion) {
        this.content = content;
        this.emotion = emotion;
    }
}
