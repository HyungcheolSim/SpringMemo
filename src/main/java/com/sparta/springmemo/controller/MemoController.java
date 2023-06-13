package com.sparta.springmemo.controller;

import com.sparta.springmemo.dto.MemoRequestDTO;
import com.sparta.springmemo.dto.MemoResponseDTO;
import com.sparta.springmemo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {
    private final Map<Long,Memo> memoList=new HashMap<>();
    @PostMapping("/memos")
    public MemoResponseDTO createMemo(@RequestBody MemoRequestDTO requestDto){
        // requestdto -> entity
        Memo memo = new Memo(requestDto);
        //Memo Max Id Check
        Long maxId= memoList.size()>0? Collections.max(memoList.keySet())+1:1;
        memo.setId(maxId);
        //DB 저장
        memoList.put(memo.getId(),memo);
        //Entity->responseDTO
        return new MemoResponseDTO(memo);
    }

    @GetMapping("/memos")
    public List<MemoResponseDTO> getMemos(){
        return memoList.values().stream().map(MemoResponseDTO::new).toList();
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDTO requestDTO){
        //해당 메모가 db에 존재하는지 확인
        if(memoList.containsKey(id)){
            Memo memo=memoList.get(id);
            //메모 수정
            memo.update(requestDTO);
            return memo.getId();
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        //해당 메모가 db에 존재하는지 확인
        if(memoList.containsKey(id)){
            memoList.remove(id);
            return id;
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

}
