package com.lgm.study.chapter2.act2;

public class PCSpecType1 {
    // 필드에 기본값 부여
    private String monQuality = "FHD";
    private String cpu = "Intel i5";
    private String gpu = "GTX 1060";
    private int ram = 16;

    // private 생성자
    private PCSpecType1(Builder builder) {
        this.monQuality = builder.monQuality;
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
    }

    // 정적 내부 클래스로 Builder 생성
    public static class Builder {
        private String monQuality;
        private String cpu;
        private String gpu;
        private int ram;

        public Builder monQuality (String monQuality) {
            this.monQuality = monQuality;
            return this;
        }

        public Builder cpu (String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder gpu (String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder ram (int ram) {
            this.ram = ram;
            return this;
        }

        // 객체 생성 메서드
        public PCSpecType1 build() {
            return new PCSpecType1(this);
        }
    }
}
