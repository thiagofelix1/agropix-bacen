package com.agropix.bacen.domain.type.either;

public class Right<TLeft, TRight> implements Either<TLeft, TRight> {
    private final TRight value;
    private Right(TRight value) {
        this.value = value;
    }

    public static <TLeft, TRight> Right<TLeft, TRight> create(TRight value) {
        return new Right<>(value);
    }

    public boolean isLeft() {
        return false;
    }

    public boolean isRight() {
        return true;
    }

    public TRight unsafeGetRight() {
        if (value == null) {
            throw new IllegalArgumentException("Left value was empty");
        } else {
            return value;
        }
    }

    public TLeft unsafeGetLeft() {
        throw new IllegalStateException("Left instance cannot contain right values");
    }
}
