package com.lgm.study.chapter2.act9;

public class DocReader {
    public static void readDoc() {
        // try(...) 내 resource 객체 선언 및 할당 (try-with-resources)
        // 선언한 변수들은 try 내에서만 사용 가능
        // 코드 실행 위치가 try 문을 벗어나면 선언된 객체의 close() 메서드 호출
        // 자동 close() 호출은 AutoCloseable을 구현한 객체만 해당
        try (DocumentResource documentResource = new DocumentResource()) {
            documentResource.readContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
