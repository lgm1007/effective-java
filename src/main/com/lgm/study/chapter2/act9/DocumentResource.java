package com.lgm.study.chapter2.act9;

// AutoCloseable 구현
public class DocumentResource implements AutoCloseable {
    public void readContent() {
        System.out.println("Document Resource's readContent method.");
    }
    
    // 실제 close 메서드 구현 시 구체적인 Exception throw 할 것 권장
    // close 동작이 전혀 실패할 리 없는 경우 Exception throw 하지 않을 것 권장
    // close 메서드 구현 시 InterruptedException throw 하지 않을 것 권장
    @Override
    public void close() throws Exception {
        System.out.println("DocumentResource Closed.");
    }
}
