package com.example.configs;
public class MyConfigs {

    private String one;
    private String two;
    private String three;

    public MyConfigs(final String one, final String two, final String three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("MyConfigs [")//
                .append("one=\"")//
                .append(one).append("\"")//
                .append(",two=\"")//
                .append(two).append("\"")//
                .append(",three=\"")//
                .append(three).append("\"")//
                .append("]");
        return builder.toString();
    }
}
