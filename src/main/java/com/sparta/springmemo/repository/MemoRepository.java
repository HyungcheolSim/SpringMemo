package com.sparta.springmemo.repository;

import com.sparta.springmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

//jpa repository를 extends 함으로써 @repository 어노테이션이 붙은 효과
public interface MemoRepository extends JpaRepository<Memo,Long> {
}
