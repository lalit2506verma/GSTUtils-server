package com.GSTUtils.server.Helper;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int number;

    Month(int number){
        this.number = number;
    }

    public String getName() {
        return this.name();
    }

    public int getNumber(){
        return this.number;
    }

    public static Month fromNumber(int number){
        for(Month m : values()){
            if(m.number == number){
                return m;
            }
        }

        throw new IllegalArgumentException("invalid Month name" + number);
    }
}
