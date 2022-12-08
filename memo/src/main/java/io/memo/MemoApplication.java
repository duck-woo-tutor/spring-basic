package io.memo;

import io.memo.entity.Memo;
import io.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class MemoApplication implements ApplicationRunner {
    private final MemoRepository memoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Memo> memos = new ArrayList<>();

        memos.add(new Memo("tester1", "contents test"));
        memos.add(new Memo("tester2", "contents test~"));
        memos.add(new Memo("tester3", "contents 2test"));
        memos.add(new Memo("tester4", "contents test~@3"));
        memos.add(new Memo("tester5", "contents test$4"));
        memos.add(new Memo("tester6", "contents test5"));
        memos.add(new Memo("tester7", "contents 6test"));
        memos.add(new Memo("tester8", "contents test7"));
        memos.add(new Memo("tester9", "contents test78"));
        memos.add(new Memo("tester10", "contents t9est"));
        memos.add(new Memo("tester11", "contents 10test"));

        memoRepository.saveAll(memos);
    }
}
