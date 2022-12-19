package com.agropix.bacen.domain.type.either;

public class Left<TLeft, TRight> implements Either<TLeft, TRight> {

    private final TLeft value;

    private Left(TLeft value) {
        this.value = value;
    }


    public static <TLeft, TRight> Left<TLeft, TRight> create(TLeft value) {
        return new Left<>(value);
    }

    public boolean isLeft() {
        return true;
    }

    public boolean isRight() {
        return false;
    }

    public TLeft unsafeGetLeft() {
        if (value == null) {
            throw new IllegalArgumentException("Left value was empty");
        } else {
            return value;
        }
    }

    public TRight unsafeGetRight() {
        throw new IllegalStateException("Left instance cannot contain right values");
    }
}
