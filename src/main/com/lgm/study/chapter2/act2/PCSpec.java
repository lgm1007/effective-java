package com.lgm.study.chapter2.act2;

public class PCSpec {
    private final String monQuality;
    private final String cpu;
    private final String gpu;
    private final int ram;

    // private 생성자
    private PCSpec (Builder builder) {
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
        public PCSpec build() {
            return new PCSpec(this);
        }
    }
}
