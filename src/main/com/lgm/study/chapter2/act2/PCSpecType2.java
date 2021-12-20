package com.lgm.study.chapter2.act2;

public class PCSpecType2 {
    private final int id;
    private final String monQuality;
    private final String cpu;
    private final String gpu;
    private final int ram;

    private PCSpecType2(Builder builder) {
        this.id = builder.id;
        this.monQuality = builder.monQuality;
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
    }

    public static class Builder {
        private int id;
        private String monQuality;
        private String cpu;
        private String gpu;
        private int ram;

        // 필수 필드값은 빌더 생성자에서 입력받음
        public Builder(int id) {
            this.id = id;
        }

        public PCSpecType2.Builder monQuality (String monQuality) {
            this.monQuality = monQuality;
            return this;
        }

        public PCSpecType2.Builder cpu (String cpu) {
            this.cpu = cpu;
            return this;
        }

        public PCSpecType2.Builder gpu (String gpu) {
            this.gpu = gpu;
            return this;
        }

        public PCSpecType2.Builder ram (int ram) {
            this.ram = ram;
            return this;
        }

        public PCSpecType2 build() { return new PCSpecType2(this); }
    }
}
