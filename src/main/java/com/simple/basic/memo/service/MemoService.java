package com.simple.basic.memo.service;

import java.util.List;

import com.simple.basic.command.MemoVO;

public interface MemoService {
	
	void memoRegist(MemoVO vo);
	List<MemoVO> getMemoList();

}
